package com.example.stackexchange.navigationarchitecture.repository

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.example.stackexchange.navigationarchitecture.model.UserModel
import com.example.stackexchange.navigationarchitecture.utils.SharePref

class UserRepository(activity: Activity) {
    var sharePref : SharePref? = null
    val userLiveData = MutableLiveData<UserModel>()
    init {
        sharePref = SharePref(activity)
    }
    fun getUser():MutableLiveData<UserModel>{
        if(sharePref!!.user != null)
            this.userLiveData.value = sharePref!!.user
        return userLiveData
    }
    fun createUser(userModel: UserModel){
        sharePref!!.putUser(userModel)
    }
}