package org.example

object ToggleLite {
    private val flags = mutableMapOf<String, Boolean>()
    private val listeners = mutableMapOf<String, MutableList<(Boolean) -> Unit>>()

    fun set(key: String, enabled: Boolean) {
        flags[key] = enabled
        listeners[key]?.forEach { it(enabled) }
    }

    fun isEnabled(key: String, default: Boolean = false): Boolean = flags[key] ?: default

    fun toggle(key: String) = set(key, !isEnabled(key))

    fun observe(key: String, listener: (Boolean) -> Unit): () -> Unit {
        listeners.getOrPut(key) { mutableListOf() }.add(listener)
        return { listeners[key]?.remove(listener) }
    }

    fun setAll(config: Map<String, Boolean>) = config.forEach { (k, v) -> set(k, v) }

    fun getAll(): Map<String, Boolean> = flags.toMap()

    fun clear() {
        flags.clear()
        listeners.clear()
    }

    inline fun <T> whenEnabled(key: String, action: () -> T): T? = if (isEnabled(key)) action() else null
}
