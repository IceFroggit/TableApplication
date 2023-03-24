package com.example.tableapplication.domain

import androidx.lifecycle.LiveData

interface MemberListRepository {
    fun addMember(member: Member)
    fun getMemberList(): LiveData<List<Member>>
    fun editMemberUseCase(member: Member)
}