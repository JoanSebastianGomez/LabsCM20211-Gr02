package co.edu.udea.compumovil.gr02_20211.lab2.data.preferences

interface PreferencesManager {

    fun setPreference(key: String, value: String)
    fun setPreference(key: String, value: Boolean)
    fun isPreferenceActive(key: String) : Boolean
}