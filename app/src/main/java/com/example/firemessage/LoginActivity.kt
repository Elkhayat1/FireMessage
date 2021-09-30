package com.example.firemessage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth :FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        btn_login.setOnClickListener {
            loginUser()
        }
    }



    private fun loginUser() {
        val email = et_login_email.text.toString().trim()
        val password = et_login_password.text.toString().trim()
        when {
            email == "" -> {
                Toast.makeText(this, "Please enter an Email", Toast.LENGTH_LONG).show()
            }
            password == "" -> {
                Toast.makeText(this, "Please enter a Password", Toast.LENGTH_LONG).show()
            }else -> {
                mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this@LoginActivity, "Error message: ${it.exception?.message.toString()}", Toast.LENGTH_LONG).show()

                        }
                    }

        }            }
    }


}