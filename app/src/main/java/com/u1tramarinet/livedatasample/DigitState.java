package com.u1tramarinet.livedatasample;

public class DigitState {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 9;
    private int value;

    public DigitState(int initialValue) {
        this.value = initialValue;
    }

    public final boolean countUp() {
        if (isCountUpEnabled()) {
            value++;
            return true;
        }
        return false;
    }

    public final boolean countDown() {
        if (isCountDownEnabled()) {
            value--;
            return true;
        }
        return false;
    }

    public final int getValue() {
        return value;
    }

    public final boolean isCountUpEnabled() {
        return (value < MAX_VALUE);
    }

    public final boolean isCountDownEnabled() {
        return (value > MIN_VALUE);
    }
}
