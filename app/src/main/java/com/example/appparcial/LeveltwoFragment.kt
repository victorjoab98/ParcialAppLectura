package com.example.appparcial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.appparcial.databinding.FragmentLeveloneBinding
import com.example.appparcial.databinding.FragmentLeveltwoBinding

class LeveltwoFragment : Fragment() {

    data class Word(
        val img: Int,
        val answers: List<String>)

    private val words: MutableList<Word> = mutableListOf(
        Word( img =R.drawable.carro
            ,answers = listOf("ca", "ru", "ae", "ci")),
        Word( img = R.drawable.lapiz
            , answers = listOf("la", "gu", "di", "lo")),
        Word( img = R.drawable.rata
            , answers = listOf("ra", "re", "sa", "po")),
        Word( img = R.drawable.tomate
            , answers = listOf("to", "hi", "da", "fe")),
        Word( img = R.drawable.toro
            , answers = listOf("to", "ca", "te", "ue"))
    )

    lateinit var currentWord: LeveltwoFragment.Word
    lateinit var answers: MutableList<String>
    private var wordIndex = 0
    private val numQuestions = 5

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentLeveltwoBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_leveltwo, container, false)
        randomizeWords()
        binding.questionImage.setImageResource(currentWord.img)
        binding.game = this

        binding.submitButton.setOnClickListener  @Suppress("UNUSED_ANONYMOUS_PARAMETER"){ view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Si checkedId es -1 no se checo nada (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    //No es necesario considerar FirstSecondAnswer
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                if (answers[answerIndex] == currentWord.answers[0]){
                    wordIndex++

                    //SI aun no ha completado las 5 preguntas, mostrarle la siguiente
                    if (wordIndex < numQuestions) {
                        currentWord = words[wordIndex]
                        setWord()
                        binding.questionImage.setImageResource(currentWord.img)
                        binding.invalidateAll()
                    }else{
                        //Si ya mostro las 5 preguntas, entonces gana

                        view.findNavController().
                        navigate(R.id.action_leveltwoFragment_to_winFragment)
                    }
                }else{
                    //si se equivoco en una pierde
                }
            }
        }


        return binding.root
    }



    private fun randomizeWords() {
        words.shuffle()//revuelve el arreglo de palabras
        wordIndex = 0
        setWord()
    }

    private fun setWord() {
        currentWord = words[wordIndex] //obtiene la primera palabra del arreglo revuelto
        // revuelve el arreglo de las vocales
        answers = currentWord.answers.toMutableList()
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_appParcial, wordIndex + 1, numQuestions)

    }

}