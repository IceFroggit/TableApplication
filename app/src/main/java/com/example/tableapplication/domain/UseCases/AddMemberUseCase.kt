package com.example.tableapplication.domain.UseCases

import com.example.tableapplication.domain.Member
import com.example.tableapplication.domain.MemberListRepository

class AddMemberUseCase(private val memberListRepository: MemberListRepository) {
    fun addMember(member: Member) {
        memberListRepository.addMember(member)
    }
}