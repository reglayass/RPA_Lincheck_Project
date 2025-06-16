import java.util.concurrent.LinkedBlockingQueue

class LinkedQueueSpec {
    private val buffer = LinkedBlockingQueue<Int>()

    fun offer(e: Int) = buffer.offer(e)

    fun poll(): Int? = buffer.poll()

    fun peek(): Int? = buffer.peek()
}