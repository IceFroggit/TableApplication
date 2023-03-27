package com.example.tableapplication.presentation


import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
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

    var ExpAmtArray = ArrayList<String>()
    var ExpenseFinalTotal = 0
    var textviewTotalExpense: TextView? = null

    var sum = 0
    var listOfCorrectEditText = arrayListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.table_row, parent, false)
        val viewHolder = MemberViewHolder(view)
        listOfCorrectEditText.add(0)
        setListeners(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val item = memberList[position]
        holder.tvName.text = item.name
        holder.tvMemberId.text = item.id.toString()
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

    fun setListeners(holder: MemberViewHolder) {
        holder.etGrade1.addTextChangedListener(customTextWatcher(holder.etGrade1,
            holder.sumPoint,
            holder.place,
            listOfCorrectEditText,
            sum, holder))
        holder.etGrade2.addTextChangedListener(customTextWatcher(holder.etGrade1,
            holder.sumPoint,
            holder.place,
            listOfCorrectEditText,
            sum, holder))
        holder.etGrade3.addTextChangedListener(customTextWatcher(holder.etGrade1,
            holder.sumPoint,
            holder.place,
            listOfCorrectEditText,
            sum, holder))
        holder.etGrade4.addTextChangedListener(customTextWatcher(holder.etGrade1,
            holder.sumPoint,
            holder.place,
            listOfCorrectEditText,
            sum, holder))
        holder.etGrade5.addTextChangedListener(customTextWatcher(holder.etGrade1,
            holder.sumPoint,
            holder.place,
            listOfCorrectEditText,
            sum, holder))
        holder.etGrade6.addTextChangedListener(customTextWatcher(holder.etGrade1,
            holder.sumPoint,
            holder.place,
            listOfCorrectEditText,
            sum, holder))
        holder.etGrade7.addTextChangedListener(customTextWatcher(holder.etGrade1,
            holder.sumPoint,
            holder.place,
            listOfCorrectEditText,
            sum, holder))

    }

    class customTextWatcher(
        private val view: EditText,
        private val sumPoints: TextView,
        private val place: TextView,
        private var listOfCorrectPoints: ArrayList<Int>,
        private var sum: Int,
        private val holder: MemberViewHolder,
    ) : TextWatcher {
        var isOnTextChangedCorrect = false
        var pointInt = -1
        private var count = 0
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            view.isEnabled = true
            if (isOnTextChangedCorrect) {
                var oldSum = try {
                    sumPoints.text.toString().trim().toInt()
                } catch (e: Exception) {
                    0
                }
                listOfCorrectPoints[0]++
                //sumPoints.text = (oldSum + pointInt).toString()
                /*when (view.id) {
                    R.id.grade_1 -> listOfCorrectPoints.add(0, true)
                    R.id.grade_2 -> listOfCorrectPoints.add(1, true)
                    R.id.grade_3 -> listOfCorrectPoints.add(2, true)
                    R.id.grade_4 -> listOfCorrectPoints.add(3, true)
                    R.id.grade_5 -> listOfCorrectPoints.add(4, true)
                    R.id.grade_6 -> listOfCorrectPoints.add(5, true)
                    R.id.grade_7 -> listOfCorrectPoints.add(6, true)
                }*/
                //todo add constant
                //if (listOfCorrectPoints.size >= 6)
                if (listOfCorrectPoints[0] == 6) {
                    sumPoints.setTextColor(Color.BLACK)
                    sumPoints.text = ""

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

                    sumPoints.text = (gr1 + gr2 + gr3 + gr4 + gr5 + gr6 + gr7).toString()


                }

            } else {
                listOfCorrectPoints[0]--
            }
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (!p0.isNullOrBlank()) {
                pointInt = try {
                    p0.toString().trim().toInt()
                } catch (e: Exception) {
                    -100
                }
               /* if (pointInt == -100) {
                    when (view.id) {
                        R.id.grade_1 -> listOfCorrectPoints.removeAt(0)
                        R.id.grade_2 -> listOfCorrectPoints.removeAt(1)
                        R.id.grade_3 -> listOfCorrectPoints.removeAt(2)
                        R.id.grade_4 -> listOfCorrectPoints.removeAt(3)
                        R.id.grade_5 -> listOfCorrectPoints.removeAt(4)
                        R.id.grade_6 -> listOfCorrectPoints.removeAt(5)
                        R.id.grade_7 -> listOfCorrectPoints.removeAt(6)
                    }
                }*/
                //todo константы здесь определить возможно стоит
                if (pointInt > MainViewModel.MAX_POINT || pointInt < MainViewModel.MIN_POINT) {
                    view.error = "invalid input"
                    view.isEnabled = false
                    Toast.makeText(view.getContext(),
                        "Enter only number 0-5",
                        Toast.LENGTH_SHORT).show();
                } else {
                    view.error = null
                    isOnTextChangedCorrect = true
                }

            }


        }
    }

}


