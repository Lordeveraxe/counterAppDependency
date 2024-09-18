package com.example.counterappdependency

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val counterKey = "counter"

    // LiveData to observe counter changes
    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> get() = _counter

    init {
        // Load the counter value from SharedPreferences
        val savedCounter = sharedPreferences.getInt(counterKey, 0)
        _counter.value = savedCounter
    }

    // Increment the counter and save it to SharedPreferences
    fun incrementCounter() {
        val newCounter = (_counter.value ?: 0) + 1
        _counter.value = newCounter
        saveCounter(newCounter)
    }

    // Reset the counter to zero and save it to SharedPreferences
    fun resetCounter() {
        _counter.value = 0
        saveCounter(0)
    }

    // Save the counter value to SharedPreferences
    private fun saveCounter(counter: Int) {
        sharedPreferences.edit().putInt(counterKey, counter).apply()
    }
}
