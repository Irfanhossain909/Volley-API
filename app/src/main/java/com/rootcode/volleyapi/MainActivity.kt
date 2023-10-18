package com.rootcode.volleyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {

    val url = "https://api.github.com/users"
    var userInfoItem = arrayOf<UserInfoItem>()
    var userInfo = UserInfo()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stringRequest = StringRequest(url,
            Response.Listener {
                              val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()
                userInfoItem = gson.fromJson(it,Array<UserInfoItem>::class.java)
                userInfoItem.forEach {
                    userInfo.add(it)
                }
                Toast.makeText(this, userInfo.toString(),Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                Toast.makeText(this,"Something Worng"+it.toString(),Toast.LENGTH_SHORT).show()
            }
        )
        val volley = Volley.newRequestQueue(this)
        volley.add(stringRequest)
    }
}