package com.example.applicationforoop_jy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewbinding.ViewBinding
import com.example.applicationforoop_jy.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            // 클릭 이벤트 처리: 컴포넌트 통신
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }



}