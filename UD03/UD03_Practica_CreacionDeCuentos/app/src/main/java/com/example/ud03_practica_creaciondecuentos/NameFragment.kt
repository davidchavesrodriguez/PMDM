package com.example.ud03_practica_creaciondecuentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.ud03_practica_creaciondecuentos.R

class NameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate fragment
        val view = inflater.inflate(R.layout.fragment_name, container, false)

        // Button
        val buttonStart = view.findViewById<Button>(R.id.main_next_button)
        buttonStart.setOnClickListener {
            view.findNavController().navigate(R.id.action_nameFragment_to_themeFragment)
        }

        return view
    }
}
