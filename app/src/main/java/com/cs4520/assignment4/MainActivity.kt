package com.cs4520.assignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cs4520.assignment4.data.OfflineDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OfflineDatabase.getDatabase(applicationContext)
    }
}