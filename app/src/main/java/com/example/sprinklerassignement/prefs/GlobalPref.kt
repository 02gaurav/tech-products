package com.example.sprinklerassignement.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.sprinklerassignement.data.FILTER_TYPE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalPref
@Inject
constructor(private val mContext: Context)
{

    companion object {
        private const val PREF_NAME = "TECHPRODUCT"
        private const val KEY_CURRENT_FILTER = "KEY_CURRENT_FILTER"
    }

    private var sharedPref: SharedPreferences =  mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var currentFilter: String
    get() = sharedPref.getString(KEY_CURRENT_FILTER, "")?: FILTER_TYPE.NONE.name
    set(value) = sharedPref.putString(KEY_CURRENT_FILTER, value)
}


fun SharedPreferences.putString(key: String, value: String?) {
    this.edit().putString(key, value).apply()
}