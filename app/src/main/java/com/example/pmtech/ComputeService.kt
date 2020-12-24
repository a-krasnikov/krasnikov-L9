package com.example.pmtech

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class ComputeService : IntentService("ComputeService") {

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "ComputeService start")

        intent?.also {
            val arg1 = intent.getIntExtra(EXTRA_ARG1, 0)
            val arg2 = intent.getIntExtra(EXTRA_ARG2, 0)

            val result = compute(arg1, arg2)

            sendResult(result)
        }

        Log.d(TAG, "ComputeService finish")
    }

    private fun compute(arg1: Int, arg2: Int): Int {
        Thread.sleep(1000)
        return arg1 + arg2
    }

    private fun sendResult(result: Int) {
        Log.d(TAG, "ComputeService send Broadcast")
        val intent = Intent(ACTION_RESPONSE)
        intent.putExtra(EXTRA_RESULT, result)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
    }

    companion object {
        private const val TAG = "ComputeService"

        const val ACTION_RESPONSE = "com.example.pmtech.RESPONSE"
        const val EXTRA_ARG1 = "arg1"
        const val EXTRA_ARG2 = "arg2"
        const val EXTRA_RESULT = "result"

        fun startService(context: Context, arg1: Int, arg2: Int) {
            val intent = Intent(context, ComputeService::class.java)
            intent.putExtra(EXTRA_ARG1, arg1)
            intent.putExtra(EXTRA_ARG2, arg2)

            context.startService(intent)
        }
    }
}