package com.example.appparcial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.appparcial.databinding.FragmentLossBinding

class LossFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentLossBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_loss, container, false)
        // Inflate the layout for this fragment

        binding.tryAgainButton.setOnClickListener{view: View->
            view.findNavController()
                .navigate(R.id.action_lossFragment_to_titleFragment)}

        return  binding.root
    }



}