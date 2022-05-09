package com.png.interview.weather.ui.binder

import android.app.Activity
import androidx.preference.PreferenceManager
import com.png.interview.R
import com.png.interview.weather.utils.PreferenceUtils

class SettingsFragmentViewBinder(
    activity: Activity
) {
    private val prefs = PreferenceManager.getDefaultSharedPreferences(activity)

    fun onRadioChanged(id: Int) {
        prefs.edit().putInt(PreferenceUtils.PREF_UNITS,
            when (id) {
                R.id.metricChecked -> 1
                R.id.imperialChecked -> 0
                else -> 0
            }
        ).apply()
    }

    fun currentChecked(index: Int): Boolean {
        return prefs.getInt(PreferenceUtils.PREF_UNITS, 0) == index
    }
}