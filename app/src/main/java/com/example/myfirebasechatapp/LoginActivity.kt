package com.example.myfirebasechatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener{
            val email=etEmail.text.toString()
            val password = etPassword.text.toString()

            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                Toast.makeText(
                    applicationContext,
                    "email and password required",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){
                        if(it.isSuccessful) {
                            etEmail.setText("")
                            etPassword.setText("")
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                        }

                        else {
                            Toast.makeText(
                                applicationContext,
                                "email or password invalid",
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
        }

        btnSignUp.setOnClickListener{
            val intent = Intent (
                this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
        }
    }
}