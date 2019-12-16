package com.lzj.kotlinandroidnotes.fragments.jetpack


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.lzj.kotlinandroidnotes.R
import com.lzj.kotlinandroidnotes.databinding.FragmentDataBsimpleBinding
import com.lzj.kotlinandroidnotes.databinding.FragmentShowViewBinding
import kotlinx.android.synthetic.main.fragment_data_bsimple.*


/**
 * A simple [Fragment] subclass.
 *
 */
data class User(
    var name: String,
    var password: ObservableField<String>,
    var age: Int
) {
    val data1: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
    val isVisibility: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(true)
    }
    val address: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
}

class DataBSimpleFragment : Fragment() {
    lateinit var user: User
    lateinit var binding: FragmentDataBsimpleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDataBsimpleBinding.inflate(layoutInflater, container, false)
        //关联生命周期
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var i = 0;
        user = User("小红", ObservableField("55555"), 12)
        binding.user = user
        //可观察数据
        button.setOnClickListener {
            user.password.set("44444${i++}")
        }
        //liveData
        button2.setOnClickListener {
            user.data1.value = "bs${i++}"
        }
        btn_visib.setOnClickListener {
            user.isVisibility.value = if (user.isVisibility.value!!) false else true
        }
    }

}
