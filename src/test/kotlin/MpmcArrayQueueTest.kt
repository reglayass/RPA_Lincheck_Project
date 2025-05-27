import org.jctools.queues.MpmcArrayQueue
import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.junit.*
import org.jetbrains.kotlinx.lincheck.paramgen.*

class MpmcArrayQueueTest {
    private var queue = MpmcArrayQueue<Int>(3)

    @Operation
    fun offer(@Param(gen = IntGen::class, conf = "0:5") e: Int) = queue.offer(e)

    @Operation
    fun peek() = queue.peek()

    @Operation
    fun poll() = queue.poll()

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)
}