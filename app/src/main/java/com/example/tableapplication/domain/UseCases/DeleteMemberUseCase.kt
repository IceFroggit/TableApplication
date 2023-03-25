package com.example.tableapplication.domain.UseCases

import com.example.tableapplication.domain.Member
import com.example.tableapplication.domain.MemberListRepository

class DeleteMemberUseCase(private val memberListRepository: MemberListRepository) {
    fun deleteMember(member: Member) {
        memberListRepository.deleteMember(member)
    }
}