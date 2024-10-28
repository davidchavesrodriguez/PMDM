package com.example.ud04_practice_space

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class SolarSystemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_solar_system, container, false)

        // Obtener el array de strings
        val planetsArray = resources.getStringArray(R.array.planets_array)

        // Obtener el ListView desde el layout del fragmento
        val listView = view.findViewById<ListView>(R.id.listViewPlanets)

        // Crear un adaptador para el ListView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, planetsArray)

        // Asignar el adaptador al ListView
        listView.adapter = adapter

        return view
    }
}
