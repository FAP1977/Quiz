package com.example.quiz.quiz

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.quiz.R
import com.example.quiz.databinding.QuizFragmentBinding
import com.example.quiz.framework.BaseFragment
import com.example.quiz.getAppInjector
import com.example.quiz.quizList.QuizListViewModel
import kotlinx.android.synthetic.main.quiz_fragment.*
import javax.inject.Inject

class QuizFragment : BaseFragment<QuizViewModel, QuizFragmentBinding>()
{
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var quizListViewModel: QuizListViewModel

    companion object{
        private const val ARG_QUIZ_ID = "index"
        fun getInstance(quizId: String): QuizFragment {
            return QuizFragment().apply {
                arguments = Bundle().apply { putString(ARG_QUIZ_ID, quizId) }
            }
        }
    }

    override fun initViewModel() : QuizViewModel {
        getAppInjector().inject(this)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)[QuizViewModel::class.java]
        viewModel.withId(getQuizId())
        return viewModel
    }

    override fun getLayoutId() = R.layout.quiz_fragment

    private fun getQuizId(): String {
        return arguments!!.takeIf { it.containsKey(ARG_QUIZ_ID) }
            ?.getString(ARG_QUIZ_ID)
            ?: ""
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        quizListViewModel = ViewModelProviders.of(activity!!)[QuizListViewModel::class.java]
        setupQuizView()
        setupCheckResultButton()
        setupResultView()
    }

    private fun setupQuizView() {
        viewModel.quiz().observe(this, Observer { quiz ->
            quiz?.let { binding.quiz = quiz }
            setupOptionView(quiz.answer)
        })
    }

    private fun setupOptionView(answer: String) {

        optionView.setOnCheckedChangeListener{_, checkedId ->
            val userSelection = getUserSelection(checkedId)
            quizListViewModel.moveToNextQuestion(getQuizId(), answer, userSelection)
        }

    }

    private fun getUserSelection(@IdRes checkedOption: Int) : String {
        return when(checkedOption) {
            R.id.optionA -> "A"
            R.id.optionB -> "B"
            R.id.optionC -> "C"
            R.id.optionD -> "D"
            else -> ""
        }
    }

    private fun setupCheckResultButton() {
        check_result_button.setOnClickListener {
            result_text_view.visibility = View.VISIBLE
        }
    }

    private fun setupResultView() {
        viewModel.result.observe(this, Observer { isCorrect ->

            binding.isCorrect = isCorrect

            if (isCorrect) {
                result_text_view.text = getString(R.string.correct_answer)
                result_text_view.setTextColor(Color.GREEN)
            } else {
                result_text_view.text = getString(R.string.incorrect_answer)
                result_text_view.setTextColor(Color.RED)
            }

        })
    }

}