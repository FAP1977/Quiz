package com.example.quiz.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.quiz.data.local.dao.CategoryDao
import com.example.quiz.data.local.dao.ChoiceDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.framework.SingletonHolder
import com.example.quiz.model.Category
import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz

// The implementation of this class is based on Android Sunflower
// https://github.com/googlesamples/android-sunflower
// Thanks Sunflower team for a great work! Keep going!
// PS: Excuse me for not say thanks in public and not knowing the name of the team
@Database(
    entities = [Category::class, Quiz::class, Choice::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val quizDao: QuizDao
    abstract val choiceDao: ChoiceDao
    abstract val categoryDao: CategoryDao

    companion object: SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(
            it,
            AppDatabase::class.java,
            "quiz.db"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val request = OneTimeWorkRequestBuilder<SeedDataWorker>().build()
                    WorkManager.getInstance().enqueue(request)
                }
            })
            .fallbackToDestructiveMigration()
            .build()
    })
}