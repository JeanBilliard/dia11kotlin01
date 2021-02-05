package com.example.evaluacion_intermedia_kotlin.model

import android.content.Context
import android.os.strictmode.InstanceCountViolation
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [TaskEntity ::class], version = 1)
abstract class TaskDataBase : RoomDatabase(){

    abstract fun  getTaskDao() : TaskDao

    companion object {
        @Volatile
        private var INSTANCE : TaskDataBase? = null

        fun  getDatBase(context: Context) : TaskDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return  tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDataBase::class.java,
                    "task_db")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}