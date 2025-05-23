package com.projects.budgetapp



import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): LiveData<List<Category>> // Changed from Flow to LiveData
}

// Reference: Android Developers (2023)
// Android Developers (2023) ViewModel Overview. Available at: https://developer.android.com/topic/libraries/architecture/viewmodel (Accessed: 15 June 2024).