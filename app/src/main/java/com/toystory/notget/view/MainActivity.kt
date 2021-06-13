package com.toystory.notget.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.toystory.notget.R
import com.toystory.notget.base.BaseActivity
import com.toystory.notget.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initGoActionBarBtn()
    }

    private fun initGoActionBarBtn() {
        binding.btnGoCreateActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateActivity::class.java)
            startActivity(intent)
        }
    }
}