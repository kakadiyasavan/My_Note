package com.example.my_note.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.my_note.Entity.NoteEntitty
import com.example.my_note.databinding.NoteItemBinding

class NoteAdapter(notes: List<NoteEntitty>) : Adapter<NoteAdapter.NoteHolder>() {


    var notes = notes

    class NoteHolder(itemView: NoteItemBinding) : ViewHolder(itemView.root) {

        var binding = itemView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        var binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(binding)
    }

    override fun getItemCount(): Int {

        return notes.size


    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {

        holder.binding.apply {
            notes.get(position).apply {
                txtitle.text = title
                txtnote.text = text
                txtdate.text = date

            }
            txtitle.isSelected = true
            card.setCardBackgroundColor(notes.get(position).color)
        }
    }

    fun update(notes: List<NoteEntitty>) {
        this.notes = notes
        notifyDataSetChanged()
    }

}