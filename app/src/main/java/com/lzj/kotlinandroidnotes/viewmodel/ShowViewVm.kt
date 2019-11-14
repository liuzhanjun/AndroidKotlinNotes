package com.lzj.kotlinandroidnotes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShowViewVm : ViewModel() {
    //显示饼图数字代表的图
    val isShowNumberView: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }


}