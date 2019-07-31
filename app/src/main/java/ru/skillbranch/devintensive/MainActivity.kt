package ru.skillbranch.devintensive

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.extensions.isKeyboardClosed
import ru.skillbranch.devintensive.extensions.isKeyboardOpen
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView

    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS")?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION")?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val (r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)

        messageEt.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                sendAnswer()
                true
            } else {
                false
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_send -> {
                if (validateAnswer())
                    sendAnswer()
                else
                    makeErrorMessage()
            }
        }
    }

    private fun makeErrorMessage() {
        val errorMessage = when(benderObj.question){
            Bender.Question.NAME -> "Имя должно начинаться с заглавной буквы"
            Bender.Question.PROFESSION -> "Профессия должна начинаться со строчной буквы"
            Bender.Question.MATERIAL -> "Материал не должен содержать цифр"
            Bender.Question.BDAY -> "Год моего рождения должен содержать только цифры"
            Bender.Question.SERIAL -> "Серийный номер содержит только цифры, и их 7"
            else -> "На этом все, вопросов больше нет"
        }
        textTxt.text = "$errorMessage\n${benderObj.question.question}"
        messageEt.setText("")

    }

    private fun validateAnswer(): Boolean {
        return benderObj.question.validate(messageEt.text.toString())
    }

    private fun sendAnswer() {
        val (phrase, color) = benderObj
            .listenAnswer(messageEt.text.toString().toLowerCase())

        val (r,g,b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = phrase

        hideKeyboard()

        Toast.makeText(applicationContext, "Open? ${isKeyboardClosed()}", Toast.LENGTH_LONG).show()
    }

}