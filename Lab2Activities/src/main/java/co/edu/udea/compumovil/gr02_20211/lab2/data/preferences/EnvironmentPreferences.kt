package co.edu.udea.compumovil.gr02_20211.lab2.data.preferences

import android.content.Context

class EnvironmentPreferences(context: Context) : PreferencesManager {

    private val preferences = context.getSharedPreferences(
        Preferences.PREFERENCE_NAME, Context.MODE_PRIVATE
    )

    override fun setPreference(key: String, value: String) {
        preferences.edit().apply {
            putString(key, value)
            apply()
        }
    }

    override fun setPreference(key: String, value: Boolean) {
        preferences.edit().apply {
            putBoolean(key, value)
            apply()
        }
    }

    override fun isPreferenceActive(key: String) = preferences.getBoolean(key, false)
}