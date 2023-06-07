package com.example.my_note.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.my_note.Entity.NoteEntitty
@Dao
interface NoteDao {

    @Insert
    fun addNote(noteEntity : NoteEntitty)

    @Query("SELECT * FROM notes")
    fun getNotes():List<NoteEntitty>

    @Update
    fun updateNote(noteEntity: NoteEntitty)

    @Delete
    fun detelNote(noteEntity: NoteEntitty)

    @Query("DELETE FROM notes")
    fun allDelete()

}