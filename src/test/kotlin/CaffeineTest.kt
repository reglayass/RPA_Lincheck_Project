import com.github.benmanes.caffeine.cache.Caffeine
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.junit.Test

class CaffeineTest {
    private var cache = Caffeine.newBuilder().build<Int, Int>()

    @Operation
    fun get(k: Int) = cache.getIfPresent(k)

    @Operation
    fun put(k: Int, v: Int) = cache.put(k, v)

    @Operation
    fun invalidate(k: Int) = cache.invalidate(k)

    @Operation
    fun invalidateAll() = cache.invalidateAll()

    @Test
    fun modelChecking() = ModelCheckingOptions().check(this::class)
}