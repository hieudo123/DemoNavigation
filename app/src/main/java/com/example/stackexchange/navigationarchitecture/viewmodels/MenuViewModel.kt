package com.example.stackexchange.navigationarchitecture.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.model.MenuModel
import com.example.stackexchange.navigationarchitecture.orthers.constant.AppConstant
import com.example.stackexchange.navigationarchitecture.repository.MenuRepository
import com.example.stackexchange.navigationarchitecture.utils.AppUtils

class MenuViewModel : ViewModel() {
    private lateinit var menuRepository: MenuRepository
    var list = MutableLiveData<ArrayList<MenuModel>> ()
    fun loadMenu(){
        menuRepository = MenuRepository()
        menuRepository.setMenu()
        list = menuRepository.getMenu()
    }
    fun menuItemClickListener(position : Int,navController : NavController){
        when(list.value!![position].title){
            AppConstant.SIGLEPLAYER->{
                navController.navigate(R.id.action_mainFragment_to_singleGameFragment)
            }
        }
    }
}