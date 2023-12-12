package com.thierryboiago.convidados.ui.activity.guestform

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thierryboiago.convidados.repository.GuestRepository
import com.thierryboiago.convidados.repository.model.GuestModel

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application.applicationContext)

    private val _guest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = _guest

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest


    fun save(guest: GuestModel) {
        if(guest.id == 0){
            if(repository.insert(guest)){
                _saveGuest.value = "Inserção com sucesso"
            }else{
                _saveGuest.value = "Erro"
            }
        }else{
            if(repository.update(guest)){
                _saveGuest.value = "Alteração com sucesso"
            }else{
                _saveGuest.value = "Erro"
            }
        }

    }

    fun getGuest(id: Int){
        _guest.value = repository.getGuest(id)
    }

}