package com.example.tableapplication.domain

class EditMemberUseCase(private val memberListRepository: MemberListRepository) {
    fun editMember(member: Member){
        memberListRepository.editMemberUseCase(member)
    }
}