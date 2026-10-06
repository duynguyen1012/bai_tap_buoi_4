package com.example.baitapbuoi4.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.baitapbuoi4.data.Student
import com.example.baitapbuoi4.data.StudentDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val studentDao = StudentDatabase.getInstance(application).studentDao()

    val studentList: StateFlow<List<Student>> = studentDao.getAllStudents()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addStudent(student: Student, onComplete: () -> Unit = {}) {
        viewModelScope.launch(Dispatchers.IO) {
            studentDao.insertStudent(student)
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }
    }

    fun updateStudent(student: Student, onComplete: () -> Unit = {}) {
        viewModelScope.launch(Dispatchers.IO) {
            studentDao.updateStudent(student)
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }
    }

    fun deleteStudent(student: Student, onComplete: () -> Unit = {}) {
        viewModelScope.launch(Dispatchers.IO) {
            studentDao.deleteStudent(student)
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }
    }

    suspend fun getStudentById(id: Int): Student? {
        return withContext(Dispatchers.IO) {
            studentDao.getStudentById(id)
        }
    }
}
