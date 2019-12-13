package com.lzj.kotlinandroidnotes.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "headteacher")
data class HeadTeacher(


    @ColumnInfo(name = "t_name")
    val t_name: String,
    @ColumnInfo(name = "t_age")
    val t_age: Int,

    @ColumnInfo(name = "t_sex")
    val t_sex: Int

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var _id: Int = 0

    override fun toString(): String {
        return "HeadTeacher(t_name='$t_name', t_age=$t_age, t_sex=$t_sex, _id=$_id)"
    }


}