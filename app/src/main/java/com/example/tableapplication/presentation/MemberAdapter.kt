package com.example.tableapplication.presentation


import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.tableapplication.R
import com.example.tableapplication.domain.Member


class MemberAdapter : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    private var _editIsFinished = MutableLiveData<Boolean>()
    val editIsFinished: LiveData<Boolean>
        get() = _editIsFinished

    var memberList = listOf<Member>()
    var dataIsFull = mutableListOf(false)
    val listOfCorrectPoints = ArrayList<ArrayList<Int>>()


    init {
        listOfCorrectPoints.add(0, arrayListOf(0))
        listOfCorrectPoints.add(1, arrayListOf(0))
        listOfCorrectPoints.add(2, arrayListOf(0))
        listOfCorrectPoints.add(3, arrayListOf(0))
        listOfCorrectPoints.add(4, arrayListOf(0))
        listOfCorrectPoints.add(5, arrayListOf(0))
        listOfCorrectPoints.add(6, arrayListOf(0))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.table_row, parent, false)
        val viewHolder = MemberViewHolder(view)
        //todo зачем это if (!dataIsFull[0])
        setListeners(viewHolder, listOfCorrectPoints)
        return viewHolder
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val item = memberList[position]
        with(holder) {
            tvName.text = item.name
            tvMemberId.text = item.id.toString()
            place.text = item.place.toString()
            sumPoint.text = item.pointSum.toString()
            Log.d("TAG", sumPoint.text.toString())
            if (dataIsFull[0]) {
                etGrade1.isEnabled = true
                etGrade2.isEnabled = true
                etGrade3.isEnabled = true
                etGrade4.isEnabled = true
                etGrade5.isEnabled = true
                etGrade6.isEnabled = true
                etGrade7.isEnabled = true
                etGrade1.setText(item.pointList[0].toString())
                etGrade2.setText(item.pointList[1].toString())
                etGrade3.setText(item.pointList[2].toString())
                etGrade4.setText(item.pointList[3].toString())
                etGrade5.setText(item.pointList[4].toString())
                etGrade6.setText(item.pointList[5].toString())
                etGrade7.setText(item.pointList[6].toString())
            }
            when (position) {
                0 -> etGrade1.isEnabled = false
                1 -> etGrade2.isEnabled = false
                2 -> etGrade3.isEnabled = false
                3 -> etGrade4.isEnabled = false
                4 -> etGrade5.isEnabled = false
                5 -> etGrade6.isEnabled = false
                6 -> etGrade7.isEnabled = false
            }
        }
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    class MemberViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val tvName: TextView = view.findViewById(R.id.member_name)
        val tvMemberId: TextView = view.findViewById(R.id.member_id)
        val etGrade1: EditText = view.findViewById(R.id.grade_1)
        val etGrade2: EditText = view.findViewById(R.id.grade_2)
        val etGrade3: EditText = view.findViewById(R.id.grade_3)
        val etGrade4: EditText = view.findViewById(R.id.grade_4)
        val etGrade5: EditText = view.findViewById(R.id.grade_5)
        val etGrade6: EditText = view.findViewById(R.id.grade_6)
        val etGrade7: EditText = view.findViewById(R.id.grade_7)
        val sumPoint: TextView = view.findViewById(R.id.sum_of_points)
        val place: TextView = view.findViewById(R.id.place)
        val id: Int
            get() = tvMemberId.text.toString().toInt()
    }

    private fun setListeners(holder: MemberViewHolder, list: ArrayList<ArrayList<Int>>) {
        holder.etGrade1.addTextChangedListener(PointTextWatcher(holder.etGrade1,
            list, holder, _editIsFinished))
        holder.etGrade2.addTextChangedListener(PointTextWatcher(holder.etGrade2,
            list,
            holder, _editIsFinished))
        holder.etGrade3.addTextChangedListener(PointTextWatcher(holder.etGrade3,
            list,
            holder, _editIsFinished))
        holder.etGrade4.addTextChangedListener(PointTextWatcher(holder.etGrade4,
            list,
            holder, _editIsFinished))
        holder.etGrade5.addTextChangedListener(PointTextWatcher(holder.etGrade5,
            list,
            holder, _editIsFinished))
        holder.etGrade6.addTextChangedListener(PointTextWatcher(holder.etGrade6,
            list,
            holder, _editIsFinished))
        holder.etGrade7.addTextChangedListener(PointTextWatcher(holder.etGrade7,
            list,
            holder, _editIsFinished))

    }

    class PointTextWatcher(
        private val currentEditText: EditText,
        private var listOfCorrectPoints: ArrayList<ArrayList<Int>>,
        private val holder: MemberViewHolder,
        private var _editIsFinished: MutableLiveData<Boolean>,
    ) : TextWatcher {
        private var isOnTextChanged = false
        private var isOnTextChangedCorrect = false
        private var previousPointCorrect = false

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            previousPointCorrect = checkValidation(p0)
        }

        override fun afterTextChanged(p0: Editable?) {
            if (isOnTextChanged) {
                if (isOnTextChangedCorrect) {
                    if (!previousPointCorrect && listOfCorrectPoints[holder.id].size != MAX_POINT_LIST_SIZE + 1)
                        listOfCorrectPoints[holder.id].add(p0.toString().toInt())

                    if (listOfCorrectPoints[holder.id].size == MAX_POINT_LIST_SIZE + 1) {
                        var sum = parsePointSum(holder)
                        holder.sumPoint.text = sum.toString()
                    } else {
                        holder.sumPoint.text = ""
                    }
                    var flag = false
                    for (it in listOfCorrectPoints) {
                        if (it.size == 7)
                            flag = true
                        else {
                            flag = false
                            break
                        }
                    }
                    if (flag)
                        _editIsFinished.value = flag
                } else {
                    if (previousPointCorrect && listOfCorrectPoints[holder.id].size - 1 != 0)
                        listOfCorrectPoints[holder.id].removeAt(0)
                }
            }
            isOnTextChanged != isOnTextChanged

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            isOnTextChanged = true
            if (checkValidation(p0)) {
                isOnTextChangedCorrect = true
            } else {
                currentEditText.error = "invalid input"
                isOnTextChangedCorrect = false
            }
        }

        private fun checkValidation(p0: CharSequence?): Boolean {
            var checkIsCorrect = true
            val pointInt: Int = try {
                Integer.parseInt(p0.toString().trim())
            } catch (e: Exception) {
                INCORRECT_POINT
            }
            if (pointInt > MAX_POINT || pointInt < MIN_POINT)
                checkIsCorrect = false

            return checkIsCorrect
        }

        private fun parsePointSum(holder: MemberViewHolder): Int {
            with(holder) {
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
                listOfCorrectPoints[holder.id] = arrayListOf(gr1, gr2, gr3, gr4, gr5, gr6, gr7)

                return gr1 + gr2 + gr3 + gr4 + gr5 + gr6 + gr7
            }
        }
    }

    companion object {
        const val MAX_POINT = 5
        const val MIN_POINT = 0
        const val INCORRECT_POINT = -100
        const val MAX_POINT_LIST_SIZE = 6
    }
}
