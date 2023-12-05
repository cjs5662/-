package com.example.myapplicationllll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationllll.databinding.FragmentScheduleTodoInputBinding
import com.google.firebase.database.FirebaseDatabase

class ScheduleTodoInput : Fragment() {

    private lateinit var binding: FragmentScheduleTodoInputBinding
    private lateinit var viewModel: ScheduleTodoViewModel
    private val database = FirebaseDatabase.getInstance()
    private var selectedDate: String = ""

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

        setupSpinners()
        setupButtons()
    }

    // 년, 월, 일 스피너 설정
    private fun setupSpinners() {
        val years = Array(20) { i -> (i + 2023).toString() }
        val months = Array(12) { i -> (i + 1).toString() }

        val yearAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, years)
        val monthAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, months)

        binding.spinnerYear.adapter = yearAdapter
        binding.spinnerMonth.adapter = monthAdapter

        // 스피너 선택에 따른 출력
        val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val year = binding.spinnerYear.selectedItem.toString().toInt()
                val month = binding.spinnerMonth.selectedItem.toString().toInt()
                // 월에 따른 일수 보정
                val days = when (month) {
                    2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
                    4, 6, 9, 11 -> 30
                    else -> 31
                }
                val dayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, Array(days) { i -> (i + 1).toString() })
                binding.spinnerDay.adapter = dayAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.spinnerYear.onItemSelectedListener = onItemSelectedListener
        binding.spinnerMonth.onItemSelectedListener = onItemSelectedListener
    }


    private fun setupButtons() {
        // 저장 버튼 클릭 리스너 설정
        binding.btnSave.setOnClickListener {
            saveInput()
        }

        // 취소 버튼 클릭 리스너 설정
        binding.btnCancel.setOnClickListener {
            navigateToScheduleTodo()
        }
    }

    // 라디오 버튼에 따른 저장
    private fun saveInput() {
        val title = binding.etTitle.text.toString()
        val details = binding.etDetails.text.toString()
        val reward = binding.etReward.text.toString()
        val year = binding.spinnerYear.selectedItem.toString()
        val month = binding.spinnerMonth.selectedItem.toString()
        val day = binding.spinnerDay.selectedItem.toString()
        selectedDate = "$year-$month-$day"

        when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radio_schedule -> {
                saveSchedule(title, selectedDate, details, reward)
            }
            R.id.radio_todo -> {
                saveTodo(title, selectedDate, details, reward)
            }
            else -> {
                // 라디오 버튼이 선택되지 않은 경우에 대한 처리
                Toast.makeText(context, "일정 혹은 할일 중 선택하시오", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Firebase 데이터베이스에 일정을 저장
    private fun saveSchedule(title: String, time: String, details: String, reward: String) {
        val id = database.getReference("schedules").push().key
        if (id != null) {
            val schedule = Schedule(title, time, details, reward, id)
            viewModel.saveSchedule(schedule)
            navigateToScheduleTodo()
        } else {
            // id가 null인 경우에 대한 에러 처리
            Toast.makeText(context, "일정 생성에 실패하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    // Firebase 데이터베이스에 할일을 저장
    private fun saveTodo(title: String, time: String, details: String, reward: String) {
        val id = database.getReference("todos").push().key
        if (id != null) {
            val todo = Todo(title, time, details, reward, id)
            viewModel.saveTodo(todo)
            navigateToScheduleTodo()
        } else {
            // id가 null인 경우에 대한 에러 처리
            Toast.makeText(context, "할일 생성에 실패하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    // ScheduleTodo 프래그먼트로 이동
    private fun navigateToScheduleTodo() {
        parentFragmentManager.commit {
            replace(R.id.main_frame, ScheduleTodo())
            addToBackStack(null)
        }
    }
}