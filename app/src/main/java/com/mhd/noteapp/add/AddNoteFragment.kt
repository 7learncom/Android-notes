package com.mhd.noteapp.add

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mhd.noteapp.R
import com.mhd.noteapp.databinding.FragmentAddBinding

class AddNoteFragment: Fragment(R.layout.fragment_add) {

    private val viewModel: AddNoteViewModel by viewModels { AddNoteViewModel.Factory }

    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddBinding.bind(view)
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViews() {
        handleButtonStates()
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