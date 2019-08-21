package com.example.stackexchange.navigationarchitecture.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.base.BaseFragment
import com.example.stackexchange.navigationarchitecture.orthers.constant.AppConstant
import com.example.stackexchange.navigationarchitecture.utils.AppUtils
import java.text.DecimalFormat


class SingleGameFragment:BaseFragment(),View.OnClickListener {
    lateinit var tvUserCoin : TextView
    var coin = 30000
    lateinit var btnPickSmall : Button
    lateinit var btnPickLarger : Button
    lateinit var tvResult : TextView
    lateinit var tvTextResult : TextView
    var pickUp =""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_in_singlegame,container,false)
        init(view)
        return view
    }
    fun init(view :View){
        tvUserCoin = view.findViewById(R.id.tv_userCoin)
        tvResult = view.findViewById(R.id.tv_result)
        btnPickLarger = view.findViewById(R.id.btn_larger)
        btnPickSmall = view.findViewById(R.id.btn_small)
        tvTextResult = view.findViewById(R.id.tv_textResult)
        setOnClickListener(view)
        tvUserCoin.setText(AppUtils.formatNumber(coin))
    }
    fun setOnClickListener(view : View){
        btnPickSmall.setOnClickListener(this)
        btnPickLarger.setOnClickListener(this)
    }
    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.btn_small->{
                pickUp = AppConstant.SMALL
                startRandoomResult()
            }
            R.id.btn_larger->{
                pickUp = AppConstant.LARGER
                startRandoomResult()
            }
        }
    }
    fun startRandoomResult(){
        animationVisibility(btnPickLarger,false)
        animationVisibility(btnPickSmall,false)
        object : CountDownTimer(5000,100){
            override fun onFinish() {
                val random =(1..24).random()
                tvResult.setText("${random}")
                showResult(random)
                AppUtils.makeVibrator(this@SingleGameFragment.context!!)
                val handler = Handler()
                handler.postDelayed({
                    resetView()
                },3000)
            }
            override fun onTick(p0: Long) {
                val random =(1..24).random()
                startAnimation(tvResult,R.anim.slide_in_down)
                tvResult.setText("${random}")

            }

        }.start()
    }
    fun checkUserCoin(){
        if(coin == 0){
            animationVisibility(btnPickLarger,false)
            animationVisibility(btnPickSmall,false)
        }
    }
    fun resetView(){
        animationVisibility(btnPickLarger,true)
        animationVisibility(btnPickSmall,true)
        tvResult.text =""
        tvTextResult.visibility = View.GONE
    }
    fun checkLarger(number:Int):String{
        if(number >= 12)
            return AppConstant.LARGER
        return AppConstant.SMALL
    }
    fun showResult(number : Int){
        if(pickUp == checkLarger(number)){
            tvTextResult.setText(getString(R.string.win).toUpperCase()+" +5,000")
            tvTextResult.setTextColor(ContextCompat.getColor(this@SingleGameFragment.context!!, R.color.point_color))
            fadeInTransitionAnimation(tvTextResult,R.anim.slide_in_up)
            coin += 5000
            tvUserCoin.setText(AppUtils.formatNumber(coin))
        }
        else{
            tvTextResult.setText(getString(R.string.lost).toUpperCase()+" -5,000")
            tvTextResult.setTextColor(ContextCompat.getColor(this@SingleGameFragment.context!!, R.color.colorerror))
            fadeInTransitionAnimation(tvTextResult,R.anim.slide_in_up)
            coin = coin - 5000
            tvUserCoin.setText(AppUtils.formatNumber(coin))
        }
    }
}