package com.example.stackexchange.navigationarchitecture.base

import android.animation.ObjectAnimator
import android.graphics.Path
import android.icu.text.CaseMap
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.model.UserModel

open class BaseFragment : Fragment() {
    fun addFragment(container:Int, fragment: Fragment, isBackStack:Boolean) {
        if(activity is BaseActivity){
            (activity as BaseActivity).addFragment(container,fragment,isBackStack)
        }
    }
    fun replaceFrament(container:Int, fragment: Fragment, isBackStack:Boolean) {
        if(activity is BaseActivity){
            (activity as BaseActivity).replaceFrament(container,fragment,isBackStack)
        }
    }
    fun animationVisibility(view: View, visibile: Boolean){
        view.visibility = View.VISIBLE
        if(visibile){
            ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f).start()
        }
        else
            ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f).apply {
                doOnEnd { view.visibility = View.GONE }
            }.start()
    }
    fun startAnimation(view : View,id : Int){
        val TranslateAnimation : Animation = AnimationUtils.loadAnimation(context,id)
        view.startAnimation(TranslateAnimation)
    }
    fun startRotaAnimation(view:View){
        val rotaAnimation : RotateAnimation = RotateAnimation(0F,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f)
        rotaAnimation.duration = 400
        rotaAnimation.fillAfter = true
        view.startAnimation(rotaAnimation)
    }
    fun fadeInTransitionAnimation(view:View,id : Int){
        val translateAnimation : Animation = AnimationUtils.loadAnimation(context,id)
        animationVisibility(view,true)
        view.startAnimation(translateAnimation)
    }
    fun fadeOutTransitionAnimation(view:View,id : Int){
        val translateAnimation : Animation = AnimationUtils.loadAnimation(context,id)
        animationVisibility(view,false)
        view.startAnimation(translateAnimation)
    }
    fun setActionBar(view : View, title: String){
        if(activity is BaseActivity){
            (activity as BaseActivity).setActionBar(view,title)
        }
    }
    fun getCurrentUser():UserModel{
        return  (activity!!.application as BaseApplication).getUser()
    }

}