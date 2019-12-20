package com.example.stackexchange.navigationarchitecture.repository

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.example.stackexchange.navigationarchitecture.model.UserModel
import com.example.stackexchange.navigationarchitecture.utils.SharePref

class UserRepository {
    val userLiveData = MutableLiveData<UserModel>()

}