package com.mhd.noteapp.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhd.noteapp.data.NoteEntity
import com.mhd.noteapp.databinding.ItemNoteBinding

class NoteAdapter(
    private val notes: List<NoteEntity>,
    private val onNoteClick: (Int) -> Unit,
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding, onNoteClick)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    inner class NoteViewHolder(
        private val binding: ItemNoteBinding,
        onNoteClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(noteEntity: NoteEntity) {
            binding.tvTitle.text = noteEntity.title
            binding.tvNote.text = noteEntity.text

            binding.root.setOnClickListener {
                onNoteClick(noteEntity.id)
            }
        }

    }
}