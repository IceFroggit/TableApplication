package com.example.tableapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tableapplication.domain.Member
import com.example.tableapplication.domain.Member.Companion.UNDEFINED_ID
import com.example.tableapplication.domain.MemberListRepository
import kotlin.random.Random

object MemberListRepositoryImpl:MemberListRepository{
    private val memberList = mutableListOf<Member>()
    private val memberListLD = MutableLiveData<List<Member>>()
    private var autoIncrementId = 0


    init {
        for (i in 1 until 8 ){
            val item = Member("Member$i", mutableListOf<Int>())
            addMember(item)
        }
    }


    override fun addMember(member: Member) {
        if(member.id == UNDEFINED_ID){
            member.id = autoIncrementId++
        }
        memberList.add(member)
        updateList()
    }

    override fun getMemberList(): LiveData<List<Member>> {
        return memberListLD
    }

    override fun editMemberUseCase(member: Member) {
        //todo придумать стоит ли добавлять еще один UseCase?
    }
    private fun updateList(){
        //todo возможно придется переделывать под toList, если использовать sortedSet
        memberListLD.value = memberList
    }

}