package com.example.my_note.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.my_note.Dao.NoteDao
import com.example.my_note.Entity.NoteEntitty

@Database(entities = [NoteEntitty::class], version = 1)
abstract class RoomDB : RoomDatabase() {

    companion object {
        fun init(context: Context): RoomDB {

            var db = Room.databaseBuilder(context, RoomDB::class.java, "Note.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            return db

        }
    }
   abstract fun note ():NoteDao

}