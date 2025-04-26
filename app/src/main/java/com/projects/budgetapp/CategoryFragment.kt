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

class CategoryFragment : Fragment() {

    private val categories = mutableListOf<String>() // List to store categories

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)

        val etCategory = view.findViewById<EditText>(R.id.etCategory)
        val btnAdd = view.findViewById<Button>(R.id.btnAddCategory)

        // On button click, add category
        btnAdd.setOnClickListener {
            val category = etCategory.text.toString()
            if (category.isNotBlank()) {
                categories.add(category)
                Toast.makeText(requireContext(), "Category '$category' added!", Toast.LENGTH_SHORT).show()
                etCategory.text.clear()
            } else {
                Toast.makeText(requireContext(), "Please enter a valid category.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
