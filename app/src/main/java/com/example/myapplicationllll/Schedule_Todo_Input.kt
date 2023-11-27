package com.example.myapplicationllll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationllll.databinding.FragmentScheduleTodoInputBinding
import com.google.firebase.database.FirebaseDatabase

class Schedule_Todo_Input : Fragment() {

    private lateinit var binding: FragmentScheduleTodoInputBinding
    private lateinit var viewModel: ScheduleTodoViewModel
    private val database = FirebaseDatabase.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleTodoInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ScheduleTodoViewModel::class.java)

        // 저장 버튼 클릭 시 입력 내용 파이어베이스에 저장
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val time = binding.etTime.text.toString()
            val details = binding.etDetails.text.toString()
            val reward = binding.etReward.text.toString()

            when (binding.radioGroup.checkedRadioButtonId) {
                R.id.radio_schedule -> {
                    val id = database.getReference("schedules").push().key
                    if (id != null) {
                        val schedule = Schedule(title, time, details, reward, id)
                        viewModel.saveSchedule(schedule)

                        // 화면 전환 코드
                        parentFragmentManager.commit {
                            replace(R.id.main_frame, Schedule_Todo())
                            addToBackStack(null)
                        }
                    } else {
                        // id가 null인 경우에 대한 에러 처리
                        Toast.makeText(context, "일정 생성에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.radio_todo -> {
                    val id = database.getReference("todos").push().key
                    if (id != null) {
                        val todo = Todo(title, time, details, reward, id)
                        viewModel.saveTodo(todo)

                        // 화면 전환 코드
                        parentFragmentManager.commit {
                            replace(R.id.main_frame, Schedule_Todo())
                            addToBackStack(null)
                        }
                    } else {
                        // id가 null인 경우에 대한 에러 처리
                        Toast.makeText(context, "할일 생성에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {
                    // 라디오 버튼이 선택되지 않은 경우에 대한 처리
                    Toast.makeText(context, "일정 혹은 할일 중 선택하시오", Toast.LENGTH_SHORT).show()
                }
            }
        }


        // 취소 버튼 클릭 시 이전 화면으로 이동
        binding.btnCancel.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.main_frame, Schedule_Todo())
                addToBackStack(null)
            }
        }
    }
}
