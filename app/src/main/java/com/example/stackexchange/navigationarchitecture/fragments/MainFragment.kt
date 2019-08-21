package com.example.stackexchange.navigationarchitecture.fragments


import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.base.BaseFragment
import com.example.stackexchange.navigationarchitecture.interfaces.ImpItemClickListener
import com.example.stackexchange.navigationarchitecture.model.MenuModel
import com.example.stackexchange.navigationarchitecture.orthers.adapters.MenuAdapter
import com.example.stackexchange.navigationarchitecture.viewmodels.MenuViewModel


class MainFragment : BaseFragment(),View.OnClickListener,ImpItemClickListener {
    var navController : NavController? = null
    lateinit var menuViewModel: MenuViewModel
    lateinit var rvMenu : RecyclerView
    lateinit var menuAdapter: MenuAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_main, container, false)
        setActionBar(view,getString(R.string.app_name).toUpperCase())
        init(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        super.onViewCreated(view, savedInstanceState)
    }
    fun init(view : View){
        rvMenu = view.findViewById(R.id.rv_menuList)
        menuViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(MenuViewModel::class.java)
        menuViewModel.loadMenu()
        menuViewModel.list.observe(this,object : Observer<List<MenuModel>>{
            override fun onChanged(t: List<MenuModel>?) {
                setAdapter(t as ArrayList<MenuModel>)
            }

        })
    }
    fun setAdapter(list: ArrayList<MenuModel>){
        menuAdapter = MenuAdapter(list)
        menuAdapter.setItemClickListener(this)
        val linearLayoutManager : LinearLayoutManager = LinearLayoutManager(context)
        rvMenu.layoutManager = linearLayoutManager
        rvMenu.adapter = menuAdapter
    }
    fun onClickListenner(){

    }
    override fun onClick(view: View?) {
        when(view!!.id){

        }
    }
    override fun itemClickListener(position: Int) {
        menuViewModel.menuItemClickListener(position, this.navController!!)
    }


}
