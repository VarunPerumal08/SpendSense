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
//Android Developers (2023) ViewModel Overview. Available at: https://developer.android.com/topic/libraries/architecture/viewmodel (Accessed: 15 June 2024).