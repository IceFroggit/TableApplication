package com.example.tableapplication.domain.UseCases

import com.example.tableapplication.domain.Member
import com.example.tableapplication.domain.MemberListRepository

class AddMemberListUseCase(private val repository: MemberListRepository) {
    fun addMemberList(list: List<Member>) {
        repository.addMemberList(list)
    }
}