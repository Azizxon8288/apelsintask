package com.example.apelsintask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apelsintask.databinding.ItemMenuBinding
import com.example.apelsintask.databinding.ItemTransfersBinding

class MenuAdapter(var list: List<String>, var listener: OnItemClick) :
    RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {


    inner class MyViewHolder(var itemMenuBinding: ItemMenuBinding) :
        RecyclerView.ViewHolder(itemMenuBinding.root) {
        fun onBind(str: String) {
            itemMenuBinding.apply {
                tv.text = str
            }
            itemView.setOnClickListener {
                listener.onItemClick(str)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClick {
        fun onItemClick(str: String)
    }
}