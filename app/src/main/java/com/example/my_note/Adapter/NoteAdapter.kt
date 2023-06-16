package com.example.my_note.Adapter

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.my_note.Database.RoomDB
import com.example.my_note.Entity.NoteEntitty
import com.example.my_note.MainActivity
import com.example.my_note.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NoteAdapter(notes: List<NoteEntitty>) : Adapter<NoteAdapter.NoteHolder>() {


    var notes = notes
    lateinit var context: Context
    lateinit var db: RoomDB


    class NoteHolder(itemView: View) : ViewHolder(itemView) {


        var texttitel=itemView.findViewById<TextView>(R.id.txtitle)
        var textnote=itemView.findViewById<TextView>(R.id.txtnote)
        var pin=itemView.findViewById<ImageView>(R.id.imgPin)
        var cardnote=itemView.findViewById<CardView>(R.id.card)
        var delete=itemView.findViewById<ImageView>(R.id.delete)
        var edit=itemView.findViewById<ImageView>(R.id.edit)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        context=parent.context
       return  NoteHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false))
    }

    override fun getItemCount(): Int {

        return notes.size


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NoteHolder, position: Int) {


        db = RoomDB.init(context)
        holder.apply {

            notes.get(position).apply {
                texttitel.text = title
                textnote.text = text


            }

            texttitel.isSelected = true
            cardnote.setCardBackgroundColor(notes.get(position).color)

            delete.setOnClickListener {
                db.note().detelNote(notes[position])
               update(notes)
            }

            itemView.setOnClickListener {
                var dialog=Dialog(context)
                dialog.setContentView(R.layout.activity_add)

                var formater=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a")
                var curent=LocalDateTime.now().format(formater)

                var edititle=dialog.findViewById<EditText>(R.id.editTitle)
                var editnotes=dialog.findViewById<EditText>(R.id.editNote)

                edititle.setText(notes[position].title)
                editnotes.setText(notes[position].text)



                dialog.show()

                edit.setOnClickListener {
                    var data=NoteEntitty(edititle.text.toString(),editnotes.text.toString(),curent,notes[position].color)
                    data.id=notes[position].id

                    db.note().updateNote(data)
                    dialog.dismiss()
                    update(notes)
                }
            }


        }
    }

    fun update(notes: List<NoteEntitty>) {
        this.notes = notes
        notifyDataSetChanged()
    }

}