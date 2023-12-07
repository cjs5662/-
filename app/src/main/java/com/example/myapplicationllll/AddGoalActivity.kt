package com.example.myapplicationllll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplicationllll.databinding.ActivityAddGoalBinding

class AddGoalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddGoalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnExit.setOnClickListener {
            startActivity(Intent(this, GoalManagementActivity::class.java))
        }

        binding.btnCompletion.setOnClickListener {
            val goal = binding.txtGoal.text.toString()
            val content = binding.txtContent.text.toString()
            val reward = binding.txtReward.text.toString()
            val period = when (binding.spinner.selectedItemPosition) {
                0 -> "year"
                1 -> "month"
                else -> "day"
            }

            if (goal.isNotEmpty() && content.isNotEmpty()) {
                // Firebase에 데이터 저장
                val goal = Goal(goal, content, reward, period)
                FirebaseUtil.addGoal(goal)

                // GoalManagementActivity로 돌아가기
                startActivity(Intent(this, GoalManagementActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "목표와 상세내용은 필수 입력 항목입니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}