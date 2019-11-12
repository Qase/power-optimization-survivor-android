package wtf.qase.power_optimization_survivor

import wtf.qase.power_optimization_survivor.dto.PowerManagerIntent
import wtf.qase.power_optimization_survivor.dto.PowerManagerOption
import wtf.qase.power_optimization_survivor.dto.PowerManagerSetting

object MapProviderUtil {

    fun providePowerManagerSettingsMap(): Map<String, List<PowerManagerOption>> {
        val map = mutableMapOf<String, List<PowerManagerOption>>()
        val xiaomi = arrayListOf<PowerManagerOption>(
            PowerManagerOption(
                PowerManagerSetting.XIAOMI_AUTOSTART,
                arrayListOf(
                    PowerManagerIntent(
                        "com.miui.securitycenter",
                        "com.miui.permcenter.autostart.AutoStartManagementActivity"
                    )
                )
            ),
            PowerManagerOption(
                PowerManagerSetting.XIAOMI_BATTERY_SAVER,
                arrayListOf(
                    PowerManagerIntent(
                        "com.miui.powerkeeper",
                        "com.miui.powerkeeper.ui.HiddenAppsContainerManagementActivity"
                    )
                )
            )
        )
        map.put("xiaomi", xiaomi)

        val huawei = arrayListOf<PowerManagerOption>(
            PowerManagerOption(
                PowerManagerSetting.HUAWEI_LOCK_SCREEN_CLEANUP,
                arrayListOf(
                    PowerManagerIntent(
                        "com.huawei.systemmanager",
                        "com.huawei.systemmanager.optimize.process.ProtectActivity"
                    )
                )
            ),
            PowerManagerOption(
                PowerManagerSetting.HUAWEI_APP_LAUNCH,
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
                PowerManagerSetting.OPPO_POWERMANAGET,
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
                PowerManagerSetting.OPPO_STARTUPMANAGER,
                arrayListOf(
                    PowerManagerIntent(
                        "com.coloros.safecenter",
                        "com.coloros.safecenter.startupapp.StartupAppListActivity"
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
                PowerManagerSetting.ASUS_MOBILE_MANAGER,
                arrayListOf(
                    PowerManagerIntent(
                        "com.asus.mobilemanager",
                        "com.asus.mobilemanager.entry.FunctionActivity"
                    )
                )
            ),
            PowerManagerOption(
                PowerManagerSetting.ASUS_AUTOSTART,
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

        val samsung = arrayListOf<PowerManagerOption>(
            PowerManagerOption(
                PowerManagerSetting.SAMSUNG_BATTERY,
                arrayListOf(
                    PowerManagerIntent(
                        "com.samsung.android.lool",
                        "com.samsung.android.sm.ui.battery.BatteryActivity"
                    )
                )
            )
        )
        map.put("samsung", samsung)

        // TODO: we do not have meizu phone to create texts
//        val meizu = arrayListOf<PowerManagerOption>(
//            PowerManagerOption(
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


        // TODO: we do not have letv phone to create texts
//        val letv = arrayListOf<PowerManagerOption>(
//            PowerManagerOption(
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

        return map
    }
}
