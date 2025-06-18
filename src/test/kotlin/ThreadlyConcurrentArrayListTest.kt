import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test
import org.threadly.concurrent.collections.ConcurrentArrayList

class ThreadlyConcurrentArrayListTest {
    private var list = ConcurrentArrayList<Int>()

    @Operation
    fun add(i: Int) = list.add(i)

    @Operation
    fun get(i: Int) = list[i]

    @Test
    fun stressTest() = StressOptions().check(this::class)

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)


}