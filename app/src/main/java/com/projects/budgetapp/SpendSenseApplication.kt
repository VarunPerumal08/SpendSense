package com.projects.budgetapp

import android.app.Application
import androidx.room.Room
import com.projects.budgetapp.ExpenseDatabase

class SpendSenseApplication : Application() {
    // Initialize the database with lazy delegation
    val database: ExpenseDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            ExpenseDatabase::class.java,
            "expense_database"
        )
            .fallbackToDestructiveMigration()  // Important for development
            .build()
    }
}