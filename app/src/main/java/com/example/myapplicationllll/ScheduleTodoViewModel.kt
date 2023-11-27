package com.example.myapplicationllll

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*

class ScheduleTodoViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance("https://planner-c04d6-default-rtdb.firebaseio.com/")
    var scheduleList: MutableLiveData<List<Schedule>> = MutableLiveData()
    var todoList: MutableLiveData<List<Todo>> = MutableLiveData()

    // 일정, 할일 리스트를 Firebase에서 불러오기
    fun fetchScheduleAndTodo() {
        val scheduleRef = database.getReference("schedules")
        scheduleRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val scheduleDataList = snapshot.children.mapNotNull { it.getValue(Schedule::class.java) }
                scheduleList.value = scheduleDataList
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        val todoRef = database.getReference("todos")
        todoRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val todoDataList = snapshot.children.mapNotNull { it.getValue(Todo::class.java) }
                todoList.value = todoDataList
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    // 일정을 Firebase에 저장
    fun saveSchedule(schedule: Schedule) {
        val scheduleRef = database.getReference("schedules")
        scheduleRef.child(schedule.id).setValue(schedule)
    }

    // 할일을 Firebase에 저장
    fun saveTodo(todo: Todo) {
        val todoRef = database.getReference("todos")
        todoRef.child(todo.id).setValue(todo)
    }

}

// 일정 클래스
class Schedule {
    lateinit var title: String
    lateinit var time: String
    lateinit var details: String
    lateinit var reward: String
    lateinit var id: String

    constructor()

    constructor(title: String, time: String, details: String, reward: String, id: String) {
        this.title = title
        this.time = time
        this.details = details
        this.reward = reward
        this.id = id
    }
}

// 할일 클래스
class Todo {
    lateinit var title: String
    lateinit var time: String
    lateinit var details: String
    lateinit var reward: String
    lateinit var id: String

    constructor()

    constructor(title: String, time: String, details: String, reward: String, id: String) {
        this.title = title
        this.time = time
        this.details = details
        this.reward = reward
        this.id = id
    }
}
