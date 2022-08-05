package com.jessimobilesolutions.yourremedies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jessimobilesolutions.yourremedies.model.MedModel
import com.jessimobilesolutions.yourremedies.repository.MedRepository

class MedsFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MedRepository.getInstance(application)

    private val medModel = MutableLiveData<MedModel>()
    val med: LiveData<MedModel> = medModel

    private val _saveMed = MutableLiveData<String>()
    val saveMed: LiveData<String> = _saveMed

    fun save(med: MedModel) {
        if (med.id == 0) {
            if (repository.insert(med)) {
                _saveMed.value = "Salvo com Sucesso."
            } else {
                _saveMed.value = "Falha"
            }
        } else {
            if (repository.update(med)) {
                _saveMed.value = "Atualização com Sucesso."
            } else {
                _saveMed.value = "Falha"
            }
        }
    }

    fun get(id: Int) {
        medModel.value = repository.get(id)
    }
}