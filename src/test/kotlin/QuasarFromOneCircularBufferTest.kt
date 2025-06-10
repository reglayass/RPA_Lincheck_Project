import co.paralleluniverse.strands.queues.CircularIntBuffer
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class QuasarFromOneCircularBufferTest {
    private var buffer = CircularIntBuffer(3, true);

    @Operation(nonParallelGroup = "producer")
    fun enq(i: Int) = buffer.enq(i)

    @Operation(nonParallelGroup = "consumer")
    fun poll() = buffer.poll()

    @Test
    fun runStressTest() = StressOptions().check(this::class)

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)
}