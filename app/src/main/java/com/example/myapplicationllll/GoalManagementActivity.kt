package com.example.myapplicationllll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationllll.databinding.ActivityGoalManagementBinding

class GoalManagementActivity : AppCompatActivity() {

    private val binding: ActivityGoalManagementBinding by lazy {
        ActivityGoalManagementBinding.inflate(layoutInflater)
    }

    private lateinit var goalsAdapterYear: GoalAdapter
    private lateinit var goalsAdapterMonth: GoalAdapter
    private lateinit var goalsAdapterDay: GoalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddGoalActivity::class.java))
        }

        goalsAdapterYear = GoalAdapter{ goal ->
            FirebaseUtil.deleteGoal(goal)
        }
        goalsAdapterMonth = GoalAdapter{ goal ->
            FirebaseUtil.deleteGoal(goal)
        }
        goalsAdapterDay = GoalAdapter{ goal ->
            FirebaseUtil.deleteGoal(goal)
        }

        binding.rcvYear.adapter = goalsAdapterYear
        binding.rcvMonth.adapter = goalsAdapterMonth
        binding.rcvDay.adapter = goalsAdapterDay

        binding.rcvYear.layoutManager = LinearLayoutManager(this)
        binding.rcvMonth.layoutManager = LinearLayoutManager(this)
        binding.rcvDay.layoutManager = LinearLayoutManager(this)

        FirebaseUtil.getGoals("year") { goals: List<Goal> -> goalsAdapterYear.setGoals(goals) }
        FirebaseUtil.getGoals("month") { goals -> goalsAdapterMonth.setGoals(goals)}
        FirebaseUtil.getGoals("day") { goals -> goalsAdapterDay.setGoals(goals)}
    }
}