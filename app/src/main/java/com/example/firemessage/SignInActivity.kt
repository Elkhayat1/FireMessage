package com.example.firemessage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.longSnackbar

class SignInActivity : AppCompatActivity() {
    private lateinit var mAuth : FirebaseAuth
    private lateinit var refUsers : DatabaseReference
    private var firebaseUserID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mAuth = FirebaseAuth.getInstance()

        btn_account_sign_in.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val email = et_email.text.toString().trim()
        val password = et_password.text.toString().trim()
        val userName = et_user_name.text.toString().trim()
        when {
            userName == "" -> {
                Toast.makeText(this, "Please enter a Username", Toast.LENGTH_LONG).show()
                false
            }
            email == "" -> {
                Toast.makeText(this, "Please enter an Email", Toast.LENGTH_LONG).show()
                false
            }
            password == "" -> {
                Toast.makeText(this, "Please enter a Password", Toast.LENGTH_LONG).show()
                false
            }
            else -> {
                mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            firebaseUserID = mAuth.currentUser!!.uid
                            refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)

                            val userHashMap = HashMap<String, Any>()
                            userHashMap["uid"]=firebaseUserID
                            userHashMap["username"]= userName
                            userHashMap["status"]= "offline"
                            userHashMap["facebook"]= "http://m.facebook.com"
                            userHashMap["website"]= "http://m.google.com"
                            refUsers.updateChildren(userHashMap).addOnCompleteListener { task ->
                                if(task.isSuccessful){
                                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                    finish()
                                }
                            }

                        }else{
                            Toast.makeText(this@SignInActivity, "Error message: ${task.exception?.message.toString()}", Toast.LENGTH_LONG).show()

                        }

                }
            }
        }
    }

}