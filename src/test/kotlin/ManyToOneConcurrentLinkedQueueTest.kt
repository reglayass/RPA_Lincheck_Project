import org.agrona.concurrent.ManyToOneConcurrentLinkedQueue
import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.junit.*
import org.jetbrains.kotlinx.lincheck.paramgen.*

class ManyToOneConcurrentLinkedQueueTest {
    private var linkedQueue = ManyToOneConcurrentLinkedQueue<Int>()

    @Operation
    fun add(@Param(gen = IntGen::class, conf = "0:5")e : Int) = linkedQueue.add(e)

    @Operation
    fun offer(@Param(gen = IntGen::class, conf = "0:5") e: Int) = linkedQueue.offer(e)

    @Operation(nonParallelGroup = "consumers")
    fun peek() = linkedQueue.peek()

    @Operation(nonParallelGroup = "consumers")
    fun poll() = linkedQueue.poll()

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)
}