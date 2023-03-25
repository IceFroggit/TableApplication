package com.example.tableapplication.domain.UseCases

import com.example.tableapplication.domain.Member
import com.example.tableapplication.domain.MemberListRepository

class GetMemberUseCase(private val memberListRepository: MemberListRepository) {
    fun getMember(id: Int): Member {
        return memberListRepository.getMember(id)
    }
}