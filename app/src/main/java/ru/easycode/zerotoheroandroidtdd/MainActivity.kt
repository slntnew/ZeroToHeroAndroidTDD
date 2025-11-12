package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var text: TextView
    private lateinit var button: Button
    private var uiState: UiState = UiState.Base("0")
    private val count = Count.Base(2, 4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.countTextView)
        button = findViewById<Button>(R.id.incrementButton)
        button.setOnClickListener {
            uiState = count.increment(text.text.toString())
            uiState.apply(text, button)

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(uiStateKey, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = savedInstanceState.getSerializable(uiStateKey, UiState::class.java) as UiState
        uiState.apply(text, button)
    }

    companion object {
        private const val uiStateKey = "uiStateKey"
    }
}