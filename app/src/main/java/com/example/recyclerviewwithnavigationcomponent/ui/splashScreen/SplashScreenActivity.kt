package com.example.recyclerviewwithnavigationcomponent.ui.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewwithnavigationcomponent.databinding.ActivitySplashScreenBinding
import com.example.recyclerviewwithnavigationcomponent.ui.mvvm.MainActivity
import com.example.recyclerviewwithnavigationcomponent.ui.authenctication.AuthenticationActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    private val authViewModel by viewModels<SplashScreenViewModel> { SplashScreenViewModel.provideFactory(this, applicationContext
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




        Handler(Looper.getMainLooper()).postDelayed({
            authViewModel.loadTokenAccount.observe(this){
                Log.d("valueAuth", "valueAuth: $it")
                if (it.isNullOrEmpty()){
                    startActivity(Intent(this,AuthenticationActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        },2_000)
    }
}