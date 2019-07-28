package com.example.quiz.quizedit

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.quiz.databinding.AnswerEditItemBinding
import com.example.quiz.framework.BaseAnswerItem
import com.example.quiz.model.Answer

class AnswerEditItem(private val binding: AnswerEditItemBinding) : BaseAnswerItem(binding){

    companion object{
        fun from(parent: ViewGroup): AnswerEditItem{
            val inflater = LayoutInflater.from(parent.context)
            val binding = AnswerEditItemBinding.inflate(inflater)
            return AnswerEditItem(binding)
        }
    }

    override fun bind(answer: Answer){
        binding.answer = answer
        binding.deleteButton.setOnClickListener {
            clickListener.onDeleteButtonClick(answer)
        }
    }
}