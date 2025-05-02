package com.projects.budgetapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.projects.budgetapp.R
import com.projects.budgetapp.Expense
import com.projects.budgetapp.ExpenseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.*

class ExpenseFragment : Fragment() {

    private lateinit var etAmount: EditText
    private lateinit var etDescription: EditText
    private lateinit var etStartTime: EditText
    private lateinit var etEndTime: EditText
    private lateinit var etDate: EditText
    private lateinit var spCategory: Spinner
    private lateinit var btnAttachPhoto: Button
    private lateinit var imgPreview: ImageView
    private lateinit var btnSaveExpense: Button

    private var photoUri: Uri? = null
    private val PICK_IMAGE_REQUEST = 1

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_expense, container, false)

        etAmount = view.findViewById(R.id.etAmount)
        etDescription = view.findViewById(R.id.etDescription)
        etStartTime = view.findViewById(R.id.etStartTime)
        etEndTime = view.findViewById(R.id.etEndTime)
        etDate = view.findViewById(R.id.etDate)
        spCategory = view.findViewById(R.id.spCategory)
        btnAttachPhoto = view.findViewById(R.id.btnAttachPhoto)
        imgPreview = view.findViewById(R.id.imgPreview)
        btnSaveExpense = view.findViewById(R.id.btnSaveExpense)

        val db = ExpenseDatabase.getDatabase(requireContext())
        val categoryDao = db.categoryDao()
        val expenseDao = db.expenseDao()

        categoryDao.getAllCategories().observe(viewLifecycleOwner) { categories ->
            val categoryNames = categories.map { it.name }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryNames.toList())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spCategory.adapter = adapter
        }

        etDate.setOnClickListener {
            showDatePicker()
        }

        btnAttachPhoto.setOnClickListener {
            pickImageFromGallery()
        }

        btnSaveExpense.setOnClickListener {
            val amount = etAmount.text.toString().toDoubleOrNull()
            val description = etDescription.text.toString()
            val startTime = etStartTime.text.toString()
            val endTime = etEndTime.text.toString()
            val dateStr = etDate.text.toString()
            val category = spCategory.selectedItem?.toString() ?: ""

            if (amount == null || description.isBlank() || startTime.isBlank() || endTime.isBlank() || dateStr.isBlank() || category.isBlank()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dateLong = try {
                dateFormat.parse(dateStr)?.time ?: 0L
            } catch (e: Exception) {
                0L
            }

            val expense = Expense(
                amount = amount,
                category = category,
                startTime = startTime,
                endTime = endTime,
                description = description,
                date = dateLong,
                photoUri = photoUri?.toString()
            )

            lifecycleScope.launch(Dispatchers.IO) {
                expenseDao.insert(expense)
                launch(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Expense saved!", Toast.LENGTH_SHORT).show()
                    clearForm()
                }
            }
        }

        return view
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val dpd = DatePickerDialog(requireContext(), { _, year, month, day ->
            val date = Calendar.getInstance()
            date.set(year, month, day)
            etDate.setText(dateFormat.format(date.time))
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        dpd.show()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            photoUri = data.data
            imgPreview.setImageURI(photoUri)
        }
    }

    private fun clearForm() {
        etAmount.text.clear()
        etDescription.text.clear()
        etStartTime.text.clear()
        etEndTime.text.clear()
        etDate.text.clear()
        spCategory.setSelection(0)
        imgPreview.setImageResource(0)
        photoUri = null
    }
}
// Reference: Android Developers (2023)
