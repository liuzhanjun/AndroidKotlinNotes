package com.lzj.kotlinandroidnotes.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lzj.kotlinandroidnotes.room.entity.HeadTeacher

@Dao
interface TeacherDao {

    @Insert
    fun insertTeacher(teacher: HeadTeacher)

    @Delete
    fun delete(teacher: HeadTeacher)

    @Update
    fun updateTeacher(teacher: HeadTeacher)

    //通过ID删除某个数据
    @Query(value = "delete From headteacher where _id=:id")
    fun deleteById(id: Int)

    @Query("select * from headteacher")
    fun queryAll(): LiveData<List<HeadTeacher>>
}