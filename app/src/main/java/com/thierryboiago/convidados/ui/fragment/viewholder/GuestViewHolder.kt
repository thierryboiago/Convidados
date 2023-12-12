package com.thierryboiago.convidados.ui.fragment.viewholder

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.thierryboiago.convidados.databinding.RowGuestBinding
import com.thierryboiago.convidados.repository.model.GuestModel
import com.thierryboiago.convidados.ui.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textRowName.text = guest.name

        bind.textRowName.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.textRowName.setOnLongClickListener(View.OnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim")
                { dialog, which -> listener.onDelete(guest.id) }

               .setNegativeButton("Não", null)

                .create()
                .show()


            true
        })
    }
}