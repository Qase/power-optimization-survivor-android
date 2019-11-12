## Developer manual for the library

All you need to know is explained in object PowerOptimizationSurvivorUtil. You can also check the demo app.

### Common battery optimisation
To disable common battery optimisation, that is present on all android phones since api 23, use one of two methods:
- PowerOptimizationSurvivorUtil::ignoreBatteryOptimizations
- PowerOptimizationSurvivorUtil::goToIgnoreBatteryOptimizationSettings

### Vendor specific battery optimisation
A lot of vendors add custom battery optimisation mechanisms. This library is trying to keep up with the list and provide mechanism to guide user to disable these settings.
Map of all the mechanism is available by PowerOptimizationSurvivorUtil::providePowerManagerSettingsMap.
You should present user with all options available for his manufacturer and explain, what he should set there. (Check: [user-manual.md](user-manual.md)).

To check whether the option is available on the device, call PowerManagerOption::isCallable
To open the setting, call PowerManagerOption::startIntent


```kotlin

object PowerOptimizationSurvivorUtil {

    /**
     * This method is used identify if current app is already excluded from battery optimization.
     * @return Boolean Returns true if app is not optimized, true if it is.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun isIgnoringBatteryOptimizations(context: Context): Boolean {
        val packageName = context.packageName
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return pm.isIgnoringBatteryOptimizations(packageName)
    }

    /**
     * READ THIS BEFORE USING THIS METHOD: https://developer.android.com/training/monitoring-device-state/doze-standby#whitelisting-cases
     * This method opens dialog asking user to ignore battery optimization for this app.
     * If you are using this method, you need to have <uses-permission android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" /> in AndroidManifest.
     * After releasing the app with this permission, google bot will most probably delete it from google play and after that, you need to explain them, why you need to use it and hope they will allow it.
     * Alternatively, you can use PowerOptimizationSurvivorUtil::goToIgnoreBatteryOptimizationSettings, which directs user to power optimisation settings.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun ignoreBatteryOptimizations(context: Context) {
        val packageName = context.packageName
        val intent = Intent()
        intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
        intent.data = Uri.parse("package:$packageName")
        context.startActivity(intent)
    }

    /**
     * This method is safer alternative of PowerOptimizationSurvivorUtil::ignoreBatteryOptimizations
     * It does not show dialog straight from app, but only navigates user to the battery optimization settings.
     * Google allows this with no restrictions.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun goToIgnoreBatteryOptimizationSettings(context: Context) {
        val intent = Intent()
        intent.action = Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS
        context.startActivity(intent)
    }

    /**
     * This method returns map of vendor specific battery optimisation settings. Key of the map is vendor in lower case e.g. 'samsung', 'huawei',..
     * The way to use it is to check users vendor and present him with vendor specific options (it exists) and explain him, what to set there.
     * Not every PowerManagerOption is present on all devices from the vendor, so you need to check PowerManagerOption::isCallable to be sure, the setting is present on the device.
     */
    fun providePowerManagerSettingsMap(): Map<String, List<PowerManagerOption>> {
        return MapProviderUtil.providePowerManagerSettingsMap()
    }
}

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
```
