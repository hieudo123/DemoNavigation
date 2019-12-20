package com.example.stackexchange.navigationarchitecture.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.base.BaseFragment
import com.example.stackexchange.navigationarchitecture.model.UserModel
import com.example.stackexchange.navigationarchitecture.orthers.constant.AppConstant
import com.example.stackexchange.navigationarchitecture.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_create_player.*


class CreatePlayerFragment:BaseFragment(),View.OnClickListener {

    var gender: String = AppConstant.MALE
    var navController : NavController? = null
    lateinit var userViewModel: UserViewModel
    val coin : Int = 50000

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
        userViewModel = activity?.let { ViewModelProviders.of(it).get(UserViewModel::class.java) }!!
        userViewModel.userLiveData.observe(this,object : Observer<UserModel>{
            override fun onChanged(t: UserModel?) {
                moveToMainFragment();
            }
        })
        onClickListener(view)
    }

    fun onClickListener(view : View){
        view.findViewById<ImageView>(R.id.iv_icon_male).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.iv_icon_female).setOnClickListener(this)
        btn_createPlayer.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.iv_icon_male->{
                selectedMale()
            }
            R.id.iv_icon_female->{
                selectedFemale()
            }
            R.id.btn_createPlayer->{
                createPlayer()
            }
        }
    }

    private fun createPlayer() {
        val image : Int
        if (gender == AppConstant.FEMALE)
            image = R.drawable.ic_female
        else
            image = R.drawable.ic_male
        userViewModel.createUser(et_name.text.toString(),gender,coin,image)

    }

    private fun moveToMainFragment() {
        animationVisibility(btn_createPlayer,false)
        animationVisibility(spin_kit,true)
        navController!!.navigate(R.id.action_createPlayerFragment_to_mainFragment)
    }

    private fun selectedFemale() {
        animationVisibility(tv_female,true)
        gender = AppConstant.FEMALE
        iv_icon_female.isEnabled = false;
        iv_icon_male.isEnabled = true;
        animationVisibility(tv_male,false)
    }

    private fun selectedMale() {
        animationVisibility(tv_male,true)
        gender =AppConstant.MALE
        iv_icon_male.isEnabled = false;
        iv_icon_female.isEnabled = true;
        animationVisibility(tv_female,false)
    }
}