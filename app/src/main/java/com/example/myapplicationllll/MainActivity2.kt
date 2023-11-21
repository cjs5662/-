package com.example.myapplicationllll


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CalendarView
import androidx.appcompat.app.AlertDialog


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val colorButton = findViewById<Button>(R.id.color_1)
        val calendarView: CalendarView = findViewById(R.id.calenderview)

        colorButton.setOnClickListener {

            val alertDialogBuilder = AlertDialog.Builder(MainActivity2)

            val inflater = LayoutInflater.from(MainActivity2)
            val dialogView = inflater.inflate(com.google.android.material.R.layout.mtrl_alert_dialog)

            dialogView.setBackgroundColor(Color.#D34989)


        }



        }
        )
    }
}