package com.example.navigation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.navigation.findNavController
import com.example.navigation.databinding.FragmentInGameBinding
import com.example.navigation.model.Question
import java.util.*

class InGame : Fragment() {

    // The first answer is the correct one.  We randomize the answers before showing the question.
    // All questions must have four answers.
    private val questions: MutableList<Question> = mutableListOf(
            Question(question = "What is Android Jetpack?",
                    answers = listOf("all of these", "tools", "documentation", "libraries")),
            Question(question = "Base class for Layout?",
                    answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")),
            Question(question = "Layout for complex Screens?",
                    answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")),
            Question(question = "Pushing structured data into a Layout?",
                    answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")),
            Question(question = "Inflate layout in fragments?",
                    answers = listOf("onCreateView", "onActivityCreated", "onCreateLayout", "onInflateLayout")),
            Question(question = "Build system for Android?",
                    answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")),
            Question(question = "Android vector format?",
                    answers = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")),
            Question(question = "Android Navigation Component?",
                    answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")),
            Question(question = "Registers app with launcher?",
                    answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")),
            Question(question = "Mark a layout for Data Binding?",
                    answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"))
    )

    lateinit var current: Question
    lateinit var answers: List<String>
    private var questionIndex = 0
    private val numQuestions = Math.min((questions.size + 1) / 2, 3)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentInGameBinding>(
                inflater, R.layout.fragment_in_game, container, false)

        randomizeQuestions()
        setQuestion()

        // Bind this fragment class to the layout
        binding.ingame = this
        binding.questionRadioGroup.setOnCheckedChangeListener { radioGroup: RadioGroup, checkedId: Int ->
            if (checkedId == -1) {
                return@setOnCheckedChangeListener
            }

            var answerIndex = 0
            when (checkedId) {
                R.id.secondAnswerRadioButton -> answerIndex = 1
                R.id.thirdAnswerRadioButton -> answerIndex = 2
                R.id.fourthAnswerRadioButton -> answerIndex = 3
            }

            if (answers[answerIndex] == current.answers[0]) {
                questionIndex++
                if (questionIndex < numQuestions) {
                    current = questions[questionIndex]
                    setQuestion()
                    binding.questionRadioGroup.clearCheck()
                    binding.invalidateAll()
                } else {
                    radioGroup.findNavController().navigate(InGameDirections.action_in_game_to_resultsWinner(numQuestions, questionIndex))
                }
            } else {
                radioGroup.findNavController().navigate(InGameDirections.action_in_game_to_gameOver())
            }
        }

        return binding.root
    }

    private fun randomizeQuestions() {
        randomizeArray(questions)
        // set the current question
        questionIndex = 0
    }

    // Classic Fisher-Yates shuffle
    private fun <T> randomizeArray(list: MutableList<T>): List<T> {
        val random = Random()
        val last = list.size
        for (i in 0 until last) {
            val next = random.nextInt(last - i) + i
            val temp = list[i]
            list[i] = list[next]
            list[next] = temp
        }

        return list
    }

    private fun setQuestion() {
        current = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = randomizeArray(current.answers.toMutableList())
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }
}
