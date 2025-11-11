package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

interface State : Serializable {
    fun apply(layout: LinearLayout, textView: TextView, button: Button)

    object Init: State {
        private fun readResolve(): Any = Init
        override fun apply(layout: LinearLayout, textView: TextView, button: Button) = Unit

    }
    object Removed: State {
        private fun readResolve(): Any = Removed
        override fun apply(layout: LinearLayout, textView: TextView, button: Button) {
            layout.removeView(textView)
            button.isEnabled = false
        }
    }
}