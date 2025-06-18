import co.paralleluniverse.strands.concurrent.Semaphore
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class QuasarSemaphoreTest {
    private var semaphore = Semaphore(3, true)

    @Operation
    fun tryAcquire() = semaphore.tryAcquire()

    @Operation
    fun release() = semaphore.release()

    @Operation
    fun availablePermits() = semaphore.availablePermits()

    @Operation
    fun drainPermits() = semaphore.drainPermits()

    @Test
    fun stressTest() = StressOptions().check(this::class)

    @Test
    fun modelChecking() = ModelCheckingOptions().checkObstructionFreedom().check(this::class)
}