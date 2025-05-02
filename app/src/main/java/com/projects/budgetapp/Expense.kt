package com.projects.budgetapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// Expense.kt
@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Double,
    val category: String,
    val startTime: String,
    val endTime: String,
    val description: String,
    val date: Long, // Store as timestamp
    val photoUri: String? = null // Store URI as string
)

// Implementation based on: Bazarbay (2022)