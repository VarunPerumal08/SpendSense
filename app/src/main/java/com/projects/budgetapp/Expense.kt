package com.projects.budgetapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Double,
    val category: String,
    val description: String,
    val date: Long = Calendar.getInstance().timeInMillis, // Store as timestamp
    val photoPath: String? = null // Path to stored photo (optional)
)