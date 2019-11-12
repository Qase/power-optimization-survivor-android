package wtf.qase.power_optimization_survivor.dto

import android.content.Context
import wtf.qase.power_optimization_survivor.PowerOptimisationSurvivorLogger

class PowerManagerOption(
    val powerManagerSetting: PowerManagerSetting,
    private val intents: List<PowerManagerIntent>
) {
    fun isCallable(context: Context): Boolean {
        return intents.any { it.isCallable(context) }
    }

    fun startIntent(context: Context): Boolean {
        for (powerManagerIntent in intents) {
            try {
                val intent = powerManagerIntent.createIntent()
                context.startActivity(intent)
                return true
            } catch (e: Exception) {
                PowerOptimisationSurvivorLogger.e(this.javaClass.simpleName, e)
            }
        }
        return false
    }
}
