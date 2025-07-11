import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked
import org.jctools.queues.MpscLinkedQueue
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class MpscLinkedQueueTest {
    private var queue = MpscLinkedQueue<Int>()

    @Operation
    fun offer(@Param(gen = IntGen::class, conf="1:5") e: Int) = queue.offer(e)

    @Operation(nonParallelGroup = "consumers")
    fun peek() = queue.peek()

    @Operation(nonParallelGroup = "consumers")
    fun poll() = queue.poll()

    @Test
    fun runStressTest() = StressOptions().check(this::class)

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)
}