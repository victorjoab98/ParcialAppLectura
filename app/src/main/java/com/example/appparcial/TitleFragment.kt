package com.example.appparcial

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.appparcial.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    lateinit var bindingAux : FragmentTitleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title,container,false)
        bindingAux = binding
        binding.buttonLevel1.setOnClickListener { view: View ->
           if (!bindingAux.txtPersonName.text.isEmpty()) {
                view.findNavController().navigate(R.id.action_titleFragment_to_leveloneFragment)
            }else{
                Toast.makeText(context, "Ingresa tu nombre para continuar", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonLevel2.setOnClickListener{view: View ->
            if (!bindingAux.txtPersonName.text.isEmpty()) {
                view.findNavController().navigate(R.id.action_titleFragment_to_leveltwoFragment)
            }else{
                Toast.makeText(context, "Ingresa tu nombre para continuar", Toast.LENGTH_LONG).show()
            }
        }


        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (!bindingAux.txtPersonName.text.isEmpty()) {
            return NavigationUI.
            onNavDestinationSelected(item,requireView().findNavController())
                    || super.onOptionsItemSelected(item)
        }else{
            Toast.makeText(context, "Ingresa tu nombre para continuar", Toast.LENGTH_LONG).show()
            return false
        }



    }




}