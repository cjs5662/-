package com.example.myapplicationllll

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentTransaction

import android.widget.CalendarView
import android.widget.TextView
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat

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
        
        val calendarView: CalendarView = findViewById(R.id.cal)
        val textView: TextView = findViewById(R.id.t_text)gi

        //날짜 형태
        val dateFormat: DateFormat = SimpleDateFormat("yyyy년 MM월 dd일")

        //today date
        val date: Date = Date(calendarView.date)

        //날짜변환
        calendarView.setOnDateChangeListener( calendarView,

    }
}
