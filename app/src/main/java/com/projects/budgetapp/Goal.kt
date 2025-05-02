package com.projects.budgetapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Goal(
    @PrimaryKey val month: String,
    val minAmount: Double,
    val maxAmount: Double
)
// Reference: Android Developers (2023)