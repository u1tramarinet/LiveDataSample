package com.u1tramarinet.livedatasample

class DigitState(var value: Int) {
    fun countUp(): Boolean {
        if (isCountUpEnabled) {
            value++
            return true
        }
        return false
    }

    fun countDown(): Boolean {
        if (isCountDownEnabled) {
            value--
            return true
        }
        return false
    }

    val isCountUpEnabled: Boolean
        get() = value < MAX_VALUE
    val isCountDownEnabled: Boolean
        get() = value > MIN_VALUE

    companion object {
        private const val MIN_VALUE = 0
        private const val MAX_VALUE = 9
    }
}