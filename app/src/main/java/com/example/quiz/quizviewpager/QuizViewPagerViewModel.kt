package com.example.quiz.quizviewpager

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz.countDownFinnishSignal
import com.example.quiz.countDownInitial
import com.example.quiz.countDownInterval
import com.example.quiz.data.local.dao.CategoryDao
import com.example.quiz.data.local.dao.QuizDao
import javax.inject.Inject

class QuizViewPagerViewModel @Inject constructor(
    private val quizDao: QuizDao,
    private val categoryDao: CategoryDao
) : ViewModel() {

    private lateinit var categoryId: String


    private val _timeLeft = MutableLiveData<Long>()
    val timeLeft: LiveData<Long> = _timeLeft
    private val countDownTimer = object: CountDownTimer(countDownInitial, countDownInterval){
        override fun onTick(millisLeft: Long) {
            _timeLeft.value = millisLeft
        }

        override fun onFinish() {
            _timeLeft.value = countDownFinnishSignal
        }
    }

    fun quizIdList() = quizDao.getQuizIdListByCategory(categoryId)
    fun category() = categoryDao.getById(categoryId)

    fun withCategoryId(categoryId: String) {
        this.categoryId = categoryId
    }

    fun resetTimer() {
        countDownTimer.cancel()
        countDownTimer.start()
    }

}
