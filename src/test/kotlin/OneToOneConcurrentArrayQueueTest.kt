import org.agrona.concurrent.OneToOneConcurrentArrayQueue
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class OneToOneConcurrentArrayQueueTest {
    private val queue = OneToOneConcurrentArrayQueue<Int>(3)

    @Operation(nonParallelGroup = "producer")
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
        .sequentialSpecification(ArrayDequeSpec::class.java)
        .threads(3)
        .iterations(100)
        .check(this::class)

    @Test
    fun runStressTest() = StressOptions()
        .sequentialSpecification(ArrayDequeSpec::class.java)
        .threads(3)
        .iterations(100)
        .check(this::class)
}