package com.projects.budgetapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class SummaryFragment : Fragment() {

    // Sample data: In a real app, you would query a database or API.
    private val expenseData = mapOf(
        "Food" to 150.0,
        "Transport" to 200.0,
        "Entertainment" to 120.0
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        val tvSummary = view.findViewById<TextView>(R.id.tvSummary)

        // Display total expenses by category
        val summaryText = buildString {
            append("Total Expenses per Category:\n")
            expenseData.forEach { (category, total) ->
                append("$category: \$${total}\n")
            }
        }

        tvSummary.text = summaryText
        return view
    }
}
