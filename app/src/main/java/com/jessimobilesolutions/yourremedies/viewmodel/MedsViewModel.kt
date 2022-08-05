package com.jessimobilesolutions.yourremedies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jessimobilesolutions.yourremedies.model.MedModel
import com.jessimobilesolutions.yourremedies.repository.MedRepository

class MedsViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: MedRepository = MedRepository.getInstance(application.applicationContext)

    private val listAllMeds = MutableLiveData<List<MedModel>>()
    val meds: LiveData<List<MedModel>> = listAllMeds

    fun getAll(){
        listAllMeds.value = repository.getAll()
    }

    fun getCrontrolled(){
        listAllMeds.value = repository.getCrontrolled()
    }

    fun getNotCrontrolled(){
        listAllMeds.value = repository.getNotControlled()
    }

    fun delete(id: Int){
        repository.delete(id)
    }
}