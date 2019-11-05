package wtf.qase.power_optimization_survivor.dto

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

class PowerManagerIntent(
    private val componentPackage: String,
    private val componentClass: String,
    private val category: String? = null,
    private val data: Uri? = null,
    private val extrasString: Map<String, String>? = null,
    private val action: String? = null
) {

    fun isCallable(context: Context): Boolean {
        val intent = createIntent()
        try {
            return context.packageManager.resolveActivity(
                intent,
                PackageManager.MATCH_DEFAULT_ONLY
            ) != null
        } catch (ignored: Exception) {
            return false
        }
    }

    fun createIntent(): Intent {
        val intent = Intent().setComponent(
            ComponentName(
                componentPackage,
                componentClass
            )
        )
        if (data != null) {
            intent.setData(data)
        }
        if (category != null) {
            intent.addCategory(category)
        }
        if (extrasString != null) {
            for (entry in extrasString.entries) {
                intent.putExtra(entry.key, entry.value)
            }
        }
        return intent
    }
}
