package com.toystory.notget.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.toystory.notget.NotGetApplication
import com.toystory.notget.R
import com.toystory.notget.base.BaseActivity
import com.toystory.notget.data.Note
import com.toystory.notget.data.NoteViewModel
import com.toystory.notget.data.NoteViewModelFactory
import com.toystory.notget.databinding.ActivityCreateBinding

class CreateActivity : BaseActivity<ActivityCreateBinding>({ ActivityCreateBinding.inflate(it) }) {
    private val viewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NotGetApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActionBar()
        binding.timepickerStart.setIs24HourView(true)
        binding.timepickerEnd.setIs24HourView(true)
        binding.timepickerStart.setOnTimeChangedListener { _, hourOfDay, minute ->
            viewModel.setStartTime(hourOfDay, minute)
        }
        binding.timepickerEnd.setOnTimeChangedListener { _, hourOfDay, minute ->
            viewModel.setEndTime(hourOfDay, minute)
        }
        initObserver()
    }

    private fun initObserver() {
        viewModel.startTime.observe(this) {
            binding.timepickerStart.apply {
                hour = it.hour
                minute = it.minute
            }
        }
        viewModel.endTime.observe(this) {
            binding.timepickerEnd.apply {
                hour = it.hour
                minute = it.minute
            }
        }
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_cancel_30)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_create_note -> {
                val title = binding.editTextNote.text.toString()
                viewModel.insert(title)
                finish()
                true
            }
            else -> {
                Toast.makeText(this@CreateActivity, "뒤로가기", Toast.LENGTH_SHORT).show()
                finish()
                true
            }
        }
    }

}