package com.example.my_note.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes")
data class NoteEntitty(


    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "color") var color: Int,


    ){

    @PrimaryKey(autoGenerate = true) var id:Int = 0
}
