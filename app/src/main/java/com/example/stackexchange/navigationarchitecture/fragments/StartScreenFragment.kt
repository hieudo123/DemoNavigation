package com.example.stackexchange.navigationarchitecture.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.base.BaseFragment
import com.github.ybq.android.spinkit.SpinKitView
import kotlinx.android.synthetic.main.fragment_start_screen.*

class StartScreenFragment : BaseFragment(),View.OnClickListener {
    lateinit var spinKitView: SpinKitView
    lateinit var itemLeft : ImageView
    lateinit var ivLogo : ImageView

    var navController : NavController ?= null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_start_screen,container,false)
        init(view)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

    }
    fun init(view:View){
        spinKitView = view.findViewById(R.id.spin_kit)
        itemLeft = view.findViewById(R.id.iv_itemLeft)
        ivLogo = view.findViewById(R.id.iv_logo)
        onClickListener(view)
        startAnimation(itemLeft,R.anim.anim_move_item_left)
        startAnimation(ivLogo,R.anim.slide_in_down)
    }
    fun onClickListener(view : View){
        view.findViewById<Button>(R.id.btn_startGame).setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btn_startGame->{
                animationVisibility(ivLogo,false)
                animationVisibility(itemLeft,false)
                startRotaAnimation(p0.findViewById(R.id.btn_startGame))
                val handler = Handler()
                handler.postDelayed({
                    animationVisibility(p0.findViewById(R.id.btn_startGame),false)
                    animationVisibility(spinKitView,true)
                    val handler_2 = Handler()
                    handler_2.postDelayed({
                        navController!!.navigate(R.id.action_startScreenFragment_to_createPlayerFragment)
                    },1200)
                },400)

            }
        }
    }

}