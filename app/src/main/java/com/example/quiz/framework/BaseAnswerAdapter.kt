package com.example.quiz.framework

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.quiz.model.Answer

abstract class BaseAnswerAdapter<T: BaseAnswerItem>
    : ListAdapter<Answer, T>(AnswerListDiffCallback())
{
    lateinit var itemClickListener: BaseAnswerItem.ClickListener
    abstract fun getViewHolder(parent: ViewGroup) : T

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return getViewHolder(parent)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(getItem(position))
        holder.clickListener = itemClickListener
    }
}