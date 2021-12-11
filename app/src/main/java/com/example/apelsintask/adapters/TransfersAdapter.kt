package com.example.apelsintask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apelsintask.databinding.ItemTransfersBinding

class TransfersAdapter(var list: List<String>,var listener:OnItemClick) :
    RecyclerView.Adapter<TransfersAdapter.MyViewHolder>() {


    inner class MyViewHolder(var itemTransfersBinding: ItemTransfersBinding) :
        RecyclerView.ViewHolder(itemTransfersBinding.root) {
        fun onBind(str: String) {
            itemTransfersBinding.apply {
                tv.text = str
            }
            itemView.setOnClickListener {
                listener.onItemClick(str)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemTransfersBinding.inflate(
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