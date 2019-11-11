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
//        pieView.textSize=25f
//        val datas = mutableListOf<PieView.PieData>()
//        datas.add(PieView.PieData(name = "一号产品", value = 99f, percentage = 0.2f, color = Color.RED))
//        datas.add(PieView.PieData(name = "二号产品", value = 99f, percentage = 0.3f, color = Color.BLUE))
//        datas.add(PieView.PieData(name = "三号产品", value = 99f, percentage = 0.1f, color = Color.YELLOW))
//        datas.add(PieView.PieData(name = "四号产品", value = 99f, percentage = 0.4f, color = Color.GREEN))
//        pieView.addData(datas)
        var datas = getDatas();
        val adapter = MyRecyclerAdapter(this, datas)
        adapter.onClicked = { view, postion ->
            println("点击了" + postion)
            adapter.datas.removeAt(postion)
            adapter.notifyDataSetChanged()
        }
        adapter.onLongPress = { view, postion ->
            println("长按" + postion)
        }
        recycler.adapter = adapter

        recycler.addItemDecoration(BaseGridDecoration(recycler.context,RecyclerView.HORIZONTAL, 5).let {
            it.setmDivider(R.drawable.itme_divider)
            it
        })

        recycler.layoutManager = GridLayoutManager(this, 5, RecyclerView.HORIZONTAL, false)
    }

    fun getDatas(): MutableList<String> {
        return mutableListOf(
            "xinlang0",
            "xinlang1",
            "tengxun2",
            "baidu3",
            "xinlang4",
            "tengxun5",
            "baidu6",
            "xinlang7",
            "tengxun8",
            "baidu9",
            "xinlang",
            "tengxun",
            "tengxun",
            "tengxun",
            "baidu",
            "xinlang",
            "tengxun",
            "baidu",
            "xinlang",
            "tengxun",
            "baidu",
            "xinlang",
            "tengxun",
            "baidu",
            "xinlang",
            "tengxun",
            "tengxun",
            "baidu",
            "xinlang",
            "tengxun",
            "baidu",
            "xinlang",
            "tengxun",
            "baidu",
            "xinlang",
            "tengxun",
            "al"


        )
    }
}
