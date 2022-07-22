package com.u1tramarinet.livedatasample;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.function.Consumer;

public class MainFragment extends Fragment {
    private MainViewModel model;
    private View rootView;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        model = new ViewModelProvider(this).get(MainViewModel.class);

        TextView result = view.findViewById(R.id.result);
        model.result().observe(getViewLifecycleOwner(), value -> result.setText(String.valueOf(value)));
        model.firstDigitState().observe(getViewLifecycleOwner(), digitState -> updateDigit(digitState, R.id.first_digit_up, R.id.first_digit, R.id.first_digit_down));
        model.secondDigitState().observe(getViewLifecycleOwner(), digitState -> updateDigit(digitState, R.id.second_digit_up, R.id.second_digit, R.id.second_digit_down));
        model.thirdDigitState().observe(getViewLifecycleOwner(), digitState -> updateDigit(digitState, R.id.third_digit_up, R.id.third_digit, R.id.third_digit_down));
        initializeUpDownButtonEvent(view, R.id.first_digit_up, (unused) -> model.countUpFirstDigit());
        initializeUpDownButtonEvent(view, R.id.first_digit_down, (unused) -> model.countDownFirstDigit());
        initializeUpDownButtonEvent(view, R.id.second_digit_up, (unused) -> model.countUpSecondDigit());
        initializeUpDownButtonEvent(view, R.id.second_digit_down, (unused) -> model.countDownSecondDigit());
        initializeUpDownButtonEvent(view, R.id.third_digit_up, (unused) -> model.countUpThirdDigit());
        initializeUpDownButtonEvent(view, R.id.third_digit_down, (unused) -> model.countDownThirdDigit());
    }

    private void updateDigit(@NonNull DigitState digitState, @IdRes int upButtonRes, @IdRes int digitRes, @IdRes int downButtonRes) {
        if (rootView == null) {
            return;
        }
        TextView digit = rootView.findViewById(digitRes);
        digit.setText(String.valueOf(digitState.getValue()));
        ImageButton upButton = rootView.findViewById(upButtonRes);
        upButton.setEnabled(digitState.isCountUpEnabled());
        ImageButton downButton = rootView.findViewById(downButtonRes);
        downButton.setEnabled(digitState.isCountDownEnabled());
    }

    private void initializeUpDownButtonEvent(@NonNull View root, @IdRes int upDownButtonRes, @NonNull Consumer<Void> clickEvent) {
        ImageButton upDownButton = root.findViewById(upDownButtonRes);
        upDownButton.setOnClickListener(view -> clickEvent.accept(null));
    }
}