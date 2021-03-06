package com.example.androidhomework7

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomework7.api.dto.RestClient
import com.example.androidhomework7.api.dto.request.UserRequest
import com.example.androidhomework7.api.dto.response.CreateUserResponse
import com.example.androidhomework7.api.dto.response.ListUsers
import com.example.androidhomework7.api.dto.response.UpdateUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RestClient.initClient()

        val name = findViewById<EditText>(R.id.name)
        val job = findViewById<EditText>(R.id.job)
        val id = findViewById<EditText>(R.id.id)

        val listUsersBtn = findViewById<Button>(R.id.listUsers)
        val createUserBtn = findViewById<Button>(R.id.createUser)
        val updateUserBtn = findViewById<Button>(R.id.updateUser)
        val deleteUserBtn = findViewById<Button>(R.id.deleteUser)

        val result = findViewById<TextView>(R.id.result)

        listUsersBtn.setOnClickListener {

            RestClient.reqResService.getUsers(2).enqueue(object : Callback<ListUsers> {
                override fun onResponse(
                    call: Call<ListUsers>, response: Response<ListUsers>
                ) {
                    if (response.isSuccessful) {
                        Log.d("Successful", response.body().toString())
                        result.text = response.body().toString()
                    }
                }

                override fun onFailure(call: Call<ListUsers>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
        }

        createUserBtn.setOnClickListener {
            RestClient.reqResService.createUser(
                UserRequest(
                    name = name.text.toString(),
                    job = job.text.toString()
                )
            ).enqueue(object : Callback<CreateUserResponse> {
                override fun onResponse(
                    call: Call<CreateUserResponse>, response: Response<CreateUserResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("Successful", response.body().toString())
                        result.text = response.body().toString()
//                response.body()?.data?.forEach { user -> println("nika $user") }
                    }
                }

                override fun onFailure(call: Call<CreateUserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
        }

        updateUserBtn.setOnClickListener {
            RestClient.reqResService.updateUser(
                UserRequest(
                    name = name.text.toString(),
                    job = job.text.toString()
                ), id = id.text.toString().toLong()
            ).enqueue(object : Callback<UpdateUserResponse> {
                override fun onResponse(
                    call: Call<UpdateUserResponse>, response: Response<UpdateUserResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("Successful", response.body().toString())
                        result.text = response.body().toString()
//                response.body()?.data?.forEach { user -> println("nika $user") }
                    }
                }

                override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
        }

        deleteUserBtn.setOnClickListener {
            RestClient.reqResService.deleteUser(id.text.toString().toLong())
                .enqueue(object : Callback<okhttp3.ResponseBody> {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<okhttp3.ResponseBody>,
                        response: Response<okhttp3.ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("Successful", "${response.code()} :Success Deleted")
                            result.text = "${response.code()} :Success Deleted"
                        }
                    }

                    override fun onFailure(call: Call<okhttp3.ResponseBody>, t: Throwable) {
                        Log.d("Failure", t.message.toString())
                    }
                })
        }
    }
}
