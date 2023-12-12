package com.thierryboiago.convidados.ui.fragment.absent

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thierryboiago.convidados.databinding.FragmentAbsentBinding
import com.thierryboiago.convidados.repository.constants.DataBaseConstants
import com.thierryboiago.convidados.ui.activity.guestform.GuestFormActivity
import com.thierryboiago.convidados.ui.fragment.adapter.GuestAdapter
import com.thierryboiago.convidados.ui.fragment.allguets.GuestsViewModel
import com.thierryboiago.convidados.ui.listener.OnGuestListener

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewModel
    private var adapter = GuestAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentAbsentBinding.inflate(inflater, container, false)
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }

        }

        adapter.setListener(listener)



        observer()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAbsent()
    }

    private fun observer() {
        viewModel.listAllguests.observe(viewLifecycleOwner) {
            adapter.setList(it)

        }
    }
}