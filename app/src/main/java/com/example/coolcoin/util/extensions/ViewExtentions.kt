package com.example.coolcoin.util.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(requireView().windowToken, 0)
}

fun Fragment.toast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
}

fun TextView.clearText() {
    text = ""
}

fun TextView.text() = text.trim().toString()

fun Fragment.compactColor(@ColorRes resId: Int) = ContextCompat.getColor(
    requireContext(),
    resId
)

fun Fragment.compactDrawable(@DrawableRes resId: Int) = ContextCompat.getDrawable(
    requireContext(),
    resId
)
