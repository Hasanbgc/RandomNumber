package com.hasan.rnumberfromfservice

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.hasan.rnumberfromfservice.viewmodels.MainViewModel
import kotlin.properties.Delegates

class MyBroadcastReceiver :  BroadcastReceiver(){

    private var num:Int =0
    override fun onReceive(context: Context?, intent: Intent?) {
        /*if(context!=null)
        context.sendBroadcast(intent!!)*/

        num = intent!!.getIntExtra("Number",0)

    }
    fun getBrData():Int{
        return num
    }
}

