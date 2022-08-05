package com.jessimobilesolutions.yourremedies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jessimobilesolutions.yourremedies.R
import com.jessimobilesolutions.yourremedies.constantes.DataBaseConstantes
import com.jessimobilesolutions.yourremedies.databinding.ActivityRemedyFormBinding
import com.jessimobilesolutions.yourremedies.model.MedModel
import com.jessimobilesolutions.yourremedies.viewmodel.MedsFormViewModel

class MedsFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRemedyFormBinding
    private lateinit var medFormViewModel: MedsFormViewModel
    private var medId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityRemedyFormBinding.inflate(layoutInflater) // Uso binding para acessar meus elementos da interface
        setContentView(binding.root)

        binding.btnSave.setOnClickListener(this)
        medFormViewModel = ViewModelProvider(this).get(MedsFormViewModel::class.java)
        binding.radioControlled.isChecked = true

        observe()
        loadData()
    }

    private fun observe() {
        medFormViewModel.med.observe(this) {
            binding.editName.setText(it.name)
            if (it.classification) {
                binding.radioControlled.isChecked = true
            } else {
                binding.radioNotControlled.isChecked = true
            }
        }

        medFormViewModel.saveMed.observe(this) {
            if (it != "") {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            medId = bundle.getInt(DataBaseConstantes.MED.ID)
            medFormViewModel.get(medId)
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.btnSave) {
                val name = binding.editName.text.toString()
                val classification = binding.radioControlled.isChecked
                val model = MedModel(medId, name, classification)
                medFormViewModel.save(model)

            }
        }
    }
}