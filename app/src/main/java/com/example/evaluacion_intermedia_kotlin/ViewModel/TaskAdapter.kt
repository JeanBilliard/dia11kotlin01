package com.example.evaluacion_intermedia_kotlin.ViewModel

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.evaluacion_intermedia_kotlin.databinding.TaskItemListBinding
import com.example.evaluacion_intermedia_kotlin.model.TaskEntity


class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskVH>(){
    private var listTask = listOf<TaskEntity>()

    private val selectedTaskItem = MutableLiveData<TaskEntity>()
    fun selectedItem():LiveData<TaskEntity> = selectedTaskItem

    fun  update(list: List<TaskEntity>){
        listTask = list
        notifyDataSetChanged()
    }
    inner class TaskVH(private val  binding: TaskItemListBinding):
        RecyclerView.ViewHolder(binding.root),View.OnClickListener{
        fun bind(task: TaskEntity){
            binding.tvNombre.text = task.nombre.toString()
            binding.tvCantidad.text = task.cantidad.toString()
            binding.tvTotal.text = task.cantidad.toString()
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
           selectedTaskItem.value = listTask[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        return TaskVH(TaskItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        val task = listTask[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = listTask.size

}