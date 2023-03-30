package com.example.tableapplication.presentation

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tableapplication.R

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var memberAdapter: MemberAdapter
    private lateinit var tableRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.mMemberList.observe(this) {
            memberAdapter.memberList = it
            if (!tableRecyclerView.isComputingLayout) {
                tableRecyclerView.visibility = View.INVISIBLE
                progressBar.setVisibility(View.VISIBLE)
                Handler().postDelayed({
                    progressBar.setVisibility(View.INVISIBLE)
                    tableRecyclerView.visibility = View.VISIBLE
                }, 500)
                memberAdapter.notifyDataSetChanged()
            }
        }
        memberAdapter.editIsFinished.observe(this) {
            if (it) {
                memberAdapter.isFirstRun = false
                viewModel.updateList(memberAdapter.listOfCorrectPoints)

            }
        }
    }

    private fun setupRecyclerView() {
        tableRecyclerView = findViewById(R.id.table_recycler_view)
        memberAdapter = MemberAdapter()
        tableRecyclerView.layoutManager = LinearLayoutManager(this)
        tableRecyclerView.adapter = memberAdapter
        tableRecyclerView.recycledViewPool.setMaxRecycledViews(R.layout.table_row, 7)
        progressBar = findViewById(R.id.progressBar)
    }
}