package com.example.pmtech

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.pmtech.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupFragment()
        setupBtnListener()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupFragment() {
        supportFragmentManager.commit {
            add<ResultFragment>(R.id.container)
        }
    }

    private fun setupBtnListener() {
        binding.startServiceBtn.setOnClickListener {
            startComputeService()
        }
    }

    private fun startComputeService() {
        if (binding.etArg1.text.isNullOrEmpty() || binding.etArg2.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                resources.getText(R.string.enter_all_args),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val arg1 = binding.etArg1.text.toString().toInt()
            val arg2 = binding.etArg2.text.toString().toInt()

            ComputeService.startService(this, arg1, arg2)
        }
    }
}