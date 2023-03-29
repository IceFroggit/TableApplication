package com.example.tableapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tableapplication.data.MemberListRepositoryImpl
import com.example.tableapplication.domain.Member
import com.example.tableapplication.domain.UseCases.AddMemberListUseCase
import com.example.tableapplication.domain.UseCases.EditMemberUseCase
import com.example.tableapplication.domain.UseCases.GetMemberListUseCase
import com.example.tableapplication.domain.UseCases.GetMemberUseCase

class MainViewModel : ViewModel() {

    private val repository = MemberListRepositoryImpl

    private val getMemberUseCase = GetMemberUseCase(repository)
    private val getMemberListUseCase = GetMemberListUseCase(repository)
    private val editMemberUseCase = EditMemberUseCase(repository)
    private val addMemberListUseCase = AddMemberListUseCase(repository)

    val mMemberList = getMemberListUseCase.getMemberList()

    //Для обработки ошибок LiveData
    private val _errorInputPoint = MutableLiveData<Boolean>()
    private val _memberItemLD = MutableLiveData<Member>()

    val errorInputPoint: LiveData<Boolean>
        get() = _errorInputPoint

    //todo возможно лишнее НИХУЯ НЕ ЛИШНЕЕ (Хотя?)
    val memberItem: LiveData<Member>
        get() = _memberItemLD

    fun getMemberItem(memberItemId: Int) {
        _memberItemLD.value = getMemberUseCase.getMember(memberItemId)
    }

    fun deleteMemberItem() {
        //todo решить оставлять или нет
    }

    fun resetErrorInputPoint() {
        _errorInputPoint.value = false
    }

    fun editMemberItems(data: List<ArrayList<Int>>) {
        var placeInd = 1
        val memberList = mutableListOf<Member>()
        data.forEach {
            var sum = it[7]
            var id = it[8]
            var place = placeInd++
            it.removeAt(7)
            it.removeAt(7)
            var oldItem = getMemberUseCase.getMember(id)
            var item = oldItem.copy(pointList = (it.toList()), place = place, pointSum = sum)
            memberList.add(item)
        }
        addMemberListUseCase.addMemberList(memberList)
    }

    //todo refactor in parse fun
    fun updateList(list: ArrayList<ArrayList<Int>>) {
        var id = 0
        list.forEach {
            var sum = 0
            it.forEach { sum += it }
            it.add(sum)
            it.add(id++)
        }
        editMemberItems(list.sortedWith(compareBy({ -it[7] })))
    }
    companion object {
        const val MIN_POINT = 0
        const val MAX_POINT = 5
    }


}