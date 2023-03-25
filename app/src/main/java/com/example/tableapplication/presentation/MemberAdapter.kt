package com.example.tableapplication.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tableapplication.R
import com.example.tableapplication.domain.Member

class MemberAdapter : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
    var memberList = listOf<Member>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.table_row, parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val item = memberList[position]
        holder.tvName.text= item.name
        holder.tvMemberId.text = item.id.toString()
    }

    override fun getItemCount(): Int {
       return memberList.size
    }

    class MemberViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.member_name)
        val tvMemberId = view.findViewById<TextView>(R.id.member_id)
    }

}