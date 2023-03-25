package com.example.tableapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tableapplication.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var memberAdapter: MemberAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.memberList.observe(this){
            memberAdapter.memberList = it
        }

    }

    private fun setupRecyclerView() {
        val tableRecyclerView = findViewById<RecyclerView>(R.id.table_recycler_view)
        memberAdapter = MemberAdapter()
        tableRecyclerView.layoutManager = LinearLayoutManager(this)
        tableRecyclerView.adapter = memberAdapter
    }

}