package com.example.recyclerviewwithnavigationcomponent.data

import androidx.lifecycle.LiveData
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.UserProfileData
import com.example.recyclerviewwithnavigationcomponent.data.repository.LocalLoginDataSource
import com.example.recyclerviewwithnavigationcomponent.data.repository.RemoteLoginDataSource
import com.example.recyclerviewwithnavigationcomponent.domain.LoginRepository

class LoginDataSource(private val remoteLoginDataSource: RemoteLoginDataSource, private val localLoginDataSource: LocalLoginDataSource):LoginRepository {
    /*override suspend fun login(email: String, password: String): String {
      return  remoteLoginDataSource.login(email,password)
    }

    override fun saveToken(dataToken: String) {
        localLoginDataSource.saveToken(dataToken)
    }

    override fun loadToken(): String? {
        return localLoginDataSource.loadToken()
    }

    override fun clearToken() {
        return localLoginDataSource.clearToken()
    }*/
    override suspend fun createAccount(username: String, email: String, password: String) {
        remoteLoginDataSource.createAccount(username,email,password)
    }

    override suspend fun loadDataAccount(
        usernameOrEmailInput: String,
        passwordInput: String
    ) {
        remoteLoginDataSource.loadDataAccount(usernameOrEmailInput,passwordInput)
    }

    override suspend fun loadToken(): String? {
        return localLoginDataSource.loadAccountToken()
    }


    override suspend fun clearDataAccount() {
        localLoginDataSource.clearAccount()
    }

    override suspend fun getAllDataUserProfile(): UserProfileData {
       return localLoginDataSource.getAllDataUserProfile()
    }

    override suspend fun updateUserProfile(username: String, email: String, password: String) {
        localLoginDataSource.updateDataUserProfile(username,email,password)
    }

}