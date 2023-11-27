package com.example.myapplicationllll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import android.widget.CompoundButton
import androidx.core.app.NotificationCompat
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity

class planner : Fragment() {

    private lateinit var dayText: TextView
    private lateinit var calendarView: CalendarView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_planner, container, false)

        // 객체 생성
        dayText = view.findViewById(R.id.day_text)
        calendarView = view.findViewById(R.id.calenderview)

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
    return view
    }
}