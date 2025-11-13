package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private var count = Count.Base(2, 4, 0)
    private lateinit var buttonIncrement: Button
    private lateinit var buttonDecrement: Button
    private var uiState: UiState = UiState.Base("0")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.countTextView)
        buttonIncrement = findViewById(R.id.incrementButton)
        buttonDecrement = findViewById(R.id.decrementButton)
        buttonIncrement.setOnClickListener {
            uiState = count.increment(textView.text.toString())
            uiState.apply(textView, buttonIncrement, buttonDecrement)
        }

        buttonDecrement.setOnClickListener {
            uiState = count.decrement(textView.text.toString())
            uiState.apply(textView, buttonIncrement, buttonDecrement)
        }

        if (savedInstanceState == null) {
            uiState = UiState.Base("0")
            uiState.apply(textView, buttonIncrement, buttonDecrement)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = savedInstanceState.getSerializable(UI_KEY, UiState::class.java) as UiState
        uiState.apply(textView, buttonIncrement, buttonDecrement)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(UI_KEY, uiState)

    }

    companion object {
        const val UI_KEY  = "ui_key"
    }
}