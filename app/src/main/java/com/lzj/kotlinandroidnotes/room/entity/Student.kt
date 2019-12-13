package com.lzj.kotlinandroidnotes.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "student",
    foreignKeys = arrayOf(
        ForeignKey(
            //外键表
            entity = Classes::class,
            //对应外键表的id
            parentColumns = ["_id"],
            //对应外键的字段
            childColumns = ["c_id"]
        )
    )
)
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val s_id: Int,
    @ColumnInfo
    val s_name: String,
    @ColumnInfo
    val c_id: Int
)