package com.example.stackexchange.navigationarchitecture.orthers.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.stackexchange.navigationarchitecture.R
import com.example.stackexchange.navigationarchitecture.interfaces.ImpItemClickListener
import com.example.stackexchange.navigationarchitecture.model.MenuModel
import com.example.stackexchange.navigationarchitecture.utils.AppUtils

class MenuAdapter(var list:ArrayList<MenuModel>): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    lateinit var impItemClickListener : ImpItemClickListener
    lateinit  var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.MenuViewHolder {
        val view : View  = LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false)
        context = parent.context
        return MenuViewHolder(view)
    }
    fun setItemClickListener(impItemClickListener: ImpItemClickListener){
        this.impItemClickListener = impItemClickListener
    }
    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MenuAdapter.MenuViewHolder, position: Int) {
        AppUtils.fadeInTransitionAnimation(holder.container,R.anim.slide_in_left,context)
        holder.titleMenu.setText(list[position].title.toUpperCase())
        holder.imageMenu.setImageResource(list[position].image)
        holder.itemView.setOnClickListener {
            if(impItemClickListener != null){
                AppUtils.animationVisibility(it,true)
                impItemClickListener.itemClickListener(position)
            }
        }
    }
    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageMenu = itemView.findViewById<ImageView>(R.id.iv_menu)
        val titleMenu = itemView.findViewById<TextView>(R.id.tv_titleOption)
        val container  = itemView.findViewById<CardView>(R.id.container)
    }
}