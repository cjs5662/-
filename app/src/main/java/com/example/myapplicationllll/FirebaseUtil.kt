package com.example.myapplicationllll

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object FirebaseUtil {
    private val databaseReference = FirebaseDatabase.getInstance().reference

    fun addGoal(goal: Goal) {
        val key = databaseReference.child("goals").push().key
        key?.let {
            goal.id = key
            databaseReference.child("goals").child(it).setValue(goal)
        }
    }

    fun getGoals(period: String, callback: (List<Goal>) -> Unit) {
        databaseReference.child("goals").orderByChild("period").equalTo(period)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val goals = mutableListOf<Goal>()
                    for (goalSnapshot in snapshot.children) {
                        val goal = goalSnapshot.getValue(Goal::class.java)
                        goal?.let { goals.add(it) }
                    }
                    callback(goals)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
    }

    fun deleteGoal(goal: Goal) {
        databaseReference.child("goals").child(goal.id).removeValue()
    }
}