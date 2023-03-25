package com.example.tableapplication.presentation

import androidx.lifecycle.ViewModel
import com.example.tableapplication.data.MemberListRepositoryImpl
import com.example.tableapplication.domain.EditMemberUseCase
import com.example.tableapplication.domain.GetMemberListUseCase
import com.example.tableapplication.domain.Member

class MainViewModel:ViewModel() {

    private val repository = MemberListRepositoryImpl
    private val getMemberListUseCase = GetMemberListUseCase(repository)
    private val editMemberUseCase = EditMemberUseCase(repository)

    var memberList = getMemberListUseCase.getMemberList()

    fun deleteShopItem(){
        //todo решить оставлять или нет
    }
    fun changeMemberState(){
        //todo доделать метод
    }


}