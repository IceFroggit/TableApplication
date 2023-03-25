package com.example.tableapplication.domain.UseCases

import com.example.tableapplication.domain.Member
import com.example.tableapplication.domain.MemberListRepository

class EditMemberUseCase(private val memberListRepository: MemberListRepository) {
    fun editMember(member: Member) {
        memberListRepository.editMember(member)
    }
}