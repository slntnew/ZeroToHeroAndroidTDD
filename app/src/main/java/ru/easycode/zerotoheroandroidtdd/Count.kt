package ru.easycode.zerotoheroandroidtdd

import kotlin.IllegalStateException

interface Count {

    fun increment(number: String): String
    class Base(private val step: Int) : Count {
        init {
            if (step < 1) {
                throw IllegalStateException("step should be positive, but was -2")
            }
        }

        override fun increment(number: String): String {
           return (number.toInt()+step).toString()
        }
    }
}