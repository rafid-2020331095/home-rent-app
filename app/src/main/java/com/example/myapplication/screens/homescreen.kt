package com.example.myapplication.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.addposting
import com.example.myapplication.databinding.ActivityHomescreenBinding
import com.example.myapplication.databinding.ActivityLoginactivityBinding
import com.example.myapplication.scrollhome
import com.example.myapplication.testactivity

class homescreen : AppCompatActivity() {
    private lateinit var binding: ActivityHomescreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.button13.setOnClickListener {
//            startActivity(Intent(this@homescreen, loginactivity::class.java))
//        }
            binding.button3.setOnClickListener {
                startActivity(Intent(this@homescreen, scrollhome::class.java))
            }
                binding.button2.setOnClickListener {
                    startActivity(Intent(this@homescreen, addposting::class.java))
                }

//        binding.button10.setOnClickListener {
//            startActivity(Intent(this@homescreen, testactivity::class.java))
//        }


    }
}