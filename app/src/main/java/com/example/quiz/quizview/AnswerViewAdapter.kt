package com.example.quiz.quizview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.AnswerViewItemBinding
import com.example.quiz.model.Answer

class AnswerViewAdapter(private val answerList: List<Answer>) : RecyclerView.Adapter<AnswerViewAdapter.AnswerItem>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerItem {
        return AnswerItem.from(parent)
    }

    override fun getItemCount(): Int = answerList.size

    override fun onBindViewHolder(holder: AnswerItem, position: Int) {
        holder.bind(answerList[position])
    }

    class AnswerItem private constructor(private val binding: AnswerViewItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        companion object{
            fun from(parent: ViewGroup): AnswerItem{
                val inflater = LayoutInflater.from(parent.context)
                val binding = AnswerViewItemBinding.inflate(inflater)
                return AnswerItem(binding)
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
}