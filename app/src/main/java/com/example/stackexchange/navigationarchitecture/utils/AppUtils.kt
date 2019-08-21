package com.example.stackexchange.navigationarchitecture.utils

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.animation.doOnEnd
import java.text.DecimalFormat


class AppUtils {
    companion object{
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
        fun fadeInTransitionAnimation(view: View, id : Int,context:Context){
            val translateAnimation : Animation = AnimationUtils.loadAnimation(context,id)
            animationVisibility(view,true)
            view.startAnimation(translateAnimation)
        }
        fun fadeOutTransitionAnimation(view: View, id : Int,context:Context){
            val translateAnimation : Animation = AnimationUtils.loadAnimation(context,id)
            animationVisibility(view,false)
            view.startAnimation(translateAnimation)
        }
        fun makeVibrator(context: Context){
            val vibrator : Vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if(Build.VERSION.SDK_INT >= 26){
                vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
            }else{
                vibrator.vibrate(200)
            }
        }
        fun formatNumber(value:Any):String{
            val formatter : DecimalFormat = DecimalFormat("#,###,###")
            val myFormatter : String = formatter.format(value)
            return myFormatter
        }
    }

}