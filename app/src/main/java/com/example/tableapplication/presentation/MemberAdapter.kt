package com.example.tableapplication.presentation


import android.graphics.Color
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
    var memberList = listOf<Member>()
        set(value) {
            field = value//todo notifyDataSetChanged bad practise
            notifyDataSetChanged()
        }
    val listOfCorrectPoints = ArrayList<ArrayList<Int>>()
    var listOfCorrectPoint1 = arrayListOf<Int>()
    var listOfCorrectPoint2 = arrayListOf<Int>()
    var listOfCorrectPoint3 = arrayListOf<Int>()
    var listOfCorrectPoint4 = arrayListOf<Int>()
    var listOfCorrectPoint5 = arrayListOf<Int>()
    var listOfCorrectPoint6 = arrayListOf<Int>()
    var listOfCorrectPoint7 = arrayListOf<Int>()
    init {
        listOfCorrectPoints.add(0, ArrayList<Int>())
        listOfCorrectPoints.add(1, ArrayList<Int>())
        listOfCorrectPoints.add(2, ArrayList<Int>())
        listOfCorrectPoints.add(3, ArrayList<Int>())
        listOfCorrectPoints.add(4, ArrayList<Int>())
        listOfCorrectPoints.add(5, ArrayList<Int>())
        listOfCorrectPoints.add(6, ArrayList<Int>())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.table_row, parent, false)
        val viewHolder = MemberViewHolder(view)
        setListeners(viewHolder, listOfCorrectPoints)
        return viewHolder
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val item = memberList[position]
        holder.tvName.text = item.name
        holder.tvMemberId.text = item.id.toString()
        //setListeners(holder, position)
        when (position) {
            0 -> holder.etGrade1.isEnabled = false
            1 -> holder.etGrade2.isEnabled = false
            2 -> holder.etGrade3.isEnabled = false
            3 -> holder.etGrade4.isEnabled = false
            4 -> holder.etGrade5.isEnabled = false
            5 -> holder.etGrade6.isEnabled = false
            6 -> holder.etGrade7.isEnabled = false
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position != 7) VIEW_TYPE_ITEM else VIEW_TYPE_TABLE_ENDING
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

        //todo delete later
        val id: Int
            get() = tvMemberId.text.toString().toInt()

    }

    fun setListeners(holder: MemberViewHolder, list: ArrayList<ArrayList<Int>>) {
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
        private var listOfCorrectPoints: ArrayList<ArrayList<Int>>,
        private val holder: MemberViewHolder,
    ) : TextWatcher {
        var isOnTextChanged = false
        var isOnTextChangedCorrect = false
        var previousPointCorrect = false
        private var count = 0

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            previousPointCorrect = false
            if (checkValidation(p0)) {
                previousPointCorrect = true
            }
        }

        override fun afterTextChanged(p0: Editable?) {
            currentEditText.isEnabled = true
            if (p0.toString() != "") {
                if (isOnTextChangedCorrect) {
                    if (!previousPointCorrect) {
                        listOfCorrectPoints[holder.id].add(p0.toString().toInt())
                        previousPointCorrect = true
                    }

                    //todo add constant
                    if (listOfCorrectPoints[holder.id].size == 6) {
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

                        holder.sumPoint.text =
                            (gr1 + gr2 + gr3 + gr4 + gr5 + gr6 + gr7).toString()
                    } else {
                        holder.sumPoint.text = ""
                    }
                } else {
                    if (previousPointCorrect && listOfCorrectPoints[holder.id].size != 0)
                        listOfCorrectPoints[holder.id].removeAt(0)
                }//todo delete later
            }else{//todo maybe лишний else
                if (previousPointCorrect && listOfCorrectPoints[holder.id].size != 0)
                    listOfCorrectPoints[holder.id].removeAt(0)
            }


        }


        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            isOnTextChanged = true
            if (p0.toString() != "")
                isOnTextChangedCorrect = false
            if (checkValidation(p0)) {
                //currentEditText.error = null
                isOnTextChangedCorrect = true
            } else {
                currentEditText.error = "invalid input"
                //todo вроде как когда я ввожу неправильное число выкидывает, чтобы номр закинул
                // currentEditText.isEnabled = false
                holder.sumPoint.text = ""
                isOnTextChangedCorrect = false
            }
        }

        private fun checkValidation(p0: CharSequence?): Boolean {
            var pointInt = -1
            var checkIsCorrect = true
            try {
                pointInt = Integer.parseInt(p0.toString().trim())
            } catch (e: Exception) {
                pointInt = -100
            }
            if (pointInt > MAX_POINT || pointInt < MIN_POINT)
                checkIsCorrect = false

            return checkIsCorrect
        }
    }

    companion object {
        const val MAX_POINT = 5
        const val MIN_POINT = 0
        const val VIEW_TYPE_ITEM = 100
        const val VIEW_TYPE_TABLE_ENDING = -100
    }

}


