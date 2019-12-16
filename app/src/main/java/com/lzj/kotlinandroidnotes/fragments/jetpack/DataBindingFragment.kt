package com.lzj.kotlinandroidnotes.fragments.jetpack


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.lzj.kotlinandroidnotes.R
import com.lzj.kotlinandroidnotes.adapter.MyRecyclerAdapter
import com.lzj.kotlinandroidnotes.views.recyclerview.BaseLinearDecoration
import kotlinx.android.synthetic.main.fragment_data_binding.*


/**
 * A simple [Fragment] subclass.
 *
 */
class DataBindingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_data_binding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("xxxxxxxxxxxxxxxxxxxxxxxx")
        initList()
    }

    private fun initList() {
        val adapter = MyRecyclerAdapter(context!!, getDatas())
        adapter.onClicked = { view, postion ->
            when(postion){
                0->{
                    println("=================")
                    this.findNavController().navigate(R.id.action_dataBindingFragment_to_dataBSimpleFragment)
                }
            }
        }
        list.adapter = adapter

        list.addItemDecoration(BaseLinearDecoration(context, LinearLayoutManager.VERTICAL))
        list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun getDatas(): MutableList<String> {
        return mutableListOf(
            "简单使用"
        )
    }



}
