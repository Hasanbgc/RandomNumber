package com.hasan.rnumberfromfservice

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.concurrent.timerTask

class NumberService: Service() {

    private val timer: Timer = Timer()

    override fun onCreate() {
        super.onCreate()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startTimer()
        return START_NOT_STICKY
    }


    private fun startTimer() {
        timer.scheduleAtFixedRate( timerTask{
            run(){

                val intent =Intent().setAction("Random_Number_Generator")
                val randomNum = (50..100).random()

                intent.putExtra("Number",randomNum)
                sendBroadcast(intent)
            }
        },0,1000)
    }


    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        timer.purge()
        stopSelf()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopSelf()
    }
    
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}