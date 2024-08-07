package com.mhd.noteapp.add

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mhd.noteapp.R
import com.mhd.noteapp.databinding.FragmentAddBinding
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class AddNoteFragment: Fragment(R.layout.fragment_add) {

    private val viewModel: AddNoteViewModel by viewModels { AddNoteViewModel.Factory }

    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddBinding.bind(view)
        setupViews()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(
                state = Lifecycle.State.STARTED
            ) {
                launch {
                    viewModel.onActionCompleteEvent.collect {
                        findNavController().navigateUp()
                    }
                }

                launch {
                    viewModel.currentNote
                        .filterNotNull()
                        .collect { note ->
                            binding.edtTitle.setText(note.title)
                            binding.edtNote.setText(note.text)
                        }
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViews() {
        handleButtonStates()

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.llAction.setOnClickListener {
            val title = binding.edtTitle.text?.toString().orEmpty()
            val text = binding.edtNote.text?.toString().orEmpty()
            viewModel.onActionClick(title, text)
        }

        binding.btnDelete.setOnClickListener {
            viewModel.onNoteDeleteClick()
        }
    }

    private fun handleButtonStates() {
        if (viewModel.isInEditMode) {
            binding.btnDelete.isVisible = true
            binding.tvAction.setText(R.string.add_edit)
            binding.ivAction.isVisible = false
        } else {
            binding.btnDelete.isVisible = false
            binding.tvAction.setText(R.string.add_addNote)
            binding.ivAction.setImageResource(android.R.drawable.ic_input_add)
        }
    }
}