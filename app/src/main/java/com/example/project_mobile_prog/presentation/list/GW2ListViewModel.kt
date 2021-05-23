package com.example.project_mobile_prog.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GW2ListViewModel : ViewModel(){
    private val gw2List : MutableLiveData<List<GW2>> = MutableLiveData()

}