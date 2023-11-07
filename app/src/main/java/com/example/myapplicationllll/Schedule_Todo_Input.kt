package com.example.myapplicationllll

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit

class Schedule_Todo_Input : Fragment() {

    companion object {
        fun newInstance() = Schedule_Todo_Input()
    }

    private lateinit var viewModel: ScheduleTodoInputViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule__todo__input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 취소 버튼을 통한 일정, 할일 리스트 화면으로 이동
        view.findViewById<View>(R.id.btn_cancel).setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.main_frame, Schedule_Todo())
                addToBackStack(null)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScheduleTodoInputViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
