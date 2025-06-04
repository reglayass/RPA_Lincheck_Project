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
    fun offer(@Param(gen = IntGen::class, conf="0:10")x: Int) = queue.offer(x)

    @Operation(nonParallelGroup = "consumers")
    fun poll() = queue.poll()

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions().sequentialSpecification(QueueModel::class.java).check(this::class)

    @Test
    fun runStressTest() = StressOptions().sequentialSpecification(QueueModel::class.java).check(this::class)
}

class QueueModel {
    private val queue = ArrayDeque<Int>()
    private val capacity = 3

    @Operation
    fun offer(x: Int): Boolean {
        return if (queue.size < capacity) {
            queue.addLast(x)
            true
        } else {
            false
        }
    }

    @Operation
    fun poll(): Int? {
        return if (queue.isEmpty()) {
            null
        } else {
            queue.removeFirst()
        }
    }
}