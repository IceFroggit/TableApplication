package com.example.tableapplication.presentation

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tableapplication.R

class MemberViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val tvName: TextView = view.findViewById(R.id.member_name)
    val tvMemberId: TextView = view.findViewById(R.id.member_id)
    val etGradeList = arrayListOf<EditText>(
        view.findViewById(R.id.grade_1),
        view.findViewById(R.id.grade_2),
        view.findViewById(R.id.grade_3),
        view.findViewById(R.id.grade_4),
        view.findViewById(R.id.grade_5),
        view.findViewById(R.id.grade_6),
        view.findViewById(R.id.grade_7)
    )
    val sumPoint: TextView = view.findViewById(R.id.sum_of_points)
    val place: TextView = view.findViewById(R.id.place)
    val id: Int
        get() = tvMemberId.text.toString().toInt()
}
