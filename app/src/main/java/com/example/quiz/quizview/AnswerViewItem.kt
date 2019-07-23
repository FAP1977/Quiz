package com.example.quiz.quizview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.AnswerViewItemBinding
import com.example.quiz.model.Answer

class AnswerViewItem private constructor(private val binding: AnswerViewItemBinding)
    : RecyclerView.ViewHolder(binding.root){

    companion object{
        fun from(parent: ViewGroup): AnswerViewItem{
            val inflater = LayoutInflater.from(parent.context)
            val binding = AnswerViewItemBinding.inflate(inflater)
            return AnswerViewItem(binding)
        }
    }

    fun bind(answer: Answer){
        binding.answer = answer
        binding.answerButton.setOnClickListener {
            val color = if (answer.isTrue) Color.GREEN else Color.RED
            it.setBackgroundColor(color)
        }
    }

}