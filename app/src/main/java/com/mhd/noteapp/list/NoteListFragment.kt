package com.mhd.noteapp.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mhd.noteapp.R

class NoteListFragment: Fragment(R.layout.fragment_list) {

    private val viewModel: NoteListViewModel by viewModels { NoteListViewModel.Factory }

}