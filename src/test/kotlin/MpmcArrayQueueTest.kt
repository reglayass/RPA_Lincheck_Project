import org.jctools.queues.MpmcArrayQueue
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class MpmcArrayQueueTest {
    private var queue = MpmcArrayQueue<Int>(3);

    @Operation
    fun offer(@Param(gen = IntGen::class, conf="1:5") e: Int) = queue.offer(e)

    @Operation
    fun peek() = queue.peek()

    @Operation
    fun poll() = queue.poll()

    @Test
    fun runStressTest() = StressOptions().minimizeFailedScenario(false).sequentialSpecification(ArrayDequeSpec::class.java).check(this::class)

    @Test
    fun modelChecking() = ModelCheckingOptions().minimizeFailedScenario(false).sequentialSpecification(ArrayDequeSpec::class.java).check(this::class)
}