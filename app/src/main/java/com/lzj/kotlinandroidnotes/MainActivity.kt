package com.lzj.kotlinandroidnotes

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.*
import com.example.android.navigationadvancedsample.setupWithNavController
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
        initBottomNavgation()
    }

    private fun initBottomNavgation() {
        //navgation的id
        val navGraphIds = listOf(
            R.navigation.nav_graph,
            R.navigation.nav_os,
            R.navigation.nav_jetpack,
            R.navigation.nav_tools,
            R.navigation.nav_net
        )
        //item的id
        val itemIds = listOf(
            R.id.navigation_home,
            R.id.navigation_lists,
            R.id.navigation_warning,
            R.id.navigation_counts,
            R.id.navigation_personal
        )

        val navControllers =
            bottom_nav.setupWithNavController(
                itemIds,
                navGraphIds,
                supportFragmentManager,
                R.id.nav_host_container,
                intent
            )
        //绑定声明周期
        navControllers.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })

    }


}
