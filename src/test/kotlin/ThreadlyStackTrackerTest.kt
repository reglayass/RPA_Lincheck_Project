import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test
import org.threadly.util.debug.StackTracker

class ThreadlyStackTrackerTest {
    private var tracker = StackTracker()

    @Operation
    fun counts() = tracker.dumpStackCounts().size

    @Operation
    fun record() = tracker.recordStack()

    @Operation
    fun reset() = tracker.reset()

    @Test
    fun stressTest() = StressOptions().check(this::class)

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)

}