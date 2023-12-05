package com.example.myapplicationllll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationllll.databinding.FragmentScheduleTodoBinding
import com.google.firebase.database.FirebaseDatabase

class ScheduleTodo : Fragment() {

    private lateinit var binding: FragmentScheduleTodoBinding
    private lateinit var viewModel: ScheduleTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ScheduleTodoViewModel::class.java)
        setupRecyclerViews()
        observeViewModel()
        setupInputButton()
    }

    private fun setupRecyclerViews() {
        // 스케줄 리스트 RecyclerView 설정
        binding.scheduleRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ScheduleAdapter()
        }

        // 할일 리스트 RecyclerView 설정
        binding.todoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TodoAdapter()
        }
    }

    // ViewModel 데이터 변화 체크
    private fun observeViewModel() {
        viewModel.fetchScheduleAndTodo()
        viewModel.scheduleList.observe(viewLifecycleOwner) { scheduleDataList ->
            (binding.scheduleRecyclerView.adapter as ScheduleAdapter).submitList(scheduleDataList)
        }
        viewModel.todoList.observe(viewLifecycleOwner) { todoDataList ->
            (binding.todoRecyclerView.adapter as TodoAdapter).submitList(todoDataList)
        }
    }

    // 할일, 일정 입력 화면으로 이동하는 버튼
    private fun setupInputButton() {
        binding.btnScheduleTodoInput.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frame, ScheduleTodoInput())
            transaction.commit()
        }
    }
}


// 스케줄 리스트를 RecyclerView에 표시하기 위한 어댑터
class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    private val scheduleList = ArrayList<Schedule>()

    // ViewHolder를 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ViewHolder(view)
    }

    // ViewHolder에 데이터를 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val schedule = scheduleList[position]
        holder.bind(schedule)
    }

    // 아이템의 개수를 반환
    override fun getItemCount() = scheduleList.size

    // RecyclerView의 각 항목을 표시하는 데 사용되는 클래스
    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.is_title)
        private val time: TextView = view.findViewById(R.id.is_time)
        private val details: TextView = view.findViewById(R.id.is_details)
        private val reward: TextView = view.findViewById(R.id.is_reward)
        private val btnDelete: Button = view.findViewById(R.id.btn_delete_todo)

        // Schedule 객체를 바인딩
        fun bind(schedule: Schedule) {
            title.text = schedule.title
            time.text = schedule.time
            details.text = schedule.details
            reward.text = schedule.reward

            // 삭제 버튼에 클릭 리스너 설정
            btnDelete.setOnClickListener {
                // Firebase에서 해당 항목을 삭제하는 코드
                val database = FirebaseDatabase.getInstance("https://planner-c04d6-default-rtdb.firebaseio.com/")
                val reference = database.getReference("schedules")
                reference.child(schedule.id).removeValue()
            }
        }
    }

    // 새로운 스케줄 리스트 업데이트
    fun submitList(newScheduleList: List<Schedule>) {
        scheduleList.clear()
        scheduleList.addAll(newScheduleList)
        notifyDataSetChanged()
    }
}

// 할일 리스트를 RecyclerView에 표시하기 위한 어댑터
class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private val todoList = ArrayList<Todo>()

    // ViewHolder를 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view)
    }

    // ViewHolder에 데이터를 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoList[position]
        holder.bind(todo)
    }

    // 아이템의 개수를 반환
    override fun getItemCount() = todoList.size

    // RecyclerView의 각 항목을 표시하는 데 사용되는 클래스
    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.it_title)
        private val time: TextView = view.findViewById(R.id.it_time)
        private val details: TextView = view.findViewById(R.id.it_details)
        private val reward: TextView = view.findViewById(R.id.it_reward)
        private val btnDelete: Button = view.findViewById(R.id.btn_delete_todo)

        // Todo객체를 바인딩
        fun bind(todo: Todo) {
            title.text = todo.title
            time.text = todo.time
            details.text = todo.details
            reward.text = todo.reward

            // 삭제 버튼에 클릭 리스너 설정
            btnDelete.setOnClickListener {
                val database = FirebaseDatabase.getInstance("https://planner-c04d6-default-rtdb.firebaseio.com/")
                val reference = database.getReference("todos")
                reference.child(todo.id).removeValue()
            }
        }
    }

    // 새로운 할일 리스트를 업데이트
    fun submitList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}