package com.u1tramarinet.livedatasample

import org.junit.Assert
import org.junit.Test

class DigitStateTest {
    private val maxLimitValue = 9
    private val minLimitValue = 0
    @Test
    fun testDefaultValue() {
        var digitState = DigitState(minLimitValue)
        Assert.assertEquals(digitState.value.toLong(), minLimitValue.toLong())
        digitState = DigitState(minLimitValue + 1)
        Assert.assertEquals(digitState.value.toLong(), (minLimitValue + 1).toLong())
    }

    @Test
    fun testCountUp() {
        var digitState = DigitState(minLimitValue)
        Assert.assertTrue(digitState.countUp())
        Assert.assertEquals(digitState.value.toLong(), (minLimitValue + 1).toLong())
        digitState = DigitState(maxLimitValue)
        Assert.assertFalse(digitState.countUp())
        Assert.assertEquals(digitState.value.toLong(), maxLimitValue.toLong())
    }

    @Test
    fun testCountDown() {
        var digitState = DigitState(maxLimitValue)
        Assert.assertTrue(digitState.countDown())
        Assert.assertEquals(digitState.value.toLong(), (maxLimitValue - 1).toLong())
        digitState = DigitState(minLimitValue)
        Assert.assertFalse(digitState.countDown())
        Assert.assertEquals(digitState.value.toLong(), minLimitValue.toLong())
    }

    @Test
    fun testIsCountUpEnabled() {
        var digitState = DigitState(minLimitValue)
        Assert.assertTrue(digitState.isCountUpEnabled)
        digitState = DigitState(maxLimitValue - 1)
        Assert.assertTrue(digitState.isCountUpEnabled)
        digitState = DigitState(maxLimitValue)
        Assert.assertFalse(digitState.isCountUpEnabled)
    }

    @Test
    fun testIsCountDownEnabled() {
        var digitState = DigitState(maxLimitValue)
        Assert.assertTrue(digitState.isCountDownEnabled)
        digitState = DigitState(minLimitValue + 1)
        Assert.assertTrue(digitState.isCountDownEnabled)
        digitState = DigitState(minLimitValue)
        Assert.assertFalse(digitState.isCountDownEnabled)
    }
}