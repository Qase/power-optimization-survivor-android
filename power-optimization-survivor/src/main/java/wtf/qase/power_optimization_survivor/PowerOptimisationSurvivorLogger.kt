package wtf.qase.power_optimization_survivor

class PowerOptimisationSurvivorLogger {

    companion object {
        private val listeners: MutableList<Listener> = mutableListOf()

        fun registerListener(listener: Listener) {
            listeners.add(listener)
        }

        fun unregisterListener(listener: Listener) {
            listeners.remove(listener)
        }

        fun e(tag: String, message: String) {
            for (listener in listeners) {
                listener.e(tag, message)
            }
        }

        fun e(tag: String, throwable: Throwable) {
            for (listener in listeners) {
                listener.e(tag, throwable)
            }
        }

        fun i(tag: String, message: String) {
            for (listener in listeners) {
                listener.i(tag, message)
            }
        }

        fun d(tag: String, message: String) {
            for (listener in listeners) {
                listener.d(tag, message)
            }
        }

        fun w(tag: String, message: String) {
            for (listener in listeners) {
                listener.w(tag, message)
            }
        }

        fun v(tag: String, message: String) {
            for (listener in listeners) {
                listener.v(tag, message)
            }
        }
    }

    interface Listener {
        fun e(tag: String, message: String)
        fun e(tag: String, throwable: Throwable)
        fun d(tag: String, message: String)
        fun i(tag: String, message: String)
        fun w(tag: String, message: String)
        fun v(tag: String, message: String)
    }
}
