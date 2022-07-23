package com.u1tramarinet.livedatasample

import androidx.lifecycle.*

class MainViewModel : ViewModel() {
    private val thirdDigitStateData: MutableLiveData<DigitState>
    private val secondDigitStateData: MutableLiveData<DigitState>
    private val firstDigitStateData: MutableLiveData<DigitState>
    private val resultData = MediatorLiveData<Int?>()
    private val defaultDigitStateSupplier = object : NonNullSupplier<DigitState> {
        override fun get(): DigitState {
            return DigitState(0)
        }
    }

    init {
        thirdDigitStateData = MutableLiveData(defaultDigitStateSupplier.get())
        secondDigitStateData = MutableLiveData(defaultDigitStateSupplier.get())
        firstDigitStateData = MutableLiveData(defaultDigitStateSupplier.get())
        val resultObserver = Observer { _: DigitState? -> updateResult() }
        resultData.addSource(thirdDigitStateData, resultObserver)
        resultData.addSource(secondDigitStateData, resultObserver)
        resultData.addSource(firstDigitStateData, resultObserver)
    }

    fun thirdDigitState(): LiveData<DigitState> {
        return thirdDigitStateData
    }

    fun secondDigitState(): LiveData<DigitState> {
        return secondDigitStateData
    }

    fun firstDigitState(): LiveData<DigitState> {
        return firstDigitStateData
    }

    fun result(): LiveData<Int?> {
        return resultData
    }

    fun countUpThirdDigit() {
        countUp(thirdDigitStateData)
    }

    fun countDownThirdDigit() {
        countDown(thirdDigitStateData)
    }

    fun countUpSecondDigit() {
        countUp(secondDigitStateData)
    }

    fun countDownSecondDigit() {
        countDown(secondDigitStateData)
    }

    fun countUpFirstDigit() {
        countUp(firstDigitStateData)
    }

    fun countDownFirstDigit() {
        countDown(firstDigitStateData)
    }

    private fun updateResult() {
        val third = getValue(thirdDigitStateData, defaultDigitStateSupplier).value
        val second = getValue(secondDigitStateData, defaultDigitStateSupplier).value
        val first = getValue(firstDigitStateData, defaultDigitStateSupplier).value
        var result = 0
        if (third != 0) {
            result += third * 100
        }
        if (second != 0) {
            result += second * 10
        }
        if (first != 0) {
            result += first
        }
        resultData.value = result
    }

    private fun countUp(liveData: MutableLiveData<DigitState>) {
        val digit = getValue(liveData, defaultDigitStateSupplier)
        if (digit.countUp()) {
            liveData.value = digit
        }
    }

    private fun countDown(liveData: MutableLiveData<DigitState>) {
        val digit = getValue(liveData, defaultDigitStateSupplier)
        if (digit.countDown()) {
            liveData.value = digit
        }
    }

    private fun <T> getValue(liveData: LiveData<T>, defaultValue: NonNullSupplier<T>): T {
        val value = liveData.value
        return value ?: defaultValue.get()
    }
}