package com.lzj.kotlinandroidnotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lzj.kotlinandroidnotes.R
import com.lzj.kotlinandroidnotes.views.recyclerview.BaseRecyclerAdapter
import com.lzj.kotlinandroidnotes.views.recyclerview.BaseViewHolder
import kotlinx.android.synthetic.main.text_item.view.*
import java.util.*

class MyRecyclerAdapter : BaseRecyclerAdapter {
    internal var datas: MutableList<String> = mutableListOf()
    lateinit var context: Context

    constructor(context: Context, datas: MutableList<String>) : super() {
        this.context = context
        this.datas.addAll(datas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.text_item, parent, false);
        val holder = BaseViewHolder(view, context, this.onClicked, this.onLongPress)
        return holder
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.content.text = datas.get(position)
    }
}