package com.lzj.kotlinandroidnotes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lzj.kotlinandroidnotes.views.PieView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pieView.textSize=25f
        val datas = mutableListOf<PieView.PieData>()
        datas.add(PieView.PieData(name = "一号产品", value = 99f, percentage = 0.2f, color = Color.RED))
        datas.add(PieView.PieData(name = "二号产品", value = 99f, percentage = 0.3f, color = Color.BLUE))
        datas.add(PieView.PieData(name = "三号产品", value = 99f, percentage = 0.1f, color = Color.YELLOW))
        datas.add(PieView.PieData(name = "四号产品", value = 99f, percentage = 0.4f, color = Color.GREEN))
        pieView.addData(datas)
    }
}
