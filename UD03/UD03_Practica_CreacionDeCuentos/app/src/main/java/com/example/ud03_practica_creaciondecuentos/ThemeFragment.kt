package com.example.ud03_practica_creaciondecuentos

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView // Importa CardView

class ThemeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_theme, container, false)

        val textView: TextView = view.findViewById(R.id.text_theme)

        // Recuperar el argumento pasado
        val args = ThemeFragmentArgs.fromBundle(requireArguments())
        val name = args.name

        // Mostrar el nombre en el TextView
        val title = getString(R.string.text_theme_title) + " " + name
        textView.text = title

        // Especificar explícitamente el tipo de las tarjetas como CardView
        val cardArray = arrayOf(
            view.findViewById<CardView>(R.id.card_house),
            view.findViewById<CardView>(R.id.card_forest),
            view.findViewById<CardView>(R.id.card_nightosfere)
        )

        // Configurar acciones para las tarjetas
        cardArray.forEach { card ->
            card.setOnClickListener {
                val cardName = when (card.id) {
                    R.id.card_house -> "Casa seleccionada"
                    R.id.card_forest -> "Bosque seleccionado"
                    R.id.card_nightosfere -> "Nochesfera seleccionada"
                    else -> "Tarjeta desconocida"
                }
                Toast.makeText(requireContext(), cardName, Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
