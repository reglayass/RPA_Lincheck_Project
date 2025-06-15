import org.agrona.concurrent.ManyToOneConcurrentArrayQueue
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class ManyToOneConcurrentArrayQueueTest {
    private val queue = ManyToOneConcurrentArrayQueue<Int>(3)

    @Operation
    fun offer(@Param(gen = IntGen::class, conf = "1:5") x: Int) = queue.offer(x)

    @Operation(nonParallelGroup = "consumers")
    fun poll() = queue.poll()

    @Operation(nonParallelGroup = "consumers")
    fun peek() = queue.peek()

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions()
        .sequentialSpecification(ArrayDequeSpec::class.java)
        .minimizeFailedScenario(false)
        .check(this::class)

    @Test
    fun runStressTest() = StressOptions()
        .sequentialSpecification(ArrayDequeSpec::class.java)
        .minimizeFailedScenario(false)
        .check(this::class)
}