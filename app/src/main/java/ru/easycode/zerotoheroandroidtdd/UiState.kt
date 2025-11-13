package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun apply(textView: TextView, buttonIncrement: Button, buttonDencrement: Button)
    data class Base(val text: String): UiState {
        override fun apply(
            textView: TextView,
            buttonIncrement: Button,
            buttonDencrement: Button
        ) {
            buttonDencrement.isEnabled = true
            buttonIncrement.isEnabled = true
            textView.text = text
        }
    }

    data class Min(val text: String) : UiState {
        override fun apply(
            textView: TextView,
            buttonIncrement: Button,
            buttonDencrement: Button
        ) {
            buttonDencrement.isEnabled = false
            buttonIncrement.isEnabled = true
            textView.text = text
        }
    }

    data class Max(val text: String): UiState {
        override fun apply(
            textView: TextView,
            buttonIncrement: Button,
            buttonDencrement: Button
        ) {
            buttonDencrement.isEnabled = true
            buttonIncrement.isEnabled = false
            textView.text = text
        }
    }

}