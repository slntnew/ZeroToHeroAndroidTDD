package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable
import kotlin.IllegalStateException

interface Count {

    fun increment(number: String): UiState

    class Base(private val step: Int, private val max: Int) : Count {
        init {
            if (step == 0) throw IllegalStateException("step can't be zero")
            if (step < 0) throw IllegalStateException("step should be positive, but was $step")
            if (max == 0) throw IllegalStateException("Max can't be zero")
            if (max < 0) throw IllegalStateException("max should be positive, but was $max")
            if (step > max) throw IllegalStateException("max should be more than step")
        }

        override fun increment(number: String): UiState {
            val res = number.toInt() + step
            return if (res+step <=  max) {
                UiState.Base(res.toString())
            } else {
                UiState.Max(res.toString())
            }
        }

    }
}

interface UiState: Serializable {
    fun apply(textView: TextView, button: Button)


    data class Base(private val text: String) : UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
        }

    }

    data class Max(private val text: String) : UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
            button.isEnabled = false
        }

    }
}