import org.agrona.concurrent.ManyToOneConcurrentLinkedQueue
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class ManyToOneConcurrentLinkedQueueTest {
    private val queue = ManyToOneConcurrentLinkedQueue<Int>()

    @Operation
    fun offer(@Param(gen = IntGen::class, conf="1:10")x : Int) = queue.offer(x)

    @Operation(nonParallelGroup = "consumer")
    fun poll() = queue.poll()

    @Operation(nonParallelGroup = "consumer")
    fun peek() = queue.peek()

    @Operation(nonParallelGroup = "consumer")
    fun size() = queue.size

    @Operation(nonParallelGroup = "consumer")
    fun isEmpty() = queue.isEmpty()

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions()
        .sequentialSpecification(LinkedQueueSpec::class.java)
        .threads(3)
        .iterations(100)
        .check(this::class)

    @Test
    fun runStressTest() = StressOptions()
        .sequentialSpecification(LinkedQueueSpec::class.java)
        .threads(3)
        .iterations(100)
        .check(this::class)
}