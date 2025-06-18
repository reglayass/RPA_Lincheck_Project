import java.util.ArrayDeque

class ArrayDequeSpec {
    private val buffer = ArrayDeque<Int>(3)

    fun offer(e: Int): Boolean = buffer.offer(e)

    fun poll(): Int? =
        if (buffer.isEmpty()) null else buffer.poll()

    fun peek(): Int? = buffer.peek()
}