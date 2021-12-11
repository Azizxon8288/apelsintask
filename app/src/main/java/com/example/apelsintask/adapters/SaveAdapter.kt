package com.example.apelsintask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apelsintask.databinding.ItemSaveBinding
import com.example.apelsintask.databinding.ItemServiceBinding
import com.example.apelsintask.databinding.ItemTransfersBinding

class SaveAdapter(var list: List<String>, var listener: OnItemClick) :
    RecyclerView.Adapter<SaveAdapter.MyViewHolder>() {


    inner class MyViewHolder(var itemSaveBinding: ItemSaveBinding) :
        RecyclerView.ViewHolder(itemSaveBinding.root) {
        fun onBind(str: String) {
            itemSaveBinding.apply {
                tv.text = str
            }
            itemView.setOnClickListener {
                listener.onItemClick(str)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemSaveBinding.inflate(
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