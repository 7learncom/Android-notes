package com.mhd.noteapp.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mhd.noteapp.R
import com.mhd.noteapp.databinding.FragmentListBinding
import kotlinx.coroutines.launch

class NoteListFragment: Fragment(R.layout.fragment_list) {

    private val viewModel: NoteListViewModel by viewModels { NoteListViewModel.Factory }

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)
        setupViews()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.notes.collect { notes ->

                    val adapter = NoteAdapter(
                        notes = notes,
                        onNoteClick = {},
                    )
                    binding.rvNotes.adapter = adapter

                    binding.tvCount.text = getString(R.string.list_count, notes.size)

                }
            }
        }
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