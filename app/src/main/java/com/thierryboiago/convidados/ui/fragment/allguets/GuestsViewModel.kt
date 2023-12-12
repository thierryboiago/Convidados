package com.thierryboiago.convidados.ui.fragment.allguets

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thierryboiago.convidados.repository.GuestRepository
import com.thierryboiago.convidados.repository.model.GuestModel

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application.applicationContext)
    private val _listAllguests = MutableLiveData<List<GuestModel>>()

    val listAllguests: LiveData<List<GuestModel>> = _listAllguests

    fun getAll(){
        _listAllguests.value = repository.getAll()

    }

    fun getAbsent(){
        _listAllguests.value = repository.getWithPresence(0)

    }

    fun getPresent(){
        _listAllguests.value = repository.getWithPresence(1)

    }

    fun delete(id: Int){
        repository.delete(id)
    }
}