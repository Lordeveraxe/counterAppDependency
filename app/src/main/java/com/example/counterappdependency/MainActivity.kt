package com.example.counterappdependency

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var counterViewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtain the TextView where we will display the counter
        val counterTextView: TextView = findViewById(R.id.counterTextView)
        val resetButton: Button = findViewById(R.id.resetButton)

        // Initialize the ViewModel
        counterViewModel = ViewModelProvider(this)[CounterViewModel::class.java]

        // Observe the counter LiveData
        counterViewModel.counter.observe(this, Observer { counter ->
            counterTextView.text = counter.toString()
        })

        // Increment the counter on each activity start
        counterViewModel.incrementCounter()

        // Set up the reset button to reset the counter to 0
        resetButton.setOnClickListener {
            counterViewModel.resetCounter()
        }
    }
}