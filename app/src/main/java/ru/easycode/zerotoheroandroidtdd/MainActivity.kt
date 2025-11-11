package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.contains
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var layout: LinearLayout
    private var state: State = State.Initial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.titleTextView)
        val button = findViewById<Button>(R.id.removeButton)
        layout = findViewById<LinearLayout>(R.id.rootLayout)
        button.setOnClickListener {
            state = State.Removed
            state.apply(layout, textView)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = savedInstanceState.getSerializable(KEY, State::class.java) as State
        state.apply(layout, textView)

    }

    interface State: Serializable {
        fun apply(linerLayout: LinearLayout, textView: TextView)

        object Initial: State {
            override fun apply(linerLayout: LinearLayout, textView: TextView) = Unit
        }

        object Removed: State {
            override fun apply(linerLayout: LinearLayout, textView: TextView) {
                linerLayout.removeView(textView)
            }
        }
    }

    companion object {
        private const val KEY = "removedTextView"
    }
}