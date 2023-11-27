package com.example.myapplicationllll

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // 바텀네비게이션 설정
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                // 첫번째 버튼으로 홈 화면으로 이동
                R.id.nav_main -> {
                }
                R.id.nav_schedule_todo -> {
                    // 두 번째 버튼으로 일정, 할일 리스트 화면으로 이동
                    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.main_frame, Schedule_Todo())
                    transaction.commit()
                }
            }
            true
        }

    }
}
