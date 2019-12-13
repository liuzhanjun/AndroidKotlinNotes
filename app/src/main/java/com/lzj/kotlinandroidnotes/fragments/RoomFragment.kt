package com.lzj.kotlinandroidnotes.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.lzj.kotlinandroidnotes.R
import com.lzj.kotlinandroidnotes.room.AppDataBase
import com.lzj.kotlinandroidnotes.room.entity.HeadTeacher
import kotlinx.android.synthetic.main.fragment_room.*


class RoomFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = AppDataBase.get(context!!).TeacherDao()
        var index = 0
        add_teacher.setOnClickListener() {
            index++
            dao.insertTeacher(HeadTeacher("张${index}", 30, index % 2))
        }
        query_teacher.setOnClickListener() {
            val da = dao.queryAll()
            da.observe(viewLifecycleOwner, Observer {
                content.text = da.value.toString()
            })
        }
        delete_teacher.setOnClickListener {
            dao.deleteById(index - 1)
        }
    }
}
