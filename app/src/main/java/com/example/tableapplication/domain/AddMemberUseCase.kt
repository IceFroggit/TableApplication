package com.example.tableapplication.domain

class AddMemberUseCase(private val memberListRepository: MemberListRepository) {
    fun addMember(member: Member){
        memberListRepository.addMember(member)
    }
}