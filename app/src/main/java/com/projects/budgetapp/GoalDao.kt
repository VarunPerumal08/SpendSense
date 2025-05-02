package com.projects.budgetapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(goal: Goal)

    @Query("SELECT * FROM Goal WHERE month = :month LIMIT 1")
    suspend fun getGoalByMonth(month: String): Goal?
}

// Android Developers (2023) ViewModel Overview.
// Available at: https://developer.android.com/topic/libraries/architecture/viewmodel (Accessed: 15 June 2024).