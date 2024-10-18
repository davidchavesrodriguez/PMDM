package com.example.ud03_practica_creaciondecuentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

        // EditText
        val editTextName = view.findViewById<EditText>(R.id.main_name)

        // Button
        val buttonStart = view.findViewById<Button>(R.id.main_next_button)
        buttonStart.setOnClickListener {
            val name = editTextName.text.toString()
            val action = NameFragmentDirections.actionNameFragmentToThemeFragment(name)
            view.findNavController().navigate(action)
        }

        return view
    }
}
