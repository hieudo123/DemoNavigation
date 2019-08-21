package com.example.stackexchange.navigationarchitecture.base

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.model.UserModel
import kotlinx.android.synthetic.main.actionbar_back.view.*

abstract class BaseActivity : AppCompatActivity() {
    val fragmentManager: FragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun addFragment(container:Int, fragment: Fragment, isBackStack:Boolean) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.add(container,fragment)
        if(isBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }
    fun replaceFrament(container: Int, fragment: Fragment, isBackStack: Boolean) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(container,fragment)
        if(isBackStack)
            transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun setTitle(resId: Int) {
        super.setTitle(resId)
    }

    override fun setTitle(cs: CharSequence) {
        super.setTitle(cs)
    }
    fun setActionBar(view : View, title : String ){
        val vBack : View = view.findViewById(R.id.actionbar_ivBack)
        val vTitle :TextView = view.findViewById(R.id.tv_title)
        if(title.isNotEmpty())
            vTitle.setText(title)
        if(vBack != null)
            vBack.setOnClickListener(onBackClick)
    }
    val onBackClick : View.OnClickListener =View.OnClickListener { onBackPressed() }
    override fun onBackPressed() {
        super.onBackPressed()
    }
    fun getCurrentUser():UserModel{
       return  (application as BaseApplication).getUser()
    }
}