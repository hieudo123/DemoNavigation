package com.example.stackexchange.navigationarchitecture.base

import android.app.Application
import com.example.stackexchange.navigationarchitecture.model.UserModel
import com.example.stackexchange.navigationarchitecture.utils.SharePref

class BaseApplication : Application() {
    private lateinit var userModel: UserModel
    override fun onCreate() {
        super.onCreate()

    }
    fun getUser():UserModel{
        return this.userModel
    }
    fun setUser(user: UserModel){
        this.userModel =  user
    }
}