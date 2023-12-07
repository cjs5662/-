package com.example.myapplicationllll

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationllll.databinding.ItemGoalListBinding

class GoalAdapter(private val onDeleteListener: (Goal) -> Unit) : RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    private var goals: MutableList<Goal> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val binding = ItemGoalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val current = goals[position]

        holder.bind(current)

        holder.itemView.findViewById<ImageButton>(R.id.btn_Delete).setOnClickListener {
            onDeleteListener.invoke(current)
            deleteGoal(current)
        }
    }

    override fun getItemCount(): Int = goals.size

    // 목표 목록 갱신
    fun setGoals(goals: List<Goal>) {
        this.goals = goals.toMutableList()
        notifyDataSetChanged()
    }

    private fun deleteGoal(goal: Goal) {
        val position = goals.indexOf(goal)
        if (position != -1) {
            goals.removeAt(position)
            notifyItemRemoved(position)

            FirebaseUtil.deleteGoal(goal)
        }
    }

    fun moveItemToLast(position: Int) {
        if (position < itemCount - 1) {
            val removedItem = goals.removeAt(position)
            goals.add(removedItem)
            notifyItemMoved(position, itemCount - 1)
        }
    }

    inner class GoalViewHolder(private val binding: ItemGoalListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(goal: Goal) {
            binding.itemGoal.text = goal.goal
            binding.itemContent.text = goal.content
            binding.itemReward.text = goal.reward
            binding.cb.isChecked = goal.isChecked

            binding.cb.setOnCheckedChangeListener { _, isChecked ->
                goal.isChecked = isChecked
                FirebaseUtil.updateStatus(goal)
                moveItemToLast(adapterPosition)

                // 체크박스 상태에 따라 itemGoal의 텍스트에 줄 긋기
                if (isChecked) {
                    binding.itemGoal.paintFlags =
                        binding.itemGoal.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    binding.itemGoal.paintFlags =
                        binding.itemGoal.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }
        }
    }
}