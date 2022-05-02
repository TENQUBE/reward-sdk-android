package com.tenqube.reward

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tenqube.reward.util.inTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                replace(R.id.container, MainFragment.newInstance())
            }
        }
    }
}