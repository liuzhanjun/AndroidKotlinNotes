package com.lzj.kotlinandroidnotes.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.lzj.kotlinandroidnotes.R
import com.lzj.kotlinandroidnotes.adapter.MyRecyclerAdapter
import com.lzj.kotlinandroidnotes.viewmodel.ShowViewVm
import com.lzj.kotlinandroidnotes.views.recyclerview.BaseLinearDecoration
import kotlinx.android.synthetic.main.fragment_main_list.*


class MainListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("recycler onCreate=${recycler}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_list, container, false)

        println("recycler onCreateView=${recycler}")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        providerViewModel()
        initView()

    }

    lateinit var showViewVm: ShowViewVm
    private fun providerViewModel() {
        showViewVm = activity.run {
            ViewModelProviders.of(activity!!).get(ShowViewVm::class.java)
        }

    }

    private fun initView() {
        var datas = getDatas();
        val adapter = MyRecyclerAdapter(context!!, datas)
        adapter.onClicked = { view, postion ->
            showViewVm.isShowNumberView.value = postion
            when (postion) {
                0 -> {

                    this.findNavController().navigate(R.id.action_mainListFragment_to_showViewFragment)
                }
                1 -> {
                    this.findNavController().navigate(R.id.action_mainListFragment_to_showViewFragment)
                }
                2 -> {
                    this.findNavController().navigate(R.id.action_mainListFragment_to_showViewFragment)
                }
            }
        }

        recycler.adapter = adapter
        recycler.addItemDecoration(BaseLinearDecoration(context!!, LinearLayoutManager.VERTICAL).let {
            it.setmDivider(R.drawable.itme_divider)
            it
        })
        recycler.layoutManager = LinearLayoutManager(context!!, RecyclerView.VERTICAL, false)
    }

    fun getDatas(): MutableList<String> {
        return mutableListOf(
            "饼状图",
            "梯形图",
            "遥控器图",
            "其他"
        )
    }
}
