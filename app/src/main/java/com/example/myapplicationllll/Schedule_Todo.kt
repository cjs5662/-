package com.example.myapplicationllll

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit

class Schedule_Todo : Fragment() {

    companion object {
        fun newInstance() = Schedule_Todo()
    }

    private lateinit var viewModel: ScheduleTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule__todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // +버튼을 통한 일정, 할일 입력화면으로의 이동
        view.findViewById<View>(R.id.btn_ScheduleTodoInput).setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.main_frame, Schedule_Todo_Input())
                addToBackStack(null)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScheduleTodoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}