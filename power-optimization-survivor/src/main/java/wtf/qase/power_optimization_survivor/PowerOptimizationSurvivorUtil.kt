package wtf.qase.power_optimization_survivor

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.annotation.RequiresApi
import wtf.qase.power_optimization_survivor.dto.PowerManagerIntent
import wtf.qase.power_optimization_survivor.dto.PowerManagerOption

object PowerOptimizationSurvivorUtil {

    @RequiresApi(Build.VERSION_CODES.M)
    fun isIgnoringBatteryOptimizations(context: Context): Boolean {
        val packageName = context.packageName
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return pm.isIgnoringBatteryOptimizations(packageName)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun goToNotOptimised(context: Context) {
        val packageName = context.packageName
        val intent = Intent()
        intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
        intent.data = Uri.parse("package:$packageName")
        context.startActivity(intent)
    }

    fun providePowerManagerSettingsMap(context: Context): Map<String, List<PowerManagerOption>> {
        val map = mutableMapOf<String, List<PowerManagerOption>>()
        val xiaomi = arrayListOf<PowerManagerOption>(
            PowerManagerOption(
                context.getString(R.string.android_xiaomi_autostart),
                context.getString(R.string.android_xiaomi_autostart_hint),
                arrayListOf(
                    PowerManagerIntent(
                        "com.miui.securitycenter",
                        "com.miui.permcenter.autostart.AutoStartManagementActivity"
                    )
                )
            ),
            PowerManagerOption(
                context.getString(R.string.android_xiaomi_battery_saver),
                context.getString(R.string.android_xiaomi_battery_saver_hint),
                arrayListOf(
                    PowerManagerIntent(
                        "com.miui.powerkeeper",
                        "com.miui.powerkeeper.ui.HiddenAppsContainerManagementActivity"
                    )
                )
            )
        )
        map.put("xiaomi", xiaomi)

        // TODO: we do not have letv phone to create texts
//        val letv = arrayListOf<PowerManagerOption>(
//            PowerManagerOption(
//                context.getString(R.string.android_letv_autostart),
//                context.getString(R.string.android_letv_autostart_hint),
//                arrayListOf(
//                    PowerManagerIntent(
//                        "com.letv.android.letvsafe",
//                        "com.letv.android.letvsafe.AutobootManageActivity",
//                        data = Uri.parse("mobilemanager://function/entry/AutoStart")
//                    ), PowerManagerIntent(
//                        "com.miui.securitycenter",
//                        "com.miui.permcenter.autostart.AutoStartManagementActivity"
//                    )
//                )
//            )
//        )
//        map.put("letv", letv)

        val huawei = arrayListOf<PowerManagerOption>(
            PowerManagerOption(
                context.getString(R.string.android_huawei_lock_screen_cleanup),
                context.getString(R.string.android_huawei_lock_screen_cleanup_hint),
                arrayListOf(
                    PowerManagerIntent(
                        "com.huawei.systemmanager",
                        "com.huawei.systemmanager.optimize.process.ProtectActivity"
                    )
                )
            ),
            PowerManagerOption(
                context.getString(R.string.android_huawei_app_launch),
                context.getString(R.string.android_huawei_app_launch_hint),
                arrayListOf(
                    PowerManagerIntent(
                        "com.huawei.systemmanager",
                        "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity"
                    ),
                    PowerManagerIntent(
                        "com.huawei.systemmanager",
                        "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"
                    )
                )
            )
        )

        map.put("huawei", huawei)

        val oppo = arrayListOf<PowerManagerOption>(

            PowerManagerOption(
                context.getString(R.string.android_oppo_powermanaget),
                context.getString(R.string.android_oppo_powermanaget_hint),
                arrayListOf(
                    PowerManagerIntent(
                        "com.coloros.oppoguardelf",
                        "com.coloros.powermanager.fuelgaue.PowerUsageModelActivity"
                    ),
                    PowerManagerIntent(
                        "com.coloros.oppoguardelf",
                        "com.coloros.powermanager.fuelgaue.PowerSaverModeActivity"
                    ),
                    PowerManagerIntent(
                        "com.coloros.oppoguardelf",
                        "com.coloros.powermanager.fuelgaue.PowerConsumptionActivity"
                    )
                )
            ),
            PowerManagerOption(
                context.getString(R.string.android_oppo_startupmanager),
                context.getString(R.string.android_oppo_startupmanager_hint),
                arrayListOf(
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.coloros.safecenter.startupapp.StartupAppListActivity",
                        data = Uri.parse("package:" + context.getPackageName()),
                        action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                    ),
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.coloros.safecenter.permission.startup.StartupAppListActivity"
                    ),
                    PowerManagerIntent(
                        "com.oppo.safe",
                        "com.oppo.safe.permission.startup.StartupAppListActivity"
                    ),
                    PowerManagerIntent(
                        "com.oppo.safe",
                        "com.coloros.safe.permission.startup.StartupAppListActivity"
                    ),
                    PowerManagerIntent(
                        "com.oppo.safe",
                        "com.coloros.safe.permission.startupapp.StartupAppListActivity"
                    ),
                    PowerManagerIntent(
                        "com.oppo.safe",
                        "com.coloros.safe.permission.startupmanager.StartupAppListActivity"
                    ),
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.coloros.safecenter.permission.startup.FakeActivity"
                    ),
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.coloros.safecenter.permission.startupapp.StartupAppListActivity"
                    ),
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.coloros.safecenter.permission.startupmanager.StartupAppListActivity"
                    ),
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.coloros.safecenter.permission.startsettings"
                    ),
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.coloros.safecenter.permission.startupapp.startupmanager"
                    ),
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.coloros.safecenter.permission.startupmanager.startupActivity"
                    ),
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.coloros.safecenter.permission.startup.startupapp.startupmanager"
                    ),
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.coloros.privacypermissionsentry.PermissionTopActivity.Startupmanager"
                    ),
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.coloros.privacypermissionsentry.PermissionTopActivity"
                    ),
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.oppo.safe.permission.startup.StartupAppListActivity"
                    ),
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.oppo.safe.permission.startup.StartupAppListActivity"
                    )
                )
            )
        )
        map.put("oppo", oppo)

        // TODO: we do not have vivo phone to create texts
//        val vivo = arrayListOf<PowerManagerOption>(
//            PowerManagerOption(
//                context.getString(R.string.android_vivo_phoneoptimize),
//                context.getString(R.string.android_vivo_phoneoptimize_hint),
//                arrayListOf(
//                    PowerManagerIntent(
//                        "com.iqoo.secure",
//                        "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"
//                    ),
//                    PowerManagerIntent(
//                        "com.iqoo.secure",
//                        "om.iqoo.secure.ui.phoneoptimize.BgStartUpManager"
//                    ),
//                    PowerManagerIntent(
//                        "com.vivo.permissionmanager",
//                        "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"
//                    )
//                )
//            )
//        )
//        map.put("vivo", vivo)

        val asus = arrayListOf<PowerManagerOption>(

            PowerManagerOption(
                context.resources.getString(R.string.android_asus_mobile_manager),
                context.resources.getString(R.string.android_asus_mobile_manager_hint),
                arrayListOf(
                    PowerManagerIntent(
                        "com.asus.mobilemanager",
                        "com.asus.mobilemanager.entry.FunctionActivity"
                    )
                )
            ),
            PowerManagerOption(
                context.resources.getString(R.string.android_asus_autostart),
                context.resources.getString(R.string.android_asus_autostart_hint),
                arrayListOf(
                    PowerManagerIntent(
                        "com.asus.mobilemanager",
                        "com.asus.mobilemanager.autostart.AutoStartActivity"
                    ),
                    PowerManagerIntent(
                        "com.asus.mobilemanager",
                        "com.asus.mobilemanager.MainActivity"
                    )
                )
            )
        )
        map.put("asus", asus)

        // TODO: we do not have meizu phone to create texts
//        val meizu = arrayListOf<PowerManagerOption>(
//            PowerManagerOption(
//                context.resources.getString(R.string.android_meizu_security_page),
//                context.resources.getString(R.string.android_meizu_security_page_hint),
//                arrayListOf(
//                    PowerManagerIntent(
//                        "com.meizu.safe",
//                        "com.meizu.safe.security.SHOW_APPSEC",
//                        category = Intent.CATEGORY_DEFAULT,
//                        extrasString = mapOf<String, String>(
//                            Pair(
//                                "packageName",
//                                BuildConfig.APPLICATION_ID
//                            )
//                        )
//                    )
//                )
//            )
//        )
//        map.put("meizu", meizu)

        val samsung = arrayListOf<PowerManagerOption>(
            PowerManagerOption(
                context.resources.getString(R.string.android_samsung_battery),
                context.resources.getString(R.string.android_samsung_battery_hint),
                arrayListOf(
                    PowerManagerIntent(
                        "com.samsung.android.lool",
                        "com.samsung.android.sm.ui.battery.BatteryActivity"
                    )
                )
            )
        )
        map.put("samsung", samsung)
        return map
    }
}
