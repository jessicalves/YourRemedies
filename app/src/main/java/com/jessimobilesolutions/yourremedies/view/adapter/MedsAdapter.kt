package com.jessimobilesolutions.yourremedies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jessimobilesolutions.yourremedies.databinding.RowMedBinding
import com.jessimobilesolutions.yourremedies.model.MedModel
import com.jessimobilesolutions.yourremedies.view.listener.OnMedListener
import com.jessimobilesolutions.yourremedies.view.viewholder.MedsViewHolder

class MedsAdapter: RecyclerView.Adapter<MedsViewHolder>() {

    private var medList: List<MedModel> = listOf()
    private lateinit var listener: OnMedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedsViewHolder {
        val item = RowMedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MedsViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: MedsViewHolder, position: Int) {
        holder.bind(medList[position])
    }

    override fun getItemCount(): Int {
        return medList.count()
    }

    fun updateMeds(list: List<MedModel>) {
        medList = list
        notifyDataSetChanged()
    }

    fun attachListener(medListener: OnMedListener){
        listener = medListener
    }
}