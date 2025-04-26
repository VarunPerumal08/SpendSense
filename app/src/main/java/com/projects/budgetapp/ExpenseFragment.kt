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

class ExpenseFragment : Fragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_expense, container, false)

        etDescription = view.findViewById(R.id.etDescription)
        etStartTime = view.findViewById(R.id.etStartTime)
        etEndTime = view.findViewById(R.id.etEndTime)
        etDate = view.findViewById(R.id.etDate)
        spCategory = view.findViewById(R.id.spCategory)
        btnAttachPhoto = view.findViewById(R.id.btnAttachPhoto)
        imgPreview = view.findViewById(R.id.imgPreview)
        btnSaveExpense = view.findViewById(R.id.btnSaveExpense)

        // Load dummy categories
        val dummyCategories = listOf("Food", "Transport", "Bills")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dummyCategories)
        spCategory.adapter = adapter

        btnAttachPhoto.setOnClickListener {
            pickImageFromGallery()
        }

        btnSaveExpense.setOnClickListener {
            // Here you'd normally save this to Firebase or SQLite
            Toast.makeText(requireContext(), "Expense saved!", Toast.LENGTH_SHORT).show()
        }

        return view
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
}
