package com.example.applicationforoop_jy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applicationforoop_jy.databinding.ActivityMain2Binding
import com.example.applicationforoop_jy.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            // 클릭 이벤트 처리: 컴포넌트 통신
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnCompletion.setOnClickListener {


            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}