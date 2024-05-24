package com.example.recyclerviewwithnavigationcomponent.data.repository

interface RemoteLoginDataSource {
    //suspend fun login(email:String, password:String):String
    suspend fun createAccount(usernameInput:String, emailInput:String,passwordInput:String)
    suspend fun loadDataAccount(usernameOrEmailInput:String, passwordInput:String)

}