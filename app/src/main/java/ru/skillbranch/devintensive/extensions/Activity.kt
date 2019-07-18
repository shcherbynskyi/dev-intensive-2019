package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.hideKeyboard() {
    Toast.makeText(applicationContext, "Hide Keyboard", Toast.LENGTH_SHORT).show()
}