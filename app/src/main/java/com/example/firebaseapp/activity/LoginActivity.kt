package com.example.firebaseapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.firebaseapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    //open user screen of user logged in
    private lateinit var firebaseUser: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        firebaseUser = auth.currentUser!!

        //if user logged in navigate to user screen
        if(firebaseUser != null){
            val intent = Intent(this@LoginActivity, UsersActivity::class.java)
            startActivity(intent)

            finish()

        }


        btnLogin.setOnClickListener {
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
                            val intent = Intent(this@LoginActivity, UsersActivity::class.java)

                            startActivity(intent)
                            finish()
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

                finish()
        }
    }
}