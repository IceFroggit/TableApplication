package com.example.tableapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tableapplication.domain.Member
import com.example.tableapplication.domain.Member.Companion.UNDEFINED_ID
import com.example.tableapplication.domain.MemberListRepository

object MemberListRepositoryImpl : MemberListRepository {
    private val memberList = sortedSetOf<Member>({ p0, p1 -> p0.id.compareTo(p1.id) })
    private val memberListLD = MutableLiveData<List<Member>>()
    private var autoIncrementId = 0


    init {
        for (i in 1 until 8) {
            val item = Member("Member$i", mutableListOf<Int>())
            addMember(item)
        }
    }


    override fun addMember(member: Member) {
        if (member.id == UNDEFINED_ID) {
            member.id = autoIncrementId++
        }
        memberList.add(member)
        updateList()
    }

    override fun getMemberList(): LiveData<List<Member>> {
        return memberListLD
    }

    override fun getMember(id: Int): Member {
        return memberList.find {
            it.id == id
        } ?: throw RuntimeException("Element with id $id not found")
    }

    override fun deleteMember(member: Member) {
        memberList.remove(member)
        updateList()
    }

    override fun editMember(member: Member) {
        val oldElement = getMember(member.id)
        deleteMember(oldElement)
        addMember(member)
    }

    private fun updateList() {
        memberListLD.value = memberList.toList()
    }

}