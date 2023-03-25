package com.example.tableapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tableapplication.data.MemberListRepositoryImpl
import com.example.tableapplication.domain.Member
import com.example.tableapplication.domain.UseCases.EditMemberUseCase
import com.example.tableapplication.domain.UseCases.GetMemberListUseCase
import com.example.tableapplication.domain.UseCases.GetMemberUseCase

class MainViewModel : ViewModel() {

    private val repository = MemberListRepositoryImpl

    private val getMemberUseCase = GetMemberUseCase(repository)
    private val getMemberListUseCase = GetMemberListUseCase(repository)
    private val editMemberUseCase = EditMemberUseCase(repository)

    val mMemberList = getMemberListUseCase.getMemberList()

    //Для обработки ошибок LiveData
    private val _errorInputPoint = MutableLiveData<Boolean>()
    private val _memberItemLD = MutableLiveData<Member>()

    val errorInputPoint: LiveData<Boolean>
        get() = _errorInputPoint
    //todo возможно лишнее
    val memberItem: LiveData<Member>
        get() = _memberItemLD

    fun getMemberItem(memberItemId: Int) {
        //todo maybe delete later
        _memberItemLD.value = getMemberUseCase.getMember(memberItemId)
    }

    fun editShopItem(inputPoint: String?) {
        val point = parsePoint(inputPoint)
        if (validateInput(point)){
            _memberItemLD.value?.let{
                //todo продумать как передавать иноформацию и менять значения списка баллов
                val item = it.copy()
                ///////////////////
                editMemberUseCase.editMember(item)

            }
        }
    }
    private fun parsePoint(inputPoint: String?): Int {
        return try{
            inputPoint?.trim()?.toInt() ?: -100
        } catch (e:Exception){
            -100
        }
    }

    private fun validateInput(point: Int): Boolean {
        var result = true
        if (point < MIN_MEMBER_CNT || point > MAX_MEMBER_CNT) {
            _errorInputPoint.value = true
            result = false
        }
        return result
    }


    fun deleteMemberItem() {
        //todo решить оставлять или нет
    }

    fun changeMemberState() {
        //todo доделать метод
    }

    fun resetErrorInputPoint() {
        _errorInputPoint.value = false
    }

    companion object {
        const val MIN_MEMBER_CNT = 0
        const val MAX_MEMBER_CNT = 7
    }


}