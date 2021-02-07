package com.example.evaluacion_intermedia_kotlin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evaluacion_intermedia_kotlin.ViewModel.TaskAdapter
import com.example.evaluacion_intermedia_kotlin.ViewModel.TaskViewModel
import com.example.evaluacion_intermedia_kotlin.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel : TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val adapter = TaskAdapter()
        binding.rvRecycler.adapter = adapter
        binding.rvRecycler.layoutManager = LinearLayoutManager(context)
        binding.rvRecycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.
        VERTICAL))

        viewModel.allTask.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }
        })
         adapter.selectedItem().observe(viewLifecycleOwner, Observer {
             it?.let {
                 val bundle = Bundle()
                 bundle.putInt("id",it.id)
                 findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
             }
         })
         binding.fab.setOnClickListener {
             findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                            }
                }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(context, "de elemino todo", Toast.LENGTH_LONG).show()
                viewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings -> {
                Toast.makeText(context, "Se elimino todo", Toast.LENGTH_LONG).show()
                ViewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/
    }
