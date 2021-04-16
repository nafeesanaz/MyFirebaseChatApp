package com.example.firebaseapp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseapp.R
import com.example.firebaseapp.adapter.UserAdapter
import com.example.firebaseapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.activity_users.userRecyclerView as userRecyclerView


class UsersActivity : AppCompatActivity() {
    var userList = ArrayList<User>()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        userRecyclerView.layoutManager = LinearLayoutManager( this, LinearLayout.VERTICAL, false)

        imgBack.setOnClickListener{
            onBackPressed()
        }
        getUsersList()

    }

    fun getUsersList() {
        var firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,error.message,Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()

                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val user = dataSnapShot.getValue(User::class.java)
                    if (!user!!.userId.equals(firebase.uid)) {
                        userList.add(user)
                    }
                }
                val userAdapter = UserAdapter(this@UsersActivity, userList)

                userRecyclerView.adapter = userAdapter
            }
        })
    }}


