package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var text: TextView
    private lateinit var button: Button
    private lateinit var layout: LinearLayout
    private var state: State = State.Init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.titleTextView)
        button = findViewById(R.id.removeButton)
        layout = findViewById(R.id.rootLayout)
        button.setOnClickListener {
            state = State.Removed
            state.apply(layout, text, button)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state =  savedInstanceState.getSerializable(KEY, State::class.java) as State
        state.apply(layout, text, button)
    }

    companion object {
        const val KEY = "key"
    }


}