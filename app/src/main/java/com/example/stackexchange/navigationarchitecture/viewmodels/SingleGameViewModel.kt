package com.example.stackexchange.navigationarchitecture.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.stackexchange.navigationarchitecture.model.UserModel


class SingleGameViewModel(application: Application) : AndroidViewModel(application) {
    var userLiveData = MutableLiveData<UserModel>()

    fun setUserModel(userModel: UserModel) {
        userLiveData.value = userModel
    }

    fun updateUserCoin(coin: Int){
        userLiveData.value!!.coin = coin
    }
}