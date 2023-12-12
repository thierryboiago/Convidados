package com.thierryboiago.convidados.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thierryboiago.convidados.R
import com.thierryboiago.convidados.databinding.RowGuestBinding
import com.thierryboiago.convidados.repository.model.GuestModel
import com.thierryboiago.convidados.ui.fragment.viewholder.GuestViewHolder
import com.thierryboiago.convidados.ui.listener.OnGuestListener

class GuestAdapter(): RecyclerView.Adapter<GuestViewHolder>() {

    private var guestlist : List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val view = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
       holder.bind(guestlist[position])
    }

    override fun getItemCount(): Int {
       return guestlist.size
    }

    fun setList(list:  List<GuestModel>){
        guestlist = list
        notifyDataSetChanged()
    }

    fun setListener(guestListener:  OnGuestListener){
        listener = guestListener

    }





}

