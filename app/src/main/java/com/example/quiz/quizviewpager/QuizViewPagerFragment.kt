package com.example.quiz.quizviewpager

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.quiz.BaseFragment

import com.example.quiz.R
import kotlinx.android.synthetic.main.quiz_view_pager_fragment.*

class QuizViewPagerFragment : BaseFragment<QuizViewPagerViewModel>() {
    private val args: QuizViewPagerFragmentArgs by navArgs()

    override fun initViewModel(): QuizViewPagerViewModel {
        return ViewModelProviders.of(this).get(QuizViewPagerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quiz_view_pager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.quizIdList.observe(this, Observer {
            var adapter = QuizViewPagerAdapter(childFragmentManager, it)
            view_pager.adapter = adapter
            view_pager.currentItem = args.index
        })
    }
}