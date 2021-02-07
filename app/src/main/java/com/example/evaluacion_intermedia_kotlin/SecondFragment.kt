package com.example.evaluacion_intermedia_kotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.evaluacion_intermedia_kotlin.ViewModel.TaskViewModel
import com.example.evaluacion_intermedia_kotlin.databinding.FragmentSecondBinding
import com.example.evaluacion_intermedia_kotlin.model.TaskEntity
import java.util.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: TaskViewModel by activityViewModels()
    private var idTask: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idTask = it.getInt("id", -1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cantidad = 10

        /*
        binding.NumberPicker.setOnValueChangedListener(){
            if (NumberPicker != null) {
                NumberPicker.setMinValue(1)
                NumberPicker.setMaxValue(10)
                NumberPicker.warpSelectorWheel = true

            }

        }*/

        binding.edProducto.toString()

        binding.edPrecio.toString()

        binding.tvTotalactual.setText("VALOR ACTUAL : ${totalValor()}")

        binding.buttonSecond.setOnClickListener {
            saveTask()
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }


        viewModel.getTaskById(idTask).observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.edProducto.setText(it.nombre)
                binding.tvTotalactual.setText(it.precio)
                binding.cantidad.text = it.cantidad
                binding.NumberPicker.toString()
            }
        })
    }

    private fun totalValor(){
        val valorUnitario = binding.edPrecio.toString().toInt()
        val cantidad = 10

        if(valorUnitario != null) {
            valorUnitario * cantidad
        }else{
            Toast.makeText(context, "Ingrese parametros para Calcular", Toast.LENGTH_LONG).show()
        }
    }


    private fun saveTask(){
        val nombre = binding.edProducto.text.toString()
        val precio = binding.edPrecio.text.toString()
        val cantidad = binding.NumberPicker.toString()
        if (nombre.isEmpty() || precio.isEmpty()|| cantidad.isEmpty()){
            Toast.makeText(context,"Por Favor ingrese los datos nesecitados",
                    Toast.LENGTH_LONG).show()

        }else{
            if (idTask == -1){
                val newTask =TaskEntity(nombre = nombre, precio = precio, cantidad = cantidad)
                viewModel.insertTask(newTask)
            }else{
                val updateTask = TaskEntity(id = idTask, nombre = nombre, precio = precio,
                        cantidad = cantidad)
                viewModel.updateTask(updateTask)
            }
        }
    }
}