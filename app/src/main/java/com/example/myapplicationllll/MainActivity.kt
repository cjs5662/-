package com.example.myapplicationllll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 기본화면->일정, 할일 리스트로 가는 버튼
        val btnScheduleTodo: Button = findViewById(R.id.btn_scheduletodo)
        btnScheduleTodo.setOnClickListener {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frame, Schedule_Todo())
            transaction.commit()
        }
    }
}
