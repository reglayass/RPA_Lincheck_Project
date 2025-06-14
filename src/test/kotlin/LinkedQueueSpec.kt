class LinkedQueueSpec {
    private val buf = java.util.LinkedList<Int>()

    fun offer(e: Int): Boolean {
        buf.addLast(e)
        return true
    }

    fun poll(): Int? =
        if (buf.isEmpty()) null else buf.removeFirst()

    fun peek(): Int? = buf.peekFirst()

    fun size(): Int = buf.size

    fun isEmpty(): Boolean = buf.isEmpty()

    fun clear() = buf.clear()
}