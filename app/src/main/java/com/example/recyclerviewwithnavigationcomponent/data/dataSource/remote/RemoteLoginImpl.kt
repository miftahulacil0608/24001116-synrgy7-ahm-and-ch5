package com.example.recyclerviewwithnavigationcomponent.data.dataSource.remote

import com.example.recyclerviewwithnavigationcomponent.data.model.AuthPreferences
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.User
import com.example.recyclerviewwithnavigationcomponent.data.repository.RemoteLoginDataSource

class RemoteLoginImpl(private val dataStore: AuthPreferences) : RemoteLoginDataSource {
    private val dataUser = listOf(
        User("synergy7chapter4@gmail.com", "synergy7chapter4"),
        User("chapter4@gmail.com", "chapter4")
    )

    override suspend fun createAccount(
        usernameInput: String,
        emailInput: String,
        passwordInput: String
    ) {
        dataStore.createAndUpdateAccount(usernameInput, emailInput, passwordInput)
    }

    override suspend fun loadDataAccount(
        usernameOrEmailInput: String,
        passwordInput: String
    ) {
        return dataStore.loadDataAccount(usernameOrEmailInput, passwordInput)
    }

    /*override suspend fun login(email: String, password: String): String {
        if (dataUser.contains(User(email,password))){
            return "tokenLogin123"
        }else{
            throw IllegalAccessException("User Empty")
        }
    }*/
}