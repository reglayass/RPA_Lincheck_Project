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
    fun offer(@Param(gen = IntGen::class, conf = "1:10") x: Int) = queue.offer(x)

    @Operation(nonParallelGroup = "consumer")
    fun poll() = queue.poll()

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions().check(this::class.java)

    @Test
    fun runStressTest() = StressOptions().check(this::class)
}