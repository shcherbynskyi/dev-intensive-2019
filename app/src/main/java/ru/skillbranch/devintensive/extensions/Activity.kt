package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val im = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    im.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    val r = Rect()
    this.window.decorView.getWindowVisibleDisplayFrame(r)
    val screenHeight = this.window.decorView.height
    val keypadHeight = screenHeight - r.bottom
    return keypadHeight > screenHeight * 0.15
}

fun Activity.isKeyboardClosed(): Boolean {
    return this.isKeyboardOpen().not()
}