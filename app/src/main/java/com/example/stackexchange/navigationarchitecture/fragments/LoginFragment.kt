package com.example.stackexchange.navigationarchitecture.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login_screen.*

class LoginFragment:BaseFragment(),View.OnClickListener {
    var navController :NavController? = null
    lateinit var btnLogin : Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_login_screen,container,false)
        init(view)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btn_login->{

            }
        }
    }
    fun init(view: View){
        btnLogin = view.findViewById(R.id.btn_login)
        btnLogin.setOnClickListener(this)
    }
}