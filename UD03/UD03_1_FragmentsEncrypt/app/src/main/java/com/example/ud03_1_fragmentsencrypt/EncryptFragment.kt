package com.example.ud03_1_fragmentsencrypt

import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController

class EncryptFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_encrypt, container, false)
        val message = EncryptFragmentArgs.fromBundle(requireArguments()).message
        val encryptedField = view.findViewById<TextView>(R.id.encryptText)
        encryptedField.text = caesarEncrypt(message)

        return view
    }

    fun caesarEncrypt(message: String) = message.map {
        if (it.isLetter()) {
            if (it.isUpperCase()) {
                it.code.minus('A'.code)
                    .plus(3).mod(26)
                    .plus('A'.code).toChar()
            } else {
                it.code.minus('a'.code)
                    .plus(3).mod(26)
                    .plus('a'.code).toChar()
            }

        // not characters
        } else {
            it
        }

    }.joinToString("")
}

