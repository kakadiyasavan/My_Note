package com.example.my_note

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColor
import com.example.my_note.Adapter.NoteAdapter
import com.example.my_note.Database.RoomDB
import com.example.my_note.Entity.NoteEntitty
import com.example.my_note.databinding.ActivityAddBinding
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import java.text.SimpleDateFormat
import java.util.Date


class Add_Activity : AppCompatActivity() {

    lateinit var binding: ActivityAddBinding
    lateinit var db: RoomDB
    lateinit var adapter: NoteAdapter
    var color = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = RoomDB.init(this)
        binding.btnSubmit.setOnClickListener {


            var title = binding.editTitle.text.toString()
            var note = binding.editNote.text.toString()
            var format = SimpleDateFormat("dd/MM/YYYY")
            var current = format.format(Date())
            var data = NoteEntitty(title, note, current, color)


            db.note().addNote(data)
            finish()
        }


        binding.color.setOnClickListener {

            // Kotlin Code
            MaterialColorPickerDialog
                .Builder(this)                            // Pass Activity Instance
                .setTitle("Pick Theme")                // Default "Choose Color"
                .setColorShape(ColorShape.SQAURE)    // Default ColorShape.CIRCLE
                .setColorSwatch(ColorSwatch._300)    // Default ColorSwatch._500
                .setColorListener { color, colorHex ->
                    // Handle Color Selection
                    this.color = color

                }
                .show()


        }
    }
}

