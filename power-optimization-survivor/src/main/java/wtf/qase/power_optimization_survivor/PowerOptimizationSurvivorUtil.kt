package wtf.qase.power_optimization_survivor

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.annotation.RequiresApi
import wtf.qase.power_optimization_survivor.dto.PowerManagerOption

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
