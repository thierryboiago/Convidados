package com.thierryboiago.convidados.ui.fragment.allguets

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thierryboiago.convidados.repository.GuestRepository
import com.thierryboiago.convidados.repository.model.GuestModel

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.getInstance(application.applicationContext)
    private val _listAllguests = MutableLiveData<List<GuestModel>>()

    val listAllguests: LiveData<List<GuestModel>> = _listAllguests

    fun getAll(){
        _listAllguests.value = repository.getAll()

    }

    fun delete(id: Int){
        repository.delete(id)
    }
}