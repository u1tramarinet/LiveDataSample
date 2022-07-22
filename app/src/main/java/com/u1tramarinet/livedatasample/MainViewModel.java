package com.u1tramarinet.livedatasample;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    @NonNull
    private final MutableLiveData<DigitState> thirdDigitStateData;
    @NonNull
    private final MutableLiveData<DigitState> secondDigitStateData;
    @NonNull
    private final MutableLiveData<DigitState> firstDigitStateData;
    @NonNull
    private final MediatorLiveData<Integer> resultData = new MediatorLiveData<>();
    @NonNull
    private final NonNullSupplier<DigitState> defaultDigitStateSupplier = () -> new DigitState(0);

    public MainViewModel() {
        thirdDigitStateData = new MutableLiveData<>(defaultDigitStateSupplier.get());
        secondDigitStateData = new MutableLiveData<>(defaultDigitStateSupplier.get());
        firstDigitStateData = new MutableLiveData<>(defaultDigitStateSupplier.get());
        Observer<DigitState> resultObserver = value -> updateResult();
        resultData.addSource(thirdDigitStateData, resultObserver);
        resultData.addSource(secondDigitStateData, resultObserver);
        resultData.addSource(firstDigitStateData, resultObserver);
    }

    @NonNull
    public LiveData<DigitState> thirdDigitState() {
        return thirdDigitStateData;
    }

    @NonNull
    public LiveData<DigitState> secondDigitState() {
        return secondDigitStateData;
    }

    @NonNull
    public LiveData<DigitState> firstDigitState() {
        return firstDigitStateData;
    }

    @NonNull
    public LiveData<Integer> result() {
        return resultData;
    }

    public void countUpThirdDigit() {
        countUp(thirdDigitStateData);
    }

    public void countDownThirdDigit() {
        countDown(thirdDigitStateData);
    }

    public void countUpSecondDigit() {
        countUp(secondDigitStateData);
    }

    public void countDownSecondDigit() {
        countDown(secondDigitStateData);
    }

    public void countUpFirstDigit() {
        countUp(firstDigitStateData);
    }

    public void countDownFirstDigit() {
        countDown(firstDigitStateData);
    }

    private void updateResult() {
        int third = getValue(thirdDigitStateData, defaultDigitStateSupplier).getValue();
        int second = getValue(secondDigitStateData,defaultDigitStateSupplier).getValue();
        int first = getValue(firstDigitStateData, defaultDigitStateSupplier).getValue();

        int result = 0;
        if (third != 0) {
            result += third * 100;
        }
        if (second != 0) {
            result += second * 10;
        }
        if (first != 0) {
            result += first;
        }
        resultData.setValue(result);
    }

    private void countUp(@NonNull MutableLiveData<DigitState> liveData) {
        DigitState digit = getValue(liveData, defaultDigitStateSupplier);
        if (digit.countUp()) {
            liveData.setValue(digit);
        }
    }

    private void countDown(@NonNull MutableLiveData<DigitState> liveData) {
        DigitState digit = getValue(liveData, defaultDigitStateSupplier);
        if (digit.countDown()) {
            liveData.setValue(digit);
        }
    }

    @NonNull
    private <T> T getValue(@NonNull LiveData<T> liveData, @NonNull NonNullSupplier<T> defaultValue) {
        T value = liveData.getValue();
        return (value != null) ? value : defaultValue.get();
    }
}
