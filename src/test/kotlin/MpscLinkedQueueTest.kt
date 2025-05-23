import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.*
import org.jetbrains.kotlinx.lincheck.strategy.stress.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.junit.*
import org.jctools.queues.MpscArrayQueue

@Param(name = "value", gen = IntGen::class, conf = "0:100")
class MpscLinkedQueueTest {
    private var capacity: Int = 10

    private val queue = MpscArrayQueue<Int>(capacity);

    @Operation
    fun offer(@Param(name = "value") value: Int): Boolean {
        return queue.offer(value)
    }

    @Operation
    fun relaxedOffer(@Param(name = "value") value: Int): Boolean {
        return queue.relaxedOffer(value)
    }

    @Operation(nonParallelGroup = "consumers")
    fun poll(): Int? {
        return queue.poll()
    }

    @Operation(nonParallelGroup = "consumers")
    fun relaxedPoll(): Int? {
        return queue.relaxedPoll()
    }

    @Operation(nonParallelGroup = "consumers")
    fun peek(): Int? {
        return queue.peek()
    }

    @Operation(nonParallelGroup = "consumers")
    fun relaxedPeek(): Int? {
        return queue.relaxedPeek()
    }

    @Test
    fun modelCheckingTest() = ModelCheckingOptions().check(this::class)
}