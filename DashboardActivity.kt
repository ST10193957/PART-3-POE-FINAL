package com.example.partthree

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        findViewById<Button>(R.id.btnAddCategory).setOnClickListener {
            startActivity(Intent(this, AddCategoryActivity::class.java))
        }

        findViewById<Button>(R.id.btnAddExpense).setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }

        findViewById<Button>(R.id.btnViewReports).setOnClickListener {
            startActivity(Intent(this, ReportsActivity::class.java))
        }

        findViewById<Button>(R.id.btnSetBudgetGoals).setOnClickListener {
            startActivity(Intent(this, BudgetActivity::class.java))
        }
        findViewById<Button>(R.id.btnSetBudgetGoals).setOnClickListener {
            startActivity(Intent(this, BudgetActivity::class.java))
        }

    }
}
