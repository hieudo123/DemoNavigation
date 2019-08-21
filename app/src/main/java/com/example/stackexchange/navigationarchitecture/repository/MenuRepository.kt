package com.example.stackexchange.navigationarchitecture.repository

import android.util.Log
import android.view.Menu
import androidx.lifecycle.MutableLiveData
import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.model.MenuModel
import com.example.stackexchange.navigationarchitecture.orthers.constant.AppConstant

class MenuRepository() {
    val nenuList = MutableLiveData<ArrayList<MenuModel>> ()
    fun setMenu(){
        var list: ArrayList<MenuModel> = ArrayList()
        var menu = MenuModel(AppConstant.SIGLEPLAYER, R.drawable.single_player)
        list.add(menu)
        menu = MenuModel(AppConstant.MULTIPLAYER,R.drawable.multi_player)
        list.add(menu)
        menu = MenuModel(AppConstant.BUY,R.drawable.img_cart)
        list.add(menu)
        menu = MenuModel(AppConstant.SETTING,R.drawable.iv_setting)
        list.add(menu)
        Log.e(AppConstant.MENU,"menu ${menu}")
        this.nenuList.value= list
    }
    fun getMenu():MutableLiveData<ArrayList<MenuModel>>{
        return this.nenuList
    }
}