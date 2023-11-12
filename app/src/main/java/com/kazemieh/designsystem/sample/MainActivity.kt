package com.kazemieh.designsystem.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kazemieh.designsystem.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        SmallButtonSample(binding).smallButtonConfig()
    }
}