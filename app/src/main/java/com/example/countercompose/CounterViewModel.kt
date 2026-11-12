package com.example.countercompose

import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel

class CounterViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences(
        PREFS_NAME, Context.MODE_PRIVATE
    )

    private val _counter = mutableIntStateOf(
        prefs.getInt(KEY_COUNTER, 0)
    )
    val counter: State<Int> = _counter

    fun increment() = updateCounter(_counter.intValue + 1)
    fun decrement() = updateCounter(_counter.intValue - 1)
    fun reset() = updateCounter(0)

    private fun updateCounter(newValue: Int) {
        _counter.intValue = newValue
        prefs.edit { putInt(KEY_COUNTER, newValue) }
    }

    companion object {
        private const val PREFS_NAME = "counter_prefs"
        private const val KEY_COUNTER = "counter_value"
    }
}
