package com.example.stackexchange.navigationarchitecture.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.base.BaseFragment
import com.example.stackexchange.navigationarchitecture.interfaces.ImpItemClickListener
import com.example.stackexchange.navigationarchitecture.model.MenuModel
import com.example.stackexchange.navigationarchitecture.model.UserModel
import com.example.stackexchange.navigationarchitecture.orthers.adapters.MenuAdapter
import com.example.stackexchange.navigationarchitecture.utils.AppUtils
import com.example.stackexchange.navigationarchitecture.viewmodels.MenuViewModel
import com.example.stackexchange.navigationarchitecture.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseFragment(),ImpItemClickListener {

    var navController : NavController? = null
    lateinit var userViewModel: UserViewModel
    lateinit var menuViewModel: MenuViewModel
    lateinit var rvMenu : RecyclerView
    lateinit var menuAdapter: MenuAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        setUpMenuViewModel()
        setUpUserViewModel()

    }

    private fun setUpUserViewModel() {
        userViewModel = activity?.let { ViewModelProviders.of(it).get(UserViewModel::class.java) }!!
        userViewModel.userLiveData.observe(this,
            Observer<UserModel> { userModel ->
                userModel?.let { showUserInfomation(it) }
            })
    }

    private fun showUserInfomation(userModel: UserModel) {
        fragMain_tvUsername.text = userModel.username
        fragMain_tvUserCoin.text = AppUtils.formatNumber(userModel.coin)
        fragMain_ivUserImage.setBackgroundResource(userModel.image)
    }

    fun setUpMenuViewModel(){
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
        val linearLayoutManager  = LinearLayoutManager(context)
        rvMenu.layoutManager = linearLayoutManager
        rvMenu.adapter = menuAdapter
    }

    override fun itemClickListener(position: Int) {
        menuViewModel.menuItemClickListener(position, this.navController!!)
    }
}
