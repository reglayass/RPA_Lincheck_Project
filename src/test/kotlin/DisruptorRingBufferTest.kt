import com.lmax.disruptor.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import java.util.ArrayDeque
import kotlin.test.Test

class IntEvent { var value: Int = 0 }

object IntEventFactory : EventFactory<IntEvent> {
    override fun newInstance() = IntEvent()
}

private val TRANSLATOR = EventTranslatorOneArg<IntEvent, Int> { evt, _, arg ->
    evt.value = arg
}

class ArrayDequeSpec(private val capacity: Int = Int.MAX_VALUE) {
    private val q = ArrayDeque<Int>()

    @Operation
    fun offer(@Param(gen = IntGen::class) x: Int): Boolean {
        if (q.size == capacity) return false
        q += x
        return true
    }

    @Operation
    fun poll(): Int? = if (q.isEmpty()) null else q.poll()

    @Operation
    fun peek(): Int? = q.firstOrNull()
}

class DisruptorRingBufferTest {
    companion object { private const val SIZE = 8 }

    private val consumerSeq = Sequence(-1)

    private val rb: RingBuffer<IntEvent> =
        RingBuffer.createMultiProducer(
            IntEventFactory,
            SIZE,
            BlockingWaitStrategy()
        ).apply { addGatingSequences(consumerSeq) }

    /* ----- producer-side operation -------------------------------------- */
    @Operation
    fun offer(@Param(gen = IntGen::class) x: Int): Boolean =
        rb.tryPublishEvent(TRANSLATOR, x)

    @Operation(nonParallelGroup = "consumer")
    fun poll(): Int? {
        val next = consumerSeq.get() + 1
        if (next > rb.cursor) return null          // nothing published yet
        val evt = rb.get(next)
        consumerSeq.set(next)                      // commit consumption
        return evt.value
    }

    @Operation(nonParallelGroup = "consumer")
    fun peek(): Int? =
        if (rb.cursor > consumerSeq.get())
            rb.get(consumerSeq.get() + 1).value
        else null

    @Test
    fun modelChecking() = ModelCheckingOptions()
        .threads(3)
        .invocationsPerIteration(7)
        .iterations(100)
        .sequentialSpecification(ArrayDequeSpec(SIZE)::class.java)
        .checkObstructionFreedom(true)
        .check(this::class)
}
