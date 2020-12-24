package com.example.pmtech

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.pmtech.databinding.FragmentResultBinding

class ResultFragment : Fragment(R.layout.fragment_result) {

    private var binding: FragmentResultBinding? = null

    private lateinit var receiver: BroadcastReceiver

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Create and register Receiver")
        setupReceiver()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Unregister Receiver")
        LocalBroadcastManager.getInstance(requireActivity()).unregisterReceiver(receiver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    // Create and register Receiver
    private fun setupReceiver() {
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val result = intent?.getIntExtra(ComputeService.EXTRA_RESULT, 0)

                binding?.tvResult?.text =
                    "${resources.getString(R.string.result_operation)}: $result"
            }
        }

        val filter = IntentFilter(ComputeService.ACTION_RESPONSE)
        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(receiver, filter)
    }

    companion object {
        private const val TAG = "ResultFragment"
    }
}