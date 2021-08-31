package co.edu.udea.compumovil.gr02_20211.lab2.view.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import co.edu.udea.compumovil.gr02_20211.lab2.R
import co.edu.udea.compumovil.gr02_20211.lab2.data.preferences.EnvironmentPreferences
import co.edu.udea.compumovil.gr02_20211.lab2.data.preferences.Preferences

class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        preference?.let {
            if (it.key == "logOut") {
                context?.let { EnvironmentPreferences(it).setPreference(Preferences.USER_LOGGED_IN, false)}

                findNavController().navigate(R.id.action_preferenceFragment_to_loginActivity)
                activity?.finish()
            }
        }
        return super.onPreferenceTreeClick(preference)
    }
}