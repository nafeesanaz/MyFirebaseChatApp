package com.example.myfirebasechatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()




    }

    private fun registerUser(userName:String, email:String, password:String){
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
                                val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }
    }
}