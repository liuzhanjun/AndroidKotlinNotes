package com.lzj.kotlinandroidnotes

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.*
import com.lzj.kotlinandroidnotes.adapter.MyRecyclerAdapter
import com.lzj.kotlinandroidnotes.views.PieView
import com.lzj.kotlinandroidnotes.views.recyclerview.BaseGridDecoration
import com.lzj.kotlinandroidnotes.views.recyclerview.BaseLinearDecoration
import com.lzj.kotlinandroidnotes.views.recyclerview.setmDividerByXml
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }


}
