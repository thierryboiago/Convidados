package com.thierryboiago.convidados.ui.activity.guestform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thierryboiago.convidados.R
import com.thierryboiago.convidados.databinding.ActivityGuestFormBinding
import com.thierryboiago.convidados.repository.constants.DataBaseConstants
import com.thierryboiago.convidados.repository.model.GuestModel

class GuestFormActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observer()
        loadData()

    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_save){
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked
            val model = GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            }
            viewModel.save(model)
        }
    }

    fun observer(){

        viewModel.guest.observe(this) {
            binding.editName.setText(it.name)
            if(it.presence){
                binding.radioPresent.isChecked = true
            }else {
                binding.radioAbsent.isChecked = true
            }
        }

        viewModel.saveGuest.observe(this){
            if(it != "Erro"){
                Toast.makeText(this, it, Toast.LENGTH_SHORT)
                finish()
            }else{
                Toast.makeText(this, it, Toast.LENGTH_SHORT)
            }
        }
    }
    private fun loadData(){
        val bundle = intent.extras
        if(bundle != null){
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.getGuest(guestId)
        }
    }
}