package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.contains

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var layout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.titleTextView)
        val button = findViewById<Button>(R.id.removeButton)
        layout = findViewById<LinearLayout>(R.id.rootLayout)
        button.setOnClickListener {
            layout.removeView(textView)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY, layout.contains(textView))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getBoolean(KEY, layout.contains(textView).not())) {
            layout.removeView(textView)
        }

    }

    companion object {
        private const val KEY = "removedTextView"
    }
}