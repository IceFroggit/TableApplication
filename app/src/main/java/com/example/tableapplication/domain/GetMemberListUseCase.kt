package com.example.tableapplication.domain

import androidx.lifecycle.LiveData

class GetMemberListUseCase(private val memberListRepository: MemberListRepository) {
    fun getMemberList(): LiveData<List<Member>> {
        return memberListRepository.getMemberList()
    }
}