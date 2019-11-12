package wtf.qase.power_optimization_survivor.dto

import android.content.Context
import wtf.qase.power_optimization_survivor.PowerOptimisationSurvivorLogger

class PowerManagerOption(
    val powerManagerSetting: PowerManagerSetting,
    private val intents: List<PowerManagerIntent>
) {
    /**
     * This method checks if the option is available on current device.
     * @return Boolean Returns true if the option is available on the device.
     */
    fun isCallable(context: Context): Boolean {
        return intents.any { it.isCallable(context) }
    }

    /**
     * This method opens vendor specific setting. You should first explain to the user, what to do once it is opened.
     * @return Boolean Returns false if opening of the setting failed. Means it is either not present or is not allowed to be called.
     */
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
