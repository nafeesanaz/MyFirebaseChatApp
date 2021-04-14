package com.example.myfirebasechatapp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirebasechatapp.R
import com.example.myfirebasechatapp.adapter.UserAdapter
import com.example.myfirebasechatapp.model.User
import kotlinx.android.synthetic.main.activity_users.*


class UsersActivity : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        userRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val userList = ArrayList<User>()

        userList.add(User("Naf", "https://unsplash.com/photos/pDGNBK9A0sk"))
        userList.add(User("Naf", "https://unsplash.com/photos/pDGNBK9A0sk"))
        userList.add(User("Naf", "https://unsplash.com/photos/pDGNBK9A0sk"))
        userList.add(User("Naf", "https://unsplash.com/photos/pDGNBK9A0sk"))
        userList.add(User("Naf", "https://unsplash.com/photos/pDGNBK9A0sk"))
        userList.add(User("Naf", "https://unsplash.com/photos/pDGNBK9A0sk"))
        userList.add(User("Naf", "https://unsplash.com/photos/pDGNBK9A0sk"))

        val userAdapter = UserAdapter( this, userList)

        userRecyclerView.adapter = userAdapter
    }
}