package com.example.tableapplication.domain.UseCases

import androidx.lifecycle.LiveData
import com.example.tableapplication.domain.Member
import com.example.tableapplication.domain.MemberListRepository

class GetMemberListUseCase(private val memberListRepository: MemberListRepository) {
    fun getMemberList(): LiveData<List<Member>> {
        return memberListRepository.getMemberList()
    }
}