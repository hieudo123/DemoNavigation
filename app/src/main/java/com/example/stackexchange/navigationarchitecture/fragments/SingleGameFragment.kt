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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.base.BaseFragment
import com.example.stackexchange.navigationarchitecture.model.UserModel
import com.example.stackexchange.navigationarchitecture.orthers.constant.AppConstant
import com.example.stackexchange.navigationarchitecture.utils.AppUtils
import com.example.stackexchange.navigationarchitecture.viewmodels.SingleGameViewModel
import com.example.stackexchange.navigationarchitecture.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_in_singlegame.*
import kotlinx.android.synthetic.main.fragment_in_singlegame.view.*
import java.text.DecimalFormat


class SingleGameFragment:BaseFragment(),View.OnClickListener {

    var coin = 0
    lateinit var userViewModel: UserViewModel
    lateinit var singleGameViewModel: SingleGameViewModel
    var pickUp =""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_in_singlegame,container,false)
        init(view)
        setActionBar(view,"")
        return view
    }

    fun init(view :View){
        setOnClickListener(view)
        setUpUserViewModel()
        setUpSingleGameViewModel()
    }

    private fun setUpUserViewModel() {
        userViewModel = activity?.let { ViewModelProviders.of(it).get(UserViewModel::class.java) }!!
        userViewModel.userLiveData.observe(this, Observer<UserModel> {userModel->
            singleGameViewModel.setUserModel(userModel)
            getUserInfo(userModel)
        })
    }

    private fun setUpSingleGameViewModel(){
        singleGameViewModel =  ViewModelProviders.of(this).get(SingleGameViewModel::class.java)
    }

    fun getUserInfo(userModel: UserModel){
        fragSingleGame_ivUserImage.setBackgroundResource(userModel.image)
        fragSingleGame_tvUserCoin.text = AppUtils.formatNumber(userModel.coin)
        coin = userModel.coin

    }

    fun setOnClickListener(view: View){
        view.findViewById<Button>(R.id.fragSingleGame_btnLarger).setOnClickListener(this)
        view.findViewById<Button>(R.id.fragSingleGame_btnSmall).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.fragSingleGame_btnSmall->{
                pickUp = AppConstant.SMALL
                startRandoomResult()
            }
            R.id.fragSingleGame_btnLarger->{
                pickUp = AppConstant.LARGER
                startRandoomResult()
            }
        }
    }

    fun startRandoomResult(){
        animationVisibility(fragSingleGame_btnSmall,false)
        animationVisibility(fragSingleGame_btnLarger,false)
        animationVisibility(actionBar,false)
        object : CountDownTimer(5000,100){
            override fun onFinish() {
                val random =(1..24).random()
                fragSingleGame_tvResult.setText("${random}")
                showResult(random)
                context.let {
                    AppUtils.makeVibrator(it!!)
                }
                val handler = Handler()
                handler.postDelayed({
                    if (view != null)
                        resetView()
                },3000)
            }
            override fun onTick(p0: Long) {
                val random =(1..24).random()
                fragSingleGame_tvResult.setText("${random}")
                startAnimation(fragSingleGame_tvResult,R.anim.slide_in_down)
            }

        }.start()
    }

    fun resetView(){
        animationVisibility(actionBar,true)
        animationVisibility(fragSingleGame_btnLarger,true)
        animationVisibility(fragSingleGame_btnSmall,true)
        fragSingleGame_tvResult.text =""
        tv_textResult.visibility = View.GONE
    }

    fun checkLarger(number:Int):String{
        if(number >= 12)
            return AppConstant.LARGER
        return AppConstant.SMALL
    }

    fun showResult(number : Int){
        if(pickUp == checkLarger(number)){
            tv_textResult.setText(getString(R.string.win).toUpperCase()+" +5,000")
            tv_textResult.setTextColor(ContextCompat.getColor(this@SingleGameFragment.context!!, R.color.point_color))
            fadeInTransitionAnimation(tv_textResult,R.anim.slide_in_up)
            coin += 5000
            fragSingleGame_tvUserCoin.setText(AppUtils.formatNumber(coin))
        }
        else{
            tv_textResult.setText(getString(R.string.lost).toUpperCase()+" -5,000")
            tv_textResult.setTextColor(ContextCompat.getColor(this@SingleGameFragment.context!!, R.color.colorerror))
            fadeInTransitionAnimation(tv_textResult,R.anim.slide_in_up)
            coin = coin - 5000
            fragSingleGame_tvUserCoin.setText(AppUtils.formatNumber(coin))
        }
        singleGameViewModel.updateUserCoin(coin)
    }
}