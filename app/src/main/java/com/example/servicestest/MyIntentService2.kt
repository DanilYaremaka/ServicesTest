package com.example.servicestest

import android.annotation.SuppressLint
import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.Message
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyIntentService2 : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        setIntentRedelivery(true)
        log("onCreate")
    }

    override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent")
        val page = intent?.getIntExtra(PAGE, 0) ?: 0
        for (i in 0 until 3) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyIntentService: $message")
    }


    companion object {

        private const val NAME = "MyIntentService"
        private const val PAGE = "page"
        fun newIntent(context: Context, page: Int): Intent {
            return Intent(
                context,
                MyIntentService2::class.java
            ).apply {
                putExtra(PAGE, page)
            }
        }
    }
}