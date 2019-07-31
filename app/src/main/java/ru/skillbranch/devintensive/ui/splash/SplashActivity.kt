package ru.skillbranch.devintensive.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.ui.profile.ProfileActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, ProfileActivity::class.java))
            finish()
        }, 1500)
    }

}