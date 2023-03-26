package com.example.tableapplication.presentation

import android.text.Editable
import android.text.TextWatcher
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
            field = value//todo notifyDataSetChanged bad practise
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.table_row, parent, false)
        val viewHolder = MemberViewHolder(view)
        viewHolder.setListeners()
        return viewHolder
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val item = memberList[position]
        holder.tvName.text = item.name
        holder.tvMemberId.text = item.id.toString()
        //addTextChangedListener?.invoke(holder.etGrade1)
        // addTextChangedListener?.invoke(holder.etGrade2)
        //// addTextChangedListener?.invoke(holder.etGrade3)
        // addTextChangedListener?.invoke(holder.etGrade4)
        // addTextChangedListener?.invoke(holder.etGrade5)
        // addTextChangedListener?.invoke(holder.etGrade6)
        // addTextChangedListener?.invoke(holder.etGrade7)

    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    class MemberViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {


        val tvName = view.findViewById<TextView>(R.id.member_name)
        val tvMemberId = view.findViewById<TextView>(R.id.member_id)
        var etGrade1 = view.findViewById<EditText>(R.id.grade_1)
        var etGrade2 = view.findViewById<EditText>(R.id.grade_2)
        var etGrade3 = view.findViewById<EditText>(R.id.grade_3)
        var etGrade4 = view.findViewById<EditText>(R.id.grade_4)
        var etGrade5 = view.findViewById<EditText>(R.id.grade_5)
        var etGrade6 = view.findViewById<EditText>(R.id.grade_6)
        var etGrade7 = view.findViewById<EditText>(R.id.grade_7)

        fun setListeners() {
            etGrade1.addTextChangedListener(customTextWatcher(etGrade1))
            etGrade2.addTextChangedListener(customTextWatcher(etGrade2))
            etGrade3.addTextChangedListener(customTextWatcher(etGrade3))
            etGrade4.addTextChangedListener(customTextWatcher(etGrade4))
            etGrade5.addTextChangedListener(customTextWatcher(etGrade5))
            etGrade6.addTextChangedListener(customTextWatcher(etGrade6))
            etGrade7.addTextChangedListener(customTextWatcher(etGrade7))
        }

    }

    class customTextWatcher(private val view: EditText) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            view.isEnabled = true
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val pointInt = try {
                p0.toString().trim().toInt()
            } catch (e: Exception) {
                -100
            }
            if (pointInt > MainViewModel.MAX_MEMBER_CNT || pointInt < MainViewModel.MIN_MEMBER_CNT) {
                view.error = "Message"
                view.isEnabled = false
            } else
                view.error = null
        }
    }

}