package com.example.evaluacion_intermedia_kotlin.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.evaluacion_intermedia_kotlin.model.TaskDao
import com.example.evaluacion_intermedia_kotlin.model.TaskDataBase
import com.example.evaluacion_intermedia_kotlin.model.TaskEntity
import com.example.evaluacion_intermedia_kotlin.model.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel (application: Application) :
    AndroidViewModel(application){

    //variable que representa al repositorio

    private val repository : TaskRepository

    // LiveData expone info

    val allTask : LiveData<List<TaskEntity>>

    init {
        val  TaskDao = TaskDataBase.getDatBase(application).getTaskDao()
        repository = TaskRepository(TaskDao)
        allTask = repository.ListAllTask
    }
    fun insertTask(task: TaskEntity) = viewModelScope.launch {
        repository.insertTask(task)
    }
}