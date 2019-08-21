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
    lateinit var  userRepository : UserRepository
    init {
        userRepository = UserRepository(Activity())
        getUser(Activity())
    }
    fun createUser(userModel: UserModel){
        userRepository.createUser(userModel)
    }
    fun getUser(activity: Activity){
        userLiveData = userRepository.getUser()
    }
}