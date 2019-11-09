package com.lzj.kotlinandroidnotes.views.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onClicked: ((view: View?, postion: Int) -> Unit)? = null
    var onLongPress: ((view: View?, postion: Int) -> Unit)? = null

}
