package com.lzj.kotlinandroidnotes.fragments

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs

import com.lzj.kotlinandroidnotes.R
import com.lzj.kotlinandroidnotes.databinding.FragmentShowViewBinding
import com.lzj.kotlinandroidnotes.viewmodel.ShowViewVm
import com.lzj.kotlinandroidnotes.views.PieView
import kotlinx.android.synthetic.main.fragment_show_view.*


class ShowViewFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    lateinit var binding: FragmentShowViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view=inflater.inflate(R.layout.fragment_show_view, container, false)
        binding = FragmentShowViewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        //获得ViewModel
        val showmodel = activity.run {
            ViewModelProviders.of(activity!!).get(ShowViewVm::class.java)
        }
        //绑定到布局
        binding.showView = showmodel;
        println("========" + showmodel.isShowNumberView.value)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPieView()
        showTrapeView()
        showRemote()
    }

    private fun showRemote() {
        remoteMenu.setOnClickedMenuListener {
            println("我点击了===" + it)
        }
    }

    private fun showTrapeView() {

    }

    private fun showPieView() {
        pieView.textSize = 25f
        val datas = mutableListOf<PieView.PieData>()
        datas.add(PieView.PieData(name = "一号产品", value = 99f, percentage = 0.2f, color = Color.RED))
        datas.add(PieView.PieData(name = "二号产品", value = 99f, percentage = 0.3f, color = Color.BLUE))
        datas.add(PieView.PieData(name = "三号产品", value = 99f, percentage = 0.1f, color = Color.YELLOW))
        datas.add(PieView.PieData(name = "四号产品", value = 99f, percentage = 0.4f, color = Color.GREEN))
        pieView.addData(datas)
    }

    override fun onResume() {
        super.onResume()

    }
}
