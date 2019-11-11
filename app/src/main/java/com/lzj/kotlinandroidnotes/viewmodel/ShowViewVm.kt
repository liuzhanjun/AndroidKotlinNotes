package com.lzj.kotlinandroidnotes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShowViewVm : ViewModel() {
    //是否显示饼图
    val isShowPieView: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

}