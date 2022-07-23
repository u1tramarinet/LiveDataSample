package com.u1tramarinet.livedatasample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.util.function.Consumer

class MainFragment : Fragment() {
    private var model: MainViewModel? = null
    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view
        model = ViewModelProvider(this).get(MainViewModel::class.java)
        val result = view.findViewById<TextView>(R.id.result)
        model!!.result().observe(viewLifecycleOwner) { value: Int? -> result.text = value.toString() }
        model!!.firstDigitState().observe(viewLifecycleOwner) { digitState: DigitState -> updateDigit(digitState, R.id.first_digit_up, R.id.first_digit, R.id.first_digit_down) }
        model!!.secondDigitState().observe(viewLifecycleOwner) { digitState: DigitState -> updateDigit(digitState, R.id.second_digit_up, R.id.second_digit, R.id.second_digit_down) }
        model!!.thirdDigitState().observe(viewLifecycleOwner) { digitState: DigitState -> updateDigit(digitState, R.id.third_digit_up, R.id.third_digit, R.id.third_digit_down) }
        initializeUpDownButtonEvent(view, R.id.first_digit_up) { model!!.countUpFirstDigit() }
        initializeUpDownButtonEvent(view, R.id.first_digit_down) { model!!.countDownFirstDigit() }
        initializeUpDownButtonEvent(view, R.id.second_digit_up) { model!!.countUpSecondDigit() }
        initializeUpDownButtonEvent(view, R.id.second_digit_down) { model!!.countDownSecondDigit() }
        initializeUpDownButtonEvent(view, R.id.third_digit_up) { model!!.countUpThirdDigit() }
        initializeUpDownButtonEvent(view, R.id.third_digit_down) { model!!.countDownThirdDigit() }
    }

    private fun updateDigit(digitState: DigitState, @IdRes upButtonRes: Int, @IdRes digitRes: Int, @IdRes downButtonRes: Int) {
        val digit = rootView!!.findViewById<TextView>(digitRes)
        digit.text = digitState.value.toString()
        val upButton = rootView!!.findViewById<ImageButton>(upButtonRes)
        upButton.isEnabled = digitState.isCountUpEnabled
        val downButton = rootView!!.findViewById<ImageButton>(downButtonRes)
        downButton.isEnabled = digitState.isCountDownEnabled
    }

    private fun initializeUpDownButtonEvent(root: View, @IdRes upDownButtonRes: Int, clickEvent: Consumer<Void?>) {
        val upDownButton = root.findViewById<ImageButton>(upDownButtonRes)
        upDownButton.setOnClickListener { clickEvent.accept(null) }
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}