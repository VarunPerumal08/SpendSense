package com.projects.budgetapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.projects.budgetapp.R

class GoalsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        val etMinGoal = view.findViewById<EditText>(R.id.etMinGoal)
        val etMaxGoal = view.findViewById<EditText>(R.id.etMaxGoal)
        val btnSaveGoals = view.findViewById<Button>(R.id.btnSaveGoals)

        btnSaveGoals.setOnClickListener {
            val minGoal = etMinGoal.text.toString().toDoubleOrNull()
            val maxGoal = etMaxGoal.text.toString().toDoubleOrNull()

            if (minGoal != null && maxGoal != null && minGoal <= maxGoal) {
                Toast.makeText(requireContext(), "Goals saved: Min $minGoal, Max $maxGoal", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please enter valid goals.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
