package com.lzj.kotlinandroidnotes.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lzj.kotlinandroidnotes.room.dao.TeacherDao
import com.lzj.kotlinandroidnotes.room.entity.Classes
import com.lzj.kotlinandroidnotes.room.entity.HeadTeacher
import com.lzj.kotlinandroidnotes.room.entity.Student

@Database(entities = [HeadTeacher::class, Student::class, Classes::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun TeacherDao(): TeacherDao


    companion object {
        private var appDataBase: AppDataBase? = null
        @Synchronized
        fun get(context: Context): AppDataBase {
            if (appDataBase == null) {
                appDataBase = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "appDataBase"
                )
                    .addMigrations(migration1_2)
                    .allowMainThreadQueries().build()
            }
            return appDataBase!!
        }


    }

    object migration1_2 : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE headteacher ADD COLUMN t_sex Integer not null DEFAULT 0")
        }

    }
}