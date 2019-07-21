package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Activity.hideKeyboard() {
    val im = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    im.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
}

fun Activity.isKeyboardOpen() {
    val im = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

}

fun Activity.isKeyboardClosed() {

    val r = Rect()
    this.window.decorView.getWindowVisibleDisplayFrame(r)

    val screenHeight = this.window.decorView.height

    val keypadHeight = screenHeight - r.bottom

    if (keypadHeight > screenHeight * 0.15) {
        Toast.makeText(applicationContext, "Keyboard is opened", Toast.LENGTH_LONG).show()
    }
    else {
        Toast.makeText(applicationContext, "Keyboard is closed", Toast.LENGTH_LONG).show()
    }

}

/*
* Реализуй extension для проверки, открыта или нет Software Keyboard с применением метода rootView.getWindowVisibleDisplayFrame(Rect())
*
* */