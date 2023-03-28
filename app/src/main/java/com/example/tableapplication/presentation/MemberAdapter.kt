package com.example.tableapplication.presentation


import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.tableapplication.R
import com.example.tableapplication.domain.Member


class MemberAdapter : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
    //todo delete later
    var addTextChangedListener: ((EditText) -> Unit)? = null
    //todo может лучшее сделать решение
    val _errorInput = MutableLiveData<Boolean>()
    val errorInput: LiveData<Boolean>
        get() = _errorInput

    var memberList = listOf<Member>()
        set(value) {
            field = value//todo notifyDataSetChanged bad practise
            notifyDataSetChanged()
        }
    var listOfCorrectPoint1 = arrayListOf<Boolean>()
    var listOfCorrectPoint2 = arrayListOf<Boolean>()
    var listOfCorrectPoint3 = arrayListOf<Boolean>()
    var listOfCorrectPoint4 = arrayListOf<Boolean>()
    var listOfCorrectPoint5 = arrayListOf<Boolean>()
    var listOfCorrectPoint6 = arrayListOf<Boolean>()
    var listOfCorrectPoint7 = arrayListOf<Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.table_row, parent, false)
        val viewHolder = MemberViewHolder(view)
        // setListeners(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val item = memberList[position]
        holder.tvName.text = item.name
        holder.tvMemberId.text = item.id.toString()
        setListeners(holder, position)
        //todo может придумаю лучшую реализацию
        when (item.id) {
            0 -> holder.etGrade1.isEnabled = false
            1 -> holder.etGrade2.isEnabled = false
            2 -> holder.etGrade3.isEnabled = false
            3 -> holder.etGrade4.isEnabled = false
            4 -> holder.etGrade5.isEnabled = false
            5 -> holder.etGrade6.isEnabled = false
            6 -> holder.etGrade7.isEnabled = false
        }
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
        var sumPoint = view.findViewById<TextView>(R.id.sum_of_points)
        var place = view.findViewById<TextView>(R.id.place)

    }

    fun setListeners(holder: MemberViewHolder, position: Int) {
        var list = when (position) {
            0 -> listOfCorrectPoint1
            1 -> listOfCorrectPoint2
            2 -> listOfCorrectPoint3
            3 -> listOfCorrectPoint4
            4 -> listOfCorrectPoint5
            5 -> listOfCorrectPoint6
            6 -> listOfCorrectPoint7
            else -> throw RuntimeException("Wrong postition")
        }
        holder.etGrade1.addTextChangedListener(customTextWatcher(holder.etGrade1,
            list, holder))
        holder.etGrade2.addTextChangedListener(customTextWatcher(holder.etGrade2,
            list,
            holder))
        holder.etGrade3.addTextChangedListener(customTextWatcher(holder.etGrade3,
            list,
            holder))
        holder.etGrade4.addTextChangedListener(customTextWatcher(holder.etGrade4,
            list,
            holder))
        holder.etGrade5.addTextChangedListener(customTextWatcher(holder.etGrade5,
            list,
            holder))
        holder.etGrade6.addTextChangedListener(customTextWatcher(holder.etGrade6,
            list,
            holder))
        holder.etGrade7.addTextChangedListener(customTextWatcher(holder.etGrade7,
            list,
            holder))

    }

    class customTextWatcher(
        private val currentEditText: EditText,
        private var listOfCorrectPoints: ArrayList<Boolean>,
        private val holder: MemberViewHolder
    ) : TextWatcher {
        var isOnTextChangedCorrect = false
        var previousPointCorrect = false
        var pointInt = -1
        private var count = 0

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (p0.isNullOrEmpty() || p0.isNullOrBlank()) {
                previousPointCorrect = false
            } else
                previousPointCorrect = true
        }

        override fun afterTextChanged(p0: Editable?) {
            currentEditText.isEnabled = true
            if (isOnTextChangedCorrect) {
                if (!previousPointCorrect)
                    listOfCorrectPoints.add(true)
                //todo add constant
                //if (listOfCorrectPoints.size >= 6)
                if (listOfCorrectPoints.size == 6) {
                    holder.sumPoint.setTextColor(Color.BLACK)
                    val gr1 =
                        if (holder.etGrade1.text.toString() != "") holder.etGrade1.text.toString()
                            .toInt() else 0
                    val gr2 =
                        if (holder.etGrade2.text.toString() != "") holder.etGrade2.text.toString()
                            .toInt() else 0
                    val gr3 =
                        if (holder.etGrade3.text.toString() != "") holder.etGrade3.text.toString()
                            .toInt() else 0
                    val gr4 =
                        if (holder.etGrade4.text.toString() != "") holder.etGrade4.text.toString()
                            .toInt() else 0
                    val gr5 =
                        if (holder.etGrade5.text.toString() != "") holder.etGrade5.text.toString()
                            .toInt() else 0
                    val gr6 =
                        if (holder.etGrade6.text.toString() != "") holder.etGrade6.text.toString()
                            .toInt() else 0
                    val gr7 =
                        if (holder.etGrade7.text.toString() != "") holder.etGrade7.text.toString()
                            .toInt() else 0

                    holder.sumPoint.text = (gr1 + gr2 + gr3 + gr4 + gr5 + gr6 + gr7).toString()
                }
            } else {
                holder.sumPoint.text = ""
                if (!previousPointCorrect && listOfCorrectPoints.size != 0)
                    listOfCorrectPoints.removeAt(0)

            }
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (!p0.isNullOrBlank() || !p0.isNullOrEmpty()) {
                try {
                    pointInt = p0.toString().trim().toInt()
                } catch (e: Exception) {
                    pointInt = -100
                    isOnTextChangedCorrect = false
                }
                //todo константы здесь определить возможно стоит
                if (pointInt > MAX_POINT || pointInt < MIN_POINT) {
                    currentEditText.error = "invalid input"
                    //  currentEditText.isEnabled = false
                    Toast.makeText(currentEditText.getContext(),
                        "Enter only number 0-5",
                        Toast.LENGTH_SHORT).show();
                    isOnTextChangedCorrect = false
                } else {
                    // currentEditText.error = null
                    isOnTextChangedCorrect = true
                }
            } else {

            }
            if (p0.toString() == "" && listOfCorrectPoints.size != 0)
                listOfCorrectPoints.removeAt(0)
            if (listOfCorrectPoints.size != 6)
                holder.sumPoint.text = ""
        }
    }

    companion object {
        const val MAX_POINT = 5
        const val MIN_POINT = 0
    }

}


