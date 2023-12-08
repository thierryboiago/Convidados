package com.thierryboiago.convidados.ui.activity.guestform

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thierryboiago.convidados.repository.GuestRepository
import com.thierryboiago.convidados.repository.model.GuestModel

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.getInstance(application)


    fun insert(guest: GuestModel) {
        repository.insert(guest)
    }

}