package com.lzj.kotlinandroidnotes.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "classes",
    indices = arrayOf(Index("t_id",unique = true))
)
//与headTeacher  1对1关系

data class Classes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val _id: Int,
    @ColumnInfo(name = "class_name")
    val class_Name: String,
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    val student_count: Int,
    @ColumnInfo(name = "t_id")
    val t_id:Int
)