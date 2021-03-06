package com.example.appparcial

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.appparcial.databinding.FragmentLeveloneBinding
import java.util.*
import kotlin.concurrent.schedule

class LeveloneFragment : Fragment() {

    data class Word(
        val img: Int,
        val answer: String,
        val palabra: String)

    private val words: MutableList<Word> = mutableListOf(
        Word( img =R.drawable.avion
            , answer = "a"
            , palabra = "AVION"),
        Word( img = R.drawable.elefante
            , answer = "e"
            ,palabra = "ELEFANTE"),
        Word( img = R.drawable.iglu
            , answer = "i"
            , palabra = "IGLU"),
        Word( img = R.drawable.oso
            , answer = "o",
            palabra = "OSO"),
        Word( img = R.drawable.uva
            , answer = "u"
            , palabra = "UVA")
    )

    lateinit var currentWord: Word
    var vocalesOpciones: List<String> = listOf("a", "e", "i", "o", "u")
    private var wordIndex = 0
    private val numQuestions = 5
    lateinit var nombre:String


    @SuppressLint("StringFormatMatches")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val binding : FragmentLeveloneBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_levelone, container, false)

        val args = LeveloneFragmentArgs.fromBundle(requireArguments())
        nombre = args.nombre
     //   binding.txtName.text = "Jugador: ${args.nombre}"

        //Mezcla el orden de las imagenes
        randomizeWords()
        binding.questionImage.setImageResource(currentWord.img)
        //Vincula esta clase ala variable game de la vista
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
                    R.id.fifthhAnswerRadioButton -> answerIndex = 4
                }
                if (vocalesOpciones[answerIndex] == currentWord.answer){

                    Toast.makeText(context, "CORRECTO!! ES '${currentWord.palabra}'", Toast.LENGTH_SHORT).show()


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
                        navigate(R.id.action_leveloneFragment_to_winFragment)
                    }
                }else{
                    //si se equivoco en una pierde
                    view.findNavController().
                    navigate(R.id.action_leveloneFragment_to_lossFragment)
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
        vocalesOpciones = vocalesOpciones.toMutableList()
        (vocalesOpciones as MutableList<String>).shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_appParcial, nombre, wordIndex+1, numQuestions)
    }

}