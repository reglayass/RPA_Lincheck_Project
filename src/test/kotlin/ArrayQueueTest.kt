import co.paralleluniverse.strands.queues.ArrayQueue
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.junit.Test

class ArrayQueueTest {
    private var queue = ArrayQueue<Int>(3);

    @Operation
    fun enq(i: Int) = queue.enq(i)

    @Operation
    fun poll() = queue.poll()

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)
}