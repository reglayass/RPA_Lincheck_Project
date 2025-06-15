class ArrayQueueSequentialSpec {
    private val buffer = ArrayDeque<Int>(3)

    fun offer(e: Int): Boolean =
        if (buffer.size == 3) false else {
            buffer.addLast(e)
            true
        }

    fun poll(): Int? =
        if (buffer.isEmpty()) null else buffer.removeFirst()

    fun peek(): Int? = buffer.firstOrNull()

    fun size() = buffer.size

    fun isEmpty(): Boolean = buffer.isEmpty()

    fun clear() = buffer.clear()

    fun contains(e: Int) = buffer.contains(e)
}