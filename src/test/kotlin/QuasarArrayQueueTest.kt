import co.paralleluniverse.strands.queues.ArrayQueue
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class QuasarArrayQueueTest {
    private var queue = ArrayQueue<Int>(3);

    @Operation
    fun enq(i: Int) = queue.enq(i)

    @Operation
    fun poll() = queue.poll()

    @Test
    fun runStressTest() = StressOptions().check(this::class)

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)
}