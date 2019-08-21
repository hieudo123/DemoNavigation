package com.example.stackexchange.navigationarchitecture.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.base.BaseFragment
import com.example.stackexchange.navigationarchitecture.orthers.constant.AppConstant
import com.example.stackexchange.navigationarchitecture.viewmodels.UserViewModel
import com.github.ybq.android.spinkit.SpinKitView

class CreatePlayerFragment:BaseFragment(),View.OnClickListener {

    var gender: String = AppConstant.MALE
    var navController : NavController? = null
    lateinit var tvTitlePage : TextView
    lateinit var ivMale : ImageView
    lateinit var ivFemale:ImageView
    lateinit var tvMale : TextView
    lateinit var tvFemale: TextView
    lateinit var etUsername : TextView
    lateinit var btnCreatePlayer : Button
    lateinit var spinKitView: SpinKitView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_create_player,container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        setActionBar(view,"")
    }
    fun init(view : View){
        navController = Navigation.findNavController(view)
        tvMale = view.findViewById(R.id.tv_male)
        tvFemale = view.findViewById(R.id.tv_female)
        ivMale =  view.findViewById(R.id.iv_icon_male)
        ivFemale = view.findViewById(R.id.iv_icon_female)
        tvTitlePage = view.findViewById(R.id.tv_title_page)
        btnCreatePlayer = view.findViewById(R.id.btn_createPlayer)
        spinKitView = view.findViewById(R.id.spin_kit)
        etUsername = view.findViewById(R.id.et_name)
        onClickListener(view)
    }
    fun onClickListener(view : View){
        view.findViewById<ImageView>(R.id.iv_icon_male).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.iv_icon_female).setOnClickListener(this)
        btnCreatePlayer.setOnClickListener(this)
    }
    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.iv_icon_male->{
                animationVisibility(tvMale,true)
                gender =AppConstant.MALE
                animationVisibility(tvFemale,false)
            }
            R.id.iv_icon_female->{
                animationVisibility(tvFemale,true)
                gender = AppConstant.FEMALE
                animationVisibility(tvMale,false)
            }
            R.id.btn_createPlayer->{
                animationVisibility(btnCreatePlayer,false)
                animationVisibility(spinKitView,true)
                navController!!.navigate(R.id.action_createPlayerFragment_to_mainFragment)
            }
        }
    }
}