package com.example.tableapplication.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tableapplication.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var memberAdapter: MemberAdapter
    private lateinit var tableRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.mMemberList.observe(this) {
            Toast.makeText(this, "Таблица получает данные", Toast.LENGTH_SHORT).show()
            memberAdapter.memberList = it
            if (!tableRecyclerView.isComputingLayout) {
                memberAdapter.notifyDataSetChanged()
            }
        }
        memberAdapter.editIsFinished.observe(this) {
            if (it) {
                Toast.makeText(this, "Таблица обновиться должна", Toast.LENGTH_SHORT).show()
                memberAdapter.dataIsFull[0] =  true
                viewModel.updateList(memberAdapter.listOfCorrectPoints)
            }
        }
    }

    private fun setupRecyclerView() {
        tableRecyclerView = findViewById(R.id.table_recycler_view)
        memberAdapter = MemberAdapter()
        tableRecyclerView.layoutManager = LinearLayoutManager(this)
        tableRecyclerView.adapter = memberAdapter
    }
}