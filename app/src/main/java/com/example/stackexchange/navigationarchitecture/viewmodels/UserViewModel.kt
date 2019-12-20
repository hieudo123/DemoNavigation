package com.example.stackexchange.navigationarchitecture.viewmodels

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stackexchange.navigationarchitecture.model.UserModel
import com.example.stackexchange.navigationarchitecture.repository.UserRepository

class UserViewModel(application: Application): AndroidViewModel(application) {
    var userLiveData = MutableLiveData<UserModel>()
    var  userRepository : UserRepository
    init {
        userRepository = UserRepository()

    }

    fun createUser(userName:String, gender:String, coin: Int, image : Int){
        userLiveData.value = UserModel(userName,coin,gender,image)
    }

    fun updateUserModel(userModel: UserModel){
        userLiveData.value = userModel
    }


}