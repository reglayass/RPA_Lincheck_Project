import org.jctools.queues.SpmcArrayQueue
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class SpmcArrayQueueTest {
    private var queue = SpmcArrayQueue<Int>(3);

    @Operation(nonParallelGroup = "consumers")
    fun offer(@Param(gen = IntGen::class, conf="1:5") e: Int) = queue.offer(e)

    @Operation
    fun peek() = queue.peek()

    @Operation
    fun poll() = queue.poll()

    @Operation
    fun size() = queue.size

    @Operation
    fun clear() = queue.clear()

    @Operation
    fun isEmpty() = queue.isEmpty

    @Test
    fun runStressTest() = StressOptions()
        .sequentialSpecification(ArrayQueueSequentialSpec::class.java)
        .threads(3)
        .iterations(100)
        .check(this::class)

    @Test
    fun modelChecking() = ModelCheckingOptions()
        .sequentialSpecification(ArrayQueueSequentialSpec::class.java)
        .threads(3)
        .iterations(100)
        .check(this::class)
}