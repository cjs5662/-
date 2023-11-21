package com.example.myapplicationllll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.CompoundButton
import android.widget.TextView
import androidx.core.app.NotificationCompat
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //객체 생성
        val dayText: TextView = findViewById(R.id.day_text)
        val calendarView: CalendarView = findViewById(R.id.calenderview)

        //날짜 형태
        val dateFormat: DateFormat = SimpleDateFormat("yyyy년 mm월 dd일")

        //date타입(오늘 날짜)
        val date: Date = Date(calendarView.date)

        //날짜 텍스트뷰에 담기
        //dayText.text = dateFormat.format(date)

        //CalenderView 날짜 변환 이벤트
        calendarView.setOnDateChangeListener { calendarView, year, month, daymonth ->

            //날짜 변수에 담기
            var day: String = "${year}년 ${month + 1}월 ${daymonth}일"

            //변수 텍스트뷰에 담기
            //dayText.text = day
        }

    }
}