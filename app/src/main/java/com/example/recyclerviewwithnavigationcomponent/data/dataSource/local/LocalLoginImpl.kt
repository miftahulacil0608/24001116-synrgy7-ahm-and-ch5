package com.example.recyclerviewwithnavigationcomponent.data.dataSource.local

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.example.recyclerviewwithnavigationcomponent.data.model.AuthPreferences
import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.UserProfileData
import com.example.recyclerviewwithnavigationcomponent.data.model.toLiveData
import com.example.recyclerviewwithnavigationcomponent.data.repository.LocalLoginDataSource

class LocalLoginImpl(/*private val sharedPreferences: SharedPreferences?*/private val dataStore: AuthPreferences) :
    LocalLoginDataSource {

    /*companion object{
        const val KEY_TOKEN = "KEY TOKEN"
    }

    override fun saveToken(dataToken: String) {
        sharedPreferences?.edit()?.putString(KEY_TOKEN,dataToken)?.apply()
    }

    override fun loadToken(): String? {
        return sharedPreferences?.getString(KEY_TOKEN,null)
    }

    override fun clearToken() {
        sharedPreferences?.edit()?.remove(KEY_TOKEN)?.apply()
    }*/

    override suspend fun loadAccountToken(): String? {
        return dataStore.loadToken()
    }

    override suspend fun clearAccount() {
        dataStore.clearDataAccount()
    }

    override suspend fun getAllDataUserProfile(): UserProfileData {
        return dataStore.getAllData()
    }

    override suspend fun updateDataUserProfile(username: String, email: String, password: String) {
        dataStore.createAndUpdateAccount(username, email, password)
    }

}