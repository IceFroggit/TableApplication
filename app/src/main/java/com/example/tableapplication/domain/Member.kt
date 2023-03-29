package com.example.tableapplication.domain

data class Member(
    val name: String,
    val pointList: List<Int>,
    var id: Int = UNDEFINED_ID,
    var pointSum: Int = UNDEFINED_POINT_SUM,
    var place: Int = UNDEFINED_PLACE,
) {
    companion object {
        const val UNDEFINED_ID = -1
        const val UNDEFINED_POINT_SUM = -10
        const val UNDEFINED_PLACE = -100
    }
}
