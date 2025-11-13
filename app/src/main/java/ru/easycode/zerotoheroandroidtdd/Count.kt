package ru.easycode.zerotoheroandroidtdd

import kotlin.IllegalStateException
import kotlin.compareTo

interface Count {


    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    class Base(private val step: Int, private val max: Int, private val min: Int) : Count {
        init {
            if (step == 0) throw IllegalStateException("Step must be positive")
            if (step<0) throw IllegalStateException("step should be positive, but was $step")
            if (max == 0) throw IllegalStateException("Max must be positive")
            if (max < 0) throw IllegalStateException("max should be positive, but was $max")
            if (max<step) throw IllegalStateException("max should be more than step")
            if (max<min) throw IllegalStateException("max should be more than min")

        }
        override fun initial(number: String): UiState {
            val res = number.toInt()
            return if (res <= min) {
                UiState.Min(min.toString())
            } else if (res >= max) {
                UiState.Max(max.toString())
            } else {
                UiState.Base(res.toString())
            }
        }

        override fun increment(number: String): UiState {
            val res = number.toInt() + step
            return if (res < max) {
                UiState.Base(res.toString())
            } else {
                UiState.Max(max.toString())
            }
        }

        override fun decrement(number: String): UiState {
            val res = number.toInt() - step
            return if (res <= min) {
                UiState.Min(min.toString())
            } else {
                UiState.Base(res.toString())
            }
        }

    }
}
