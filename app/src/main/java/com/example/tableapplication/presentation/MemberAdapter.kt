package com.example.tableapplication.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tableapplication.R
import com.example.tableapplication.domain.Member

class MemberAdapter : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
    var addTextChangedListener: ((EditText) -> Unit)? = null
    var memberList = listOf<Member>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.table_row, parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val item = memberList[position]
        holder.tvName.text = item.name
        holder.tvMemberId.text = item.id.toString()
        addTextChangedListener?.invoke(holder.etGrade1)
        addTextChangedListener?.invoke(holder.etGrade2)
        addTextChangedListener?.invoke(holder.etGrade3)
        addTextChangedListener?.invoke(holder.etGrade4)
        addTextChangedListener?.invoke(holder.etGrade5)
        addTextChangedListener?.invoke(holder.etGrade6)
        addTextChangedListener?.invoke(holder.etGrade7)

    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    class MemberViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.member_name)
        val tvMemberId = view.findViewById<TextView>(R.id.member_id)
        var etGrade1 = view.findViewById<EditText>(R.id.grade_1)
        var etGrade2 = view.findViewById<EditText>(R.id.grade_2)
        var etGrade3 = view.findViewById<EditText>(R.id.grade_3)
        var etGrade4 = view.findViewById<EditText>(R.id.grade_4)
        var etGrade5 = view.findViewById<EditText>(R.id.grade_5)
        var etGrade6 = view.findViewById<EditText>(R.id.grade_6)
        var etGrade7 = view.findViewById<EditText>(R.id.grade_7)
    }

}