import org.agrona.concurrent.ManyToManyConcurrentArrayQueue
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class ManyToManyConcurrentArrayQueueTest {
    private val queue = ManyToManyConcurrentArrayQueue<Int>(3)

    @Operation
    fun offer(@Param(gen = IntGen::class, conf = "1:5") x: Int) = queue.offer(x)

    @Operation
    fun poll() = queue.poll()

    @Operation
    fun peek() = queue.peek()

    @Operation
    fun size() = queue.size

    @Operation
    fun isEmpty() = queue.isEmpty()

    @Operation
    fun contains(@Param(gen = IntGen::class, conf="1:10")x: Int) = queue.contains(x)

    @Operation
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