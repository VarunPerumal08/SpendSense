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