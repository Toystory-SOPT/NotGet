package com.toystory.notget.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.toystory.notget.NotGetApplication
import com.toystory.notget.R
import com.toystory.notget.base.BaseActivity
import com.toystory.notget.data.NoteViewModel
import com.toystory.notget.data.NoteViewModelFactory
import com.toystory.notget.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    private val viewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NotGetApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initGoActionBarBtn()
        viewModel.allNotes.observe(this) {
            Log.d("태그", it.toString())
        }
    }

    private fun initGoActionBarBtn() {
        binding.btnGoCreateActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateActivity::class.java)
            startActivity(intent)
        }
    }
}