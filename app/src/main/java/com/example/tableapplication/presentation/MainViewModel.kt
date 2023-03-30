package com.example.tableapplication.presentation

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
    private val addMemberListUseCase = AddMemberListUseCase(repository)

    val mMemberList = getMemberListUseCase.getMemberList()

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
}