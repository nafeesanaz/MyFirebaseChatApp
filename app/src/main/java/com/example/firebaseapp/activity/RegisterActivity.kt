package com.example.firebaseapp.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        btnSignUp.setOnClickListener {
            val userName = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val ConfirmPassword = etConfirmPassword.text.toString()

            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(applicationContext, "username is required", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "email is required", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "password is required", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(ConfirmPassword)) {
                Toast.makeText(applicationContext, "Confirm password is required", Toast.LENGTH_SHORT).show()
            }

            if (password != ConfirmPassword) {
                Toast.makeText(applicationContext, "Password does not match", Toast.LENGTH_SHORT).show()
            }

        registerUser(userName, email, password)

        }

        btnLogin.setOnClickListener{
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

        fun registerUser(userName:String, email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){
                    if (it.isSuccessful){
                    val user: FirebaseUser? = auth.currentUser
                        val userId: String = user!!.uid
                        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)

                        val hashMap: HashMap < String, String> = HashMap()
                        hashMap.put("userId", userId)
                        hashMap.put("userName",userName)
                        hashMap.put("profileImage","")

                        databaseReference.setValue(hashMap).addOnCompleteListener(this){
                            if (it.isSuccessful){
                                //open home activity
                                etName.setText("")
                                etEmail.setText("")
                                etPassword.setText("")
                                etConfirmPassword.setText("")

                                val intent = Intent(this@RegisterActivity,
                                    UsersActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                }
    }
}