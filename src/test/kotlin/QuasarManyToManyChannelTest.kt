import co.paralleluniverse.strands.channels.Channels
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class QuasarManyToManyChannelTest {
    private var channel = Channels.newChannel<Int>(3,Channels.OverflowPolicy.BLOCK, false, false)

    @Operation
    fun trySend(i: Int) = channel.trySend(i)

    @Operation
    fun tryReceive() = channel.tryReceive()

    @Test
    fun runStressTest() = StressOptions().check(this::class)

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)
}