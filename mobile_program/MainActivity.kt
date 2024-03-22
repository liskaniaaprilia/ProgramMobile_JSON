package com.example.mobileprogram

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprogram.model.DataItem
import com.example.mobileprogram.model.ResponseUser
import com.example.mobileprogram.network.ApiConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var rv_users: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_users = findViewById(R.id.rv_users)
        adapter = UserAdapter(mutableListOf())

        rv_users.setHasFixedSize(true)
        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.adapter = adapter

        getUser()
    }

    private fun getUser(){
        val apiService = ApiConfig.getApiService(this@MainActivity) // perbaiki konteks (context) menjadi this@MainActivity
        val client = apiService.getListUsers("1")

        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>){
                if (response.isSuccessful){
                    val dataArray = response.body()?.data as List<DataItem>
                    for (data in dataArray){
                        adapter.addUser(data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable){
                Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_SHORT).show() // ubah t.message menjadi t.message.toString()
                t.printStackTrace()
            }
        })
    }
}
