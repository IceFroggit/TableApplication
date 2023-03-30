package com.example.tableapplication.presentation


import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.tableapplication.R
import com.example.tableapplication.domain.Member


class MemberAdapter : RecyclerView.Adapter<MemberViewHolder>() {

    private var _editIsFinished = MutableLiveData<Boolean>()
    val editIsFinished: LiveData<Boolean>
        get() = _editIsFinished

    var memberList = listOf<Member>()
    var isFirstRun = true
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
        setListeners(viewHolder, listOfCorrectPoints)
        return viewHolder
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val item = memberList[position]
        with(holder) {
            tvName.text = item.name
            tvMemberId.text = item.id.toString()
            if(!isFirstRun){
                place.text = item.place.toString()
                sumPoint.text = item.pointSum.toString()
            }
            for (i in 0 until etGradeList.size) {
                etGradeList[i].isEnabled = (i != position)
                if (!isFirstRun)
                    etGradeList[i].setText(item.pointList[i].toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    private fun setListeners(holder: MemberViewHolder, list: ArrayList<ArrayList<Int>>) {
        with(holder) {
            etGradeList[0].addTextChangedListener(
                PointTextWatcher(etGradeList[0], list, holder, _editIsFinished)
            )
            etGradeList[1].addTextChangedListener(
                PointTextWatcher(etGradeList[1], list, holder, _editIsFinished)
            )
            etGradeList[2].addTextChangedListener(
                PointTextWatcher(etGradeList[2], list, holder, _editIsFinished)
            )
            etGradeList[3].addTextChangedListener(
                PointTextWatcher(etGradeList[3], list, holder, _editIsFinished)
            )
            etGradeList[4].addTextChangedListener(
                PointTextWatcher(etGradeList[4], list, holder, _editIsFinished)
            )
            etGradeList[5].addTextChangedListener(
                PointTextWatcher(etGradeList[5], list, holder, _editIsFinished)
            )
            etGradeList[6].addTextChangedListener(
                PointTextWatcher(etGradeList[6], list, holder, _editIsFinished)
            )
        }
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
                    if (!previousPointCorrect && listOfCorrectPoints[holder.id].size != MAX_POINT_LIST_SIZE)
                        listOfCorrectPoints[holder.id].add(p0.toString().toInt())

                    if (listOfCorrectPoints[holder.id].size == MAX_POINT_LIST_SIZE) {
                        holder.sumPoint.text = parsePointSum(holder).toString()
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
                    if (flag) _editIsFinished.value = flag
                } else {
                    if (previousPointCorrect && listOfCorrectPoints[holder.id].size - 1 != 0) {
                        listOfCorrectPoints[holder.id].removeAt(0)
                        holder.sumPoint.text = ""
                    }
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
                val list = ArrayList<Int>()
                var sum = 0
                etGradeList.forEach {
                    val point =
                        if (it.text.toString() != "")
                            it.text.toString().toInt()
                        else 0
                    sum += point
                    list.add(point)
                }
                listOfCorrectPoints[id] = list
                return sum
            }
        }
    }

    companion object {
        const val MAX_POINT = 5
        const val MIN_POINT = 0
        const val INCORRECT_POINT = -100
        const val MAX_POINT_LIST_SIZE = 7
    }
}
