package com.example.appparcial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.appparcial.databinding.FragmentLossBinding
import com.example.appparcial.databinding.FragmentWinBinding

class WinFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentWinBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_win, container, false)
        // Inflate the layout for this fragment

        binding.buttonInicio.setOnClickListener{view: View->
            view.findNavController()
                .navigate(R.id.action_winFragment_to_titleFragment2)}

        return  binding.root
    }

}