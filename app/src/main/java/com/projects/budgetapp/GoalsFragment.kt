package com.projects.budgetapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.projects.budgetapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GoalsFragment : Fragment() {

    private lateinit var etMinGoal: EditText
    private lateinit var etMaxGoal: EditText
    private lateinit var btnSaveGoals: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        etMinGoal = view.findViewById(R.id.etMinGoal)
        etMaxGoal = view.findViewById(R.id.etMaxGoal)
        btnSaveGoals = view.findViewById(R.id.btnSaveGoals)
        val tvMinGoal = view.findViewById<TextView>(R.id.tvCurrentMinGoal)
        val tvMaxGoal = view.findViewById<TextView>(R.id.tvCurrentMaxGoal)

        val db = ExpenseDatabase.getDatabase(requireContext())
        val goalDao = db.goalDao()


        val currentMonth = SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(Date())

        // Load saved goals on start
        lifecycleScope.launch {
            val currentGoal = goalDao.getGoalByMonth(currentMonth)
            currentGoal?.let {
                tvMinGoal.text = "Current Min Goal: ${it.minAmount}"
                tvMaxGoal.text = "Current Max Goal: ${it.maxAmount}"
            }
        }

        btnSaveGoals.setOnClickListener {
            val minGoal = etMinGoal.text.toString().toDoubleOrNull()
            val maxGoal = etMaxGoal.text.toString().toDoubleOrNull()

            if (minGoal != null && maxGoal != null && minGoal <= maxGoal) {
                val goal = Goal(month = currentMonth, minAmount = minGoal, maxAmount = maxGoal)
                lifecycleScope.launch {
                    goalDao.insert(goal)
                    Toast.makeText(requireContext(), "Goals saved!", Toast.LENGTH_SHORT).show()
                    tvMinGoal.text = "Current Min Goal: $minGoal"
                    tvMaxGoal.text = "Current Max Goal: $maxGoal"
                }
            } else {
                Toast.makeText(requireContext(), "Please enter valid goals.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}