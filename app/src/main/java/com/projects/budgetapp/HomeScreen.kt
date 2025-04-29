package com.projects.budgetapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CategoryFragment())
            .commit()

        findViewById<Button>(R.id.btnCategory).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, CategoryFragment()).commit()
        }

        findViewById<Button>(R.id.btnExpenses).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ExpenseFragment()).commit()
        }

        findViewById<Button>(R.id.btnGoals).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, GoalsFragment()).commit()
        }

        findViewById<Button>(R.id.btnSummary).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SummaryFragment()).commit()
        }
    }
}

