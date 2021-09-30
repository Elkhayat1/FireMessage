package com.example.firemessage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)*/
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if(FirebaseAuth.getInstance().currentUser == null)

            startActivity<SignInActivity>()
        else
            startActivity<MainActivity>()

        finish()

    }
}