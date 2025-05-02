package com.projects.budgetapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.projects.budgetapp.R
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {

    private lateinit var etCategory: EditText
    private lateinit var btnAdd: Button
    private lateinit var categoryListView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var db: ExpenseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)

        etCategory = view.findViewById(R.id.etCategory)
        btnAdd = view.findViewById(R.id.btnAddCategory)
        categoryListView = view.findViewById(R.id.categoryListView)

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, mutableListOf())
        categoryListView.adapter = adapter

        // Access database
        db = ExpenseDatabase.getDatabase(requireContext())

        // Observe and update the category list
        db.categoryDao().getAllCategories().observe(viewLifecycleOwner) { categories ->
            val names = categories.map { it.name }
            adapter.clear()
            adapter.addAll(names)
            adapter.notifyDataSetChanged()
        }

        btnAdd.setOnClickListener {
            val categoryName = etCategory.text.toString().trim()
            if (categoryName.isNotEmpty()) {
                lifecycleScope.launch {
                    db.categoryDao().insert(Category(name = categoryName))
                }
                etCategory.text.clear()
                Toast.makeText(requireContext(), "Category added!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please enter a category name.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}

// Android Developers (2023)
// ViewModel Overview. Available at: https://developer.android.com/topic/libraries/architecture/viewmodel (Accessed: 15 June 2024).