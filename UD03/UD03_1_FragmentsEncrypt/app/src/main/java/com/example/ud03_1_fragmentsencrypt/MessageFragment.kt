package com.example.ud03_1_fragmentsencrypt

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController

class MessageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño desde el fragmento
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        val buttonNext = view.findViewById<Button>(R.id.button_next)
        buttonNext.setOnClickListener {
            view.findNavController().navigate(
                MessageFragmentDirections.actionMessageFragmentToEncryptFragment
                    (view.findViewById<EditText>(R.id.edit_text_id).text.toString())
            )
        }
        return view
    }
}