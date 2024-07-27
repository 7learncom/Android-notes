package com.mhd.noteapp.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mhd.noteapp.R
import com.mhd.noteapp.databinding.FragmentListBinding

class NoteListFragment: Fragment(R.layout.fragment_list) {

    private val viewModel: NoteListViewModel by viewModels { NoteListViewModel.Factory }

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViews() {
        binding.btnAdd.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToAddNoteFragment()
            findNavController().navigate(action)
        }
    }

}