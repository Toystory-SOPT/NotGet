package com.toystory.notget.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.toystory.notget.R
import com.toystory.notget.base.BaseActivity
import com.toystory.notget.databinding.ActivityCreateBinding

class CreateActivity : BaseActivity<ActivityCreateBinding>({ ActivityCreateBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActionBar()
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
                Toast.makeText(this@CreateActivity, "저장", Toast.LENGTH_SHORT).show()
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