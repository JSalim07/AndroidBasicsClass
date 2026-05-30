package com.justincontreras.androidbasicsclass.ui.thirdpartialids2.firstApiRequest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justincontreras.androidbasicsclass.ui.thirdpartialids2.firstApiRequest.model.Student
import com.justincontreras.androidbasicsclass.ui.thirdpartialids2.firstApiRequest.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FirstApiRequestViewModel : ViewModel() {

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchStudents()
    }

    fun refreshStudents() {
        fetchStudents()
    }

    private fun fetchStudents() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _students.value = RetrofitClient.gistService.getStudents().students
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
