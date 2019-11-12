package wtf.qase.power_optimization_survivor_android

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import wtf.qase.power_optimization_survivor.PowerOptimizationSurvivorUtil
import wtf.qase.power_optimization_survivor.dto.PowerManagerOption
import wtf.qase.power_optimization_survivor.dto.PowerManagerSetting

class MainActivity : AppCompatActivity() {

    val myAdapter = MyAdapter(arrayListOf())
    val self = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(
                this,
                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                false
            )
        val map = PowerOptimizationSurvivorUtil.providePowerManagerSettingsMap()

        val manufacturers = map.map { it.key }.toMutableList()
        val dataAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, manufacturers
        )
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        deviceManufacturer.setAdapter(dataAdapter)
        deviceManufacturer.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                myAdapter.updateItems(arrayListOf())
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedManufacturer = manufacturers[position]
                myAdapter.updateItems(map[selectedManufacturer] ?: arrayListOf())
            }
        }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = myAdapter
        recyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                this,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )

        val manufacturer = Build.MANUFACTURER.toLowerCase()
        if (map.containsKey(manufacturer)) {
            deviceManufacturer.setSelection(manufacturers.indexOf(manufacturer))
        }
        doNotOptimize.setOnClickListener {
            if (PowerOptimizationSurvivorUtil.isIgnoringBatteryOptimizations(this)) {
                Toast.makeText(this, getString(R.string.already_not_optimized), Toast.LENGTH_LONG)
                    .show()
            } else {
                PowerOptimizationSurvivorUtil.ignoreBatteryOptimizations(this)
            }
        }

        doNotOptimizeSettings.setOnClickListener {
            PowerOptimizationSurvivorUtil.goToIgnoreBatteryOptimizationSettings(this)
        }
    }

    inner class MyAdapter(internal var items: List<PowerManagerOption>) :
        androidx.recyclerview.widget.RecyclerView.Adapter<MyAdapter.DeviceViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.common_power_setting_item, parent, false)
            return DeviceViewHolder(view)
        }

        override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
            val device = items[position]
            holder.bind(device)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        fun updateItems(list: List<PowerManagerOption>) {
            this.items = list
            this.notifyDataSetChanged()
        }

        // Now define the viewholder for Normal list item
        inner class DeviceViewHolder(itemView: View) :
            androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
            fun bind(powerManagerOption: PowerManagerOption) {
                val powerSettingsTitle = itemView.findViewById<TextView>(R.id.powerSettingsTitle)
                powerSettingsTitle.text = translateLabel(powerManagerOption.powerManagerSetting)

                itemView.setOnClickListener {
                    val builder = AlertDialog.Builder(self)
                    builder.setTitle(translateLabel(powerManagerOption.powerManagerSetting))
                        .setMessage(translateHint(powerManagerOption.powerManagerSetting))
                        .setCancelable(false)
                        .setPositiveButton(getText(R.string.understand)) { dialog, id ->
                            val started = powerManagerOption.startIntent(self)
                            if (!started) {
                                Toast.makeText(
                                    self,
                                    getString(R.string.unsupported_power_setting),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            dialog.dismiss()
                        }
                        .setNegativeButton(getText(R.string.cancel)) { dialog, id -> dialog.cancel() }
                    val alert = builder.create()
                    alert.show()
                }
            }
        }
    }

    private fun translateLabel(powerManagerSetting: PowerManagerSetting): String {
        return when (powerManagerSetting) {
            PowerManagerSetting.XIAOMI_AUTOSTART -> getString(R.string.xiaomi_autostart)
            PowerManagerSetting.XIAOMI_BATTERY_SAVER -> getString(R.string.xiaomi_battery_saver)
            PowerManagerSetting.HUAWEI_LOCK_SCREEN_CLEANUP -> getString(R.string.huawei_lock_screen_cleanup)
            PowerManagerSetting.HUAWEI_APP_LAUNCH -> getString(R.string.huawei_app_launch)
            PowerManagerSetting.OPPO_POWERMANAGET -> getString(R.string.oppo_powermanaget)
            PowerManagerSetting.OPPO_STARTUPMANAGER -> getString(R.string.oppo_startupmanager)
            PowerManagerSetting.ASUS_MOBILE_MANAGER -> getString(R.string.asus_mobile_manager)
            PowerManagerSetting.ASUS_AUTOSTART -> getString(R.string.asus_autostart)
            PowerManagerSetting.SAMSUNG_BATTERY -> getString(R.string.samsung_battery)
        }
    }

    private fun translateHint(powerManagerSetting: PowerManagerSetting): String {
        return when (powerManagerSetting) {
            PowerManagerSetting.XIAOMI_AUTOSTART -> getString(R.string.xiaomi_autostart_hint)
            PowerManagerSetting.XIAOMI_BATTERY_SAVER -> getString(R.string.xiaomi_battery_saver_hint)
            PowerManagerSetting.HUAWEI_LOCK_SCREEN_CLEANUP -> getString(R.string.huawei_lock_screen_cleanup_hint)
            PowerManagerSetting.HUAWEI_APP_LAUNCH -> getString(R.string.huawei_app_launch_hint)
            PowerManagerSetting.OPPO_POWERMANAGET -> getString(R.string.oppo_powermanaget_hint)
            PowerManagerSetting.OPPO_STARTUPMANAGER -> getString(R.string.oppo_startupmanager_hint)
            PowerManagerSetting.ASUS_MOBILE_MANAGER -> getString(R.string.asus_mobile_manager_hint)
            PowerManagerSetting.ASUS_AUTOSTART -> getString(R.string.asus_autostart_hint)
            PowerManagerSetting.SAMSUNG_BATTERY -> getString(R.string.samsung_battery_hint)
        }
    }
}
