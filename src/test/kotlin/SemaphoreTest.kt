import co.paralleluniverse.strands.concurrent.Semaphore
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class SemaphoreTest {
    private var semaphore = Semaphore(1)

    @Operation
    fun tryAcquire() = semaphore.tryAcquire()

    @Operation(promptCancellation = true, allowExtraSuspension = true)
    fun acquire() = semaphore.acquire()

    @Operation
    fun release() = semaphore.release()

    @Test
    fun stressTest() = StressOptions().check(this::class)

    @Test
    fun modelChecking() = ModelCheckingOptions().checkObstructionFreedom().check(this::class)
}