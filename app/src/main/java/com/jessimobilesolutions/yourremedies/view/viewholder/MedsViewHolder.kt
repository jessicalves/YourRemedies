package com.jessimobilesolutions.yourremedies.view.viewholder

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.jessimobilesolutions.yourremedies.databinding.RowMedBinding
import com.jessimobilesolutions.yourremedies.model.MedModel
import com.jessimobilesolutions.yourremedies.view.listener.OnMedListener

class MedsViewHolder(private val item: RowMedBinding, private val listener: OnMedListener) :
    RecyclerView.ViewHolder(item.root) {

    fun bind(med: MedModel) {
        item.textName.text = med.name

        item.textName.setOnClickListener {
            listener.onClick(med.id)
        }

        item.textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Excluir Remédio")
                .setMessage("Tem certeza que deseja excluir?")
                .setPositiveButton(
                    "Sim"
                ) { dialog, which -> listener.onDelete(med.id) }
                .setNegativeButton("Não", null)
                .create()
                .show()
            true
        }
    }
}