import co.paralleluniverse.strands.channels.Channels
import kotlinx.coroutines.channels.Channel
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.junit.Test

class ChannelTest {
    private var channel = Channels.newChannel<Int>(3,Channels.OverflowPolicy.BLOCK, false, false)

    @Operation(blocking = true)
    fun send(i: Int) = channel.trySend(i)

    @Operation(blocking = true)
    fun receive() = channel.tryReceive()

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)
}