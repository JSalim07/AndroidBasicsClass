package com.justincontreras.androidbasicsclass.ui.thirdpartialids2.firstApiRequest.model

data class Student(
    val name: String,
    val studentId: String,
    val hobby: String,
    val photoUrl: String
)

data class StudentsResponse(
    val students: List<Student>
)
