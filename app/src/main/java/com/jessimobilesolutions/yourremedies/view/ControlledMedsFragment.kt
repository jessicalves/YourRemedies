package com.jessimobilesolutions.yourremedies.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jessimobilesolutions.yourremedies.constantes.DataBaseConstantes
import com.jessimobilesolutions.yourremedies.databinding.FragmentControlledMedsBinding
import com.jessimobilesolutions.yourremedies.view.adapter.MedsAdapter
import com.jessimobilesolutions.yourremedies.view.listener.OnMedListener
import com.jessimobilesolutions.yourremedies.viewmodel.MedsViewModel

class ControlledMedsFragment : Fragment() {

    private var _binding: FragmentControlledMedsBinding? = null
    private val binding get() = _binding!!
    private lateinit var controlledMedsViewModel: MedsViewModel
    private val adapter = MedsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        controlledMedsViewModel = ViewModelProvider(this).get(MedsViewModel::class.java)
        _binding = FragmentControlledMedsBinding.inflate(inflater, container, false)
        binding.recycleMeds.layoutManager = LinearLayoutManager(context)
        binding.recycleMeds.adapter = adapter
        binding.recycleMeds.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        //Classe anonima
        val listener = object : OnMedListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, MedsFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstantes.MED.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                controlledMedsViewModel.delete(id)
                controlledMedsViewModel.getCrontrolled()
            }

        }
        adapter.attachListener(listener)
        observe()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        controlledMedsViewModel.getCrontrolled()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        controlledMedsViewModel.meds.observe(viewLifecycleOwner) {
            //Lista de convidados
            adapter.updateMeds(it)
        }
    }
}