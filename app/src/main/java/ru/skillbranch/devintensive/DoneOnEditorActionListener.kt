package ru.skillbranch.devintensive

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView

class DoneOnEditorActionListener : TextView.OnEditorActionListener {
    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {



            return true
        }
        return false
    }
}
