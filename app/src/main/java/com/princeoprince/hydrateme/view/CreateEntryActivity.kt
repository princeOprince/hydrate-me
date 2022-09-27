package com.princeoprince.hydrateme.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.princeoprince.hydrateme.R
import com.princeoprince.hydrateme.databinding.ActivityCreateEntryBinding

private const val TAG = "CreateEntryActivity"

class CreateEntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(R.string.create_entry)

        binding.addEntryButton.setOnClickListener {
            val intakeMl = binding.intakeEditText.text.toString().toInt()
            val data = Intent()
            data.putExtra("intake", intakeMl)
            setResult(RESULT_OK, data)
            finish()
        }

    }
}