package com.u1tramarinet.livedatasample;

import org.junit.Assert;
import org.junit.Test;

public class DigitStateTest {

    private final int MAX_LIMIT = 9;
    private final int MIN_LIMIT = 0;

    @Test
    public void testDefaultValue() {
        DigitState digitState = new DigitState(MIN_LIMIT);
        Assert.assertEquals(digitState.getValue(), MIN_LIMIT);
        digitState = new DigitState(MIN_LIMIT + 1);
        Assert.assertEquals(digitState.getValue(), MIN_LIMIT + 1);
    }

    @Test
    public void testCountUp() {
        DigitState digitState = new DigitState(MIN_LIMIT);
        Assert.assertTrue(digitState.countUp());
        Assert.assertEquals(digitState.getValue(), MIN_LIMIT + 1);

        digitState = new DigitState(MAX_LIMIT);
        Assert.assertFalse(digitState.countUp());
        Assert.assertEquals(digitState.getValue(), MAX_LIMIT);
    }

    @Test
    public void testCountDown() {
        DigitState digitState = new DigitState(MAX_LIMIT);
        Assert.assertTrue(digitState.countDown());
        Assert.assertEquals(digitState.getValue(), MAX_LIMIT - 1);

        digitState = new DigitState(MIN_LIMIT);
        Assert.assertFalse(digitState.countDown());
        Assert.assertEquals(digitState.getValue(), MIN_LIMIT);
    }

    @Test
    public void testIsCountUpEnabled() {
        DigitState digitState = new DigitState(MIN_LIMIT);
        Assert.assertTrue(digitState.isCountUpEnabled());
        digitState = new DigitState(MAX_LIMIT - 1);
        Assert.assertTrue(digitState.isCountUpEnabled());
        digitState = new DigitState(MAX_LIMIT);
        Assert.assertFalse(digitState.isCountUpEnabled());
    }

    @Test
    public void testIsCountDownEnabled() {
        DigitState digitState = new DigitState(MAX_LIMIT);
        Assert.assertTrue(digitState.isCountDownEnabled());
        digitState = new DigitState(MIN_LIMIT + 1);
        Assert.assertTrue(digitState.isCountDownEnabled());
        digitState = new DigitState(MIN_LIMIT);
        Assert.assertFalse(digitState.isCountDownEnabled());
    }
}
