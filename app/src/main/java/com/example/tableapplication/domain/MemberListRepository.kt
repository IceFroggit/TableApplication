package com.example.tableapplication.domain

import androidx.lifecycle.LiveData

interface MemberListRepository {
    fun addMember(member: Member)

    fun getMemberList(): LiveData<List<Member>>

    fun getMember(id: Int): Member

    fun deleteMember(member: Member)

    fun editMember(member: Member)

    fun addMemberList(memberList: List<Member>)

}