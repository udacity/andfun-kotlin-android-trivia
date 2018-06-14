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
import java.util.*

class InGame : Fragment() {
    data class Question(
            val question: String,
            val answers: Array<String>)

    // The first answer is the correct one.  We randomize the answers before showing the question.
    // All questions must have four answers.
    val questions: Array<Question> = arrayOf(
            Question(question = "What is Android Jetpack?",
                    answers = arrayOf("all of these", "tools", "documentation", "libraries")),
            Question(question = "Base class for Layout?",
                    answers = arrayOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")),
            Question(question = "Layout for complex Screens?",
                    answers = arrayOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")),
            Question(question = "Pushing structured data into a Layout?",
                    answers = arrayOf("Data Binding", "Data Pushing", "Set Text", "OnClick")),
            Question(question = "Inflate layout in fragments?",
                    answers = arrayOf("onCreateView", "onActivityCreated", "onCreateLayout", "onInflateLayout")),
            Question(question = "Build system for Android?",
                    answers = arrayOf("Gradle", "Graddle", "Grodle", "Groyle")),
            Question(question = "Android vector format?",
                    answers = arrayOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")),
            Question(question = "Android Navigation Component?",
                    answers = arrayOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")),
            Question(question = "Registers app with launcher?",
                    answers = arrayOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")),
            Question(question = "Mark a layout for Data Binding?",
                    answers = arrayOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"))
    )

    lateinit var current: Question
    lateinit var answers: Array<String>
    private var questionIndex = 0
    private val numQuestions = Math.min((questions.size + 1) / 2, 3)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentInGameBinding>(
                inflater, R.layout.fragment_in_game, container, false)

        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.ingame = this
        binding.questionRadioGroup.setOnCheckedChangeListener { rg: RadioGroup, checked: Int ->
            if (-1 != checked) {
                var answerIndex = 0
                when (checked) {
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
                        rg.findNavController().navigate(InGameDirections.action_in_game_to_resultsWinner(numQuestions, questionIndex))
                    }
                } else {
                    rg.findNavController().navigate(InGameDirections.action_in_game_to_gameOver())
                }
            }
        }
        return binding.root
    }

    // Classic Fisher-Yates shuffle
    private fun <T> randomizeArray(array: Array<T>): Array<T> {
        val random = Random()
        val last = array.size
        for (i in 0 until last) {
            val next = random.nextInt(last - i) + i
            val temp = array[i]
            array[i] = array[next]
            array[next] = temp
        }
        return array
    }

    private fun setQuestion() {
        current = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = randomizeArray(current.answers.clone())
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }

    private fun randomizeQuestions() {
        randomizeArray(questions)
        // set the current question
        questionIndex = 0
        setQuestion()
    }
}
