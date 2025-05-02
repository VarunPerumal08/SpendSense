package com.projects.budgetapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Expense::class, Category::class,Goal::class],  // Your Expense entity class
    version = 1,                  // Database version
    exportSchema = false           // Set to true if you want schema exports
)
abstract class ExpenseDatabase : RoomDatabase() {
    // Abstract getter for your DAO
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao
    abstract fun goalDao(): GoalDao
    companion object {
        // Singleton prevents multiple instances of database opening
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        fun getDatabase(context: Context): ExpenseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expense_database"  // Database name
                )
                    .fallbackToDestructiveMigration() // Destroys DB on version change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// Reference: Android Developers (2023)