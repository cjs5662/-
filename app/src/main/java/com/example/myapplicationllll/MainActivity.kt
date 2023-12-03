package com.example.myapplicationllll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val plannerFragment = planner()
    private val scheduleTodoFragment = ScheduleTodo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 기본 프래그먼트 설정
        replaceFragment(plannerFragment)

        // 바텀네비게이션 설정
        findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_main -> replaceFragment(plannerFragment)
                    R.id.nav_schedule_todo -> replaceFragment(scheduleTodoFragment)
                }
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit()
    }
}