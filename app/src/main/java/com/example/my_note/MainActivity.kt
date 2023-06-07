package com.example.my_note

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.my_note.Adapter.NoteAdapter
import com.example.my_note.Database.RoomDB
import com.example.my_note.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var db: RoomDB
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = RoomDB.init(this)

        initView()
        binding.btnnext.setOnClickListener {

            startActivity(Intent(this,Add_Activity::class.java))

        }

    }




    private fun initView() {
        adapter= NoteAdapter(db.note().getNotes())
        binding.rcvlist.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
        binding.rcvlist.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        adapter.update(db.note().getNotes())
    }


}