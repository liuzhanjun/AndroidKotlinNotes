package com.lzj.kotlinandroidnotes.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.lzj.kotlinandroidnotes.R
import com.lzj.kotlinandroidnotes.adapter.MyRecyclerAdapter
import com.lzj.kotlinandroidnotes.views.recyclerview.BaseLinearDecoration
import kotlinx.android.synthetic.main.fragment_jet_pack.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class JetPackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jet_pack, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initList()

    }

    private fun initList() {
        val adapter = MyRecyclerAdapter(context!!, getDatas())
        adapter.onClicked = { view, postion ->
            when (postion) {
                0 -> {
                    this.findNavController().navigate(R.id.action_jetPackFragment_to_dataBindingFragment)
                }
            }
        }
        list.adapter = adapter
        list.addItemDecoration(BaseLinearDecoration(context, LinearLayoutManager.VERTICAL))
        list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun getDatas(): MutableList<String> {
        return mutableListOf(
            "databind",
            "LiveData",
            "ViewModel",
            "Lifecycles"
        )
    }


}
