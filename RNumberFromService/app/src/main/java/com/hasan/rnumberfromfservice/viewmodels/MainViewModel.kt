package com.hasan.rnumberfromfservice.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
         var randomNumber = MutableLiveData<Int>()
        fun setData(rNumber:Int){
                randomNumber.value= rNumber
        }
        fun getData():MutableLiveData<Int>{
                return randomNumber
        }
}