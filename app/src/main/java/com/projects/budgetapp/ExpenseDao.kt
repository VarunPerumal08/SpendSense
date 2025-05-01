// File: ExpenseDao.kt
package com.projects.budgetapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {  // Notice this is an INTERFACE
    // Insert operation
    @Insert
    suspend fun insert(expense: Expense)  // No implementation needed!

    // Query all expenses ordered by date (newest first)
    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    // Query expenses between dates
    @Query("SELECT * FROM expenses WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getExpensesBetweenDates(startDate: Long, endDate: Long): Flow<List<Expense>>

    // Get category totals between dates
    @Query("SELECT category, SUM(amount) as total FROM expenses WHERE date BETWEEN :startDate AND :endDate GROUP BY category")
    fun getCategoryTotals(startDate: Long, endDate: Long): Flow<List<CategoryTotal>>

    // Data class for the category totals result
    data class CategoryTotal(
        val category: String,
        val total: Double
    )
}