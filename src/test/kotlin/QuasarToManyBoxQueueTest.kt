import co.paralleluniverse.strands.queues.BoxQueue
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class QuasarToManyBoxQueueTest {
    private var queue = BoxQueue<Int>(true, false);

    @Operation
    fun enq(i: Int) = queue.enq(i)

    @Operation
    fun poll() = queue.poll()

    @Operation
    fun size() = queue.size()

    @Test
    fun runStressTest() = StressOptions().check(this::class)

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)

}