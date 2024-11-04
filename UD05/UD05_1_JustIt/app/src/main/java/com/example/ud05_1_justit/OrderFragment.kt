package com.example.ud05_1_justit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order, container, false)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val fabSend = view.findViewById<FloatingActionButton>(R.id.fabSend)
        fabSend.setOnClickListener {
            val groupBurger = view.findViewById<RadioGroup>(R.id.groupBurger)
            val burgerType = groupBurger.checkedRadioButtonId
            val chipGroup = view.findViewById<ChipGroup>(R.id.chipgroupExtras)

            fun getExtras(): String {
                val selectedExtras = mutableListOf<String>()
                for (i in 0 until chipGroup.childCount) {
                    val chip = chipGroup.getChildAt(i) as Chip
                    if (chip.isChecked) {
                        selectedExtras.add(chip.text.toString())
                    }
                }
                return if (selectedExtras.isNotEmpty()) {
                    "\nExtras: " + selectedExtras.joinToString(", ")
                } else {
                    ""
                }
            }

            if (burgerType == -1) {
                Toast.makeText(activity, R.string.alertSelectType, Toast.LENGTH_SHORT).show()
            } else {
                var message = getString(R.string.burgerSelected) + " "
                message += when (burgerType) {
                    R.id.radioAmericana -> getString(R.string.american) + getExtras()
                    R.id.radioChicken -> getString(R.string.chicken) + getExtras()
                    R.id.radioVegan -> getString(R.string.vegan) + getExtras()
                    else -> "Error"
                }
                Snackbar.make(fabSend, message, Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        chipGroup.clearCheck()
                        groupBurger.clearCheck()
                    }
                    .show()
            }
        }

        return view
    }
}
