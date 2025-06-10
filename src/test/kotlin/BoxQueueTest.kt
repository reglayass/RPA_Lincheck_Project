import co.paralleluniverse.strands.queues.BoxQueue
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.junit.Test

class BoxQueueTest {
    private var queue = BoxQueue<Int>(true, false);

    @Operation
    fun enq(i: Int) = queue.enq(i)

    @Operation
    fun poll() = queue.poll()

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)

}