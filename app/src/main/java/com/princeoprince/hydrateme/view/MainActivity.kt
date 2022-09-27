package com.princeoprince.hydrateme.view

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.princeoprince.hydrateme.R
import com.princeoprince.hydrateme.databinding.ActivityMainBinding
import com.princeoprince.hydrateme.model.IntakeEntry
import com.princeoprince.hydrateme.model.IntakeModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model = IntakeModel()

    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val intake: Int = it.data?.getIntExtra("intake", 0) ?: 0
                val intakeEntry = IntakeEntry(intake)
                model.addEntry(intakeEntry)
                updateTodaysTotal()
            }
        }

    private fun updateTodaysTotal() {
        val total = model.getTodaysIntake()
        val text = "$total ml"
        binding.total.text = text
        if (model.isTodaysIntakeSufficient()) {
            binding.total.setTextColor(Color.GREEN)
            binding.message.setText(R.string.done)
        } else {
            binding.total.setTextColor(Color.RED)
            binding.message.setText(R.string.not_done)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createEntryFab.setOnClickListener {
            startForResult.launch(Intent(this@MainActivity, CreateEntryActivity::class.java))
        }

        updateTodaysTotal()
    }
}