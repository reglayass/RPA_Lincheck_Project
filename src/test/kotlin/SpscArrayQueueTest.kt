import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.junit.*
import org.jctools.queues.SpscArrayQueue
import org.jetbrains.kotlinx.lincheck.paramgen.*

@Param(name = "element", gen = IntGen::class, conf = "1:5")
class SpscArrayQueueTest {
    private var queue = SpscArrayQueue<Int>(2)

    @Operation(nonParallelGroup="consumers")
    fun offer(@Param(name = "element") element: Int) = queue.offer(element)

    @Operation(nonParallelGroup="consumers")
    fun poll() = queue.poll()

    @Test
    fun modelCheckingTest() = ModelCheckingOptions().check(this::class)
}