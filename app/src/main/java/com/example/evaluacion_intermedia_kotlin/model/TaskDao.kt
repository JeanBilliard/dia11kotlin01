package com.example.evaluacion_intermedia_kotlin.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    // Insertar elementos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(Task : TaskEntity)

    // insertar lista de tareas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertAllTask(ListTask : List<TaskEntity>)

    // actualizar elemento
    @Update
    suspend fun  updateTask(Task: TaskEntity)

    //Borrar
    @Delete
    suspend fun deleteTask(task: TaskEntity)

    // para borarra todos los elementos
    @Query("DELETE FROM task_table")
    suspend fun  deleteAll()

    //traer todos los elementos
    @Query("SELECT * FROM task_table")
    fun  getAllTask() : LiveData<List<TaskEntity>>

    // traer por titulo y limita el resultado a 1
    @Query("SELECT * FROM task_table WHERE nombre = :nombre LIMIT 1")
    fun getTaskByTitle(nombre : String): LiveData<TaskEntity>


    //traer por id
    @Query("SELECT * FROM task_table WHERE id = :id")
    fun getTaskById(id: Int): LiveData<TaskEntity>

}