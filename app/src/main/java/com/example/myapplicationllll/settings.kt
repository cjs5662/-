package com.example.myapplicationllll

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView

class settings : Fragment() {

    private lateinit var textView: TextView
    private lateinit var fontButton1: Button
    private lateinit var fontButton2: Button
    private lateinit var fontButton3: Button
    private lateinit var fontButton4: Button

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var calendarView: CalendarView

    private fun changeFont(fontName: String) {
        val typeface = Typeface.createFromAsset(requireContext().assets, "font/$fontName")

        textView.typeface = typeface

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        fontButton1 = view.findViewById(R.id.font_1)
        fontButton2 = view.findViewById(R.id.font2)
        fontButton3 = view.findViewById(R.id.font3)
        fontButton4 = view.findViewById(R.id.font4)

        fontButton1.setOnClickListener { changeFont("bme.ttf") }
        fontButton2.setOnClickListener { changeFont("mugung.ttf") }
        fontButton3.setOnClickListener { changeFont("tmon.ttf") }
        fontButton4.setOnClickListener { changeFont("goyang.ttf") }




        // 초기 색상 설정
        calendarView.setBackgroundColor(Color.parseColor("#D34989"))

        button1.setOnClickListener {
            // 버튼을 클릭할 때마다 CalendarView의 배경색을 #D34989로 변경
            calendarView.setBackgroundColor(Color.parseColor("#D34989"))
        }

        // 초기 색상 설정
        calendarView.setBackgroundColor(Color.parseColor("#EDDE55"))

        button2.setOnClickListener {
            // 버튼을 클릭할 때마다 CalendarView의 배경색을 #D34989로 변경
            calendarView.setBackgroundColor(Color.parseColor("#EDDE55"))
        }

        // 초기 색상 설정
        calendarView.setBackgroundColor(Color.parseColor("#75EA7A"))

        button3.setOnClickListener {
            // 버튼을 클릭할 때마다 CalendarView의 배경색을 #D34989로 변경
            calendarView.setBackgroundColor(Color.parseColor("#75EA7A"))
        }

        // 초기 색상 설정
        calendarView.setBackgroundColor(Color.parseColor("#75EA7A"))

        button4.setOnClickListener {
            // 버튼을 클릭할 때마다 CalendarView의 배경색을 #D34989로 변경
            calendarView.setBackgroundColor(Color.parseColor("#75EA7A"))
        }

        return view
    }
}