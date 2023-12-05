package com.example.myapplicationllll

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationllll.R
import com.example.myapplicationllll.databinding.ItemGoalListBinding

class GoalAdapter(private val onDeleteListener: (Goal) -> Unit) : RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {
    private var goals: MutableList<Goal> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val binding = ItemGoalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bind(goals[position])

        // 삭제 버튼 클릭 이벤트 처리
        holder.itemView.findViewById<ImageButton>(R.id.btn_Delete).setOnClickListener {
            val goal = goals[position]
            onDeleteListener.invoke(goal)
            deleteGoal(goal)
        }
    }

    override fun getItemCount(): Int = goals.size

    fun setGoals(goals: List<Goal>) {
        this.goals = goals.toMutableList()
        notifyDataSetChanged()
    }

    private fun deleteGoal(goal: Goal) {
        val position = goals.indexOf(goal)
        if (position != -1) {
            goals.removeAt(position)
            notifyItemRemoved(position)
            // FirebaseUtil을 사용하여 목표 삭제
            FirebaseUtil.deleteGoal(goal)
        }
    }

    inner class GoalViewHolder(private val binding: ItemGoalListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(goal: Goal) {
            binding.itemGoal.text = goal.goal
            binding.itemContent.text = goal.content
            binding.itemReward.text = goal.reward
            binding.cb.isChecked = goal.isChecked
        }
    }
}