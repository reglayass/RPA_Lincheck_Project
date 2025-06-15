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

    @Operation(nonParallelGroup = "consumers")
    fun size() = queue.size

    @Operation(nonParallelGroup = "consumers")
    fun isEmpty() = queue.isEmpty()

    @Operation(nonParallelGroup = "consumers")
    fun contains(@Param(gen = IntGen::class, conf="1:10")x: Int) = queue.contains(x)

    @Operation(nonParallelGroup = "consumers")
    fun clear() = queue.clear()

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions()
        .sequentialSpecification(ArrayQueueSequentialSpec::class.java)
        .threads(3)
        .iterations(100)
        .check(this::class)

    @Test
    fun runStressTest() = StressOptions()
        .sequentialSpecification(ArrayQueueSequentialSpec::class.java)
        .threads(3)
        .iterations(100)
        .check(this::class)
}