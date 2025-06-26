package com.example.partthree

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BudgetActivity : AppCompatActivity() {

    private lateinit var etMonthlyBudget: EditText
    private lateinit var etFoodGoal: EditText
    private lateinit var etTransportGoal: EditText
    private lateinit var tvGoalSummary: TextView
    private lateinit var btnSaveGoals: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)

        etMonthlyBudget = findViewById(R.id.etMonthlyBudget)
        etFoodGoal = findViewById(R.id.etFoodGoal)
        etTransportGoal = findViewById(R.id.etTransportGoal)
        tvGoalSummary = findViewById(R.id.tvGoalSummary)
        btnSaveGoals = findViewById(R.id.btnSaveGoals)
        btnBack = findViewById(R.id.btnBackToDashboard)

        btnSaveGoals.setOnClickListener {
            val monthly = etMonthlyBudget.text.toString().toDoubleOrNull() ?: 0.0
            val food = etFoodGoal.text.toString().toDoubleOrNull() ?: 0.0
            val transport = etTransportGoal.text.toString().toDoubleOrNull() ?: 0.0
            val other = monthly - (food + transport)

            val summary = """
                🧾 Monthly Budget: R$monthly
                🍽️ Food Goal: R$food
                🚗 Transport Goal: R$transport
                🪙 Other Allowance: R$other
            """.trimIndent()

            tvGoalSummary.text = summary
        }

        btnBack.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
