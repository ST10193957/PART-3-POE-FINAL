package com.example.partthree

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AddExpenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add Expense"

        val etAmount = findViewById<EditText>(R.id.etAmount)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val spCategory = findViewById<Spinner>(R.id.spinnerCategory)
        val etDate = findViewById<EditText>(R.id.etDate)
        val etStartTime = findViewById<EditText>(R.id.etStartTime)
        val etEndTime = findViewById<EditText>(R.id.etEndTime)
        val btnSave = findViewById<Button>(R.id.btnSaveExpense)
        val btnBack = findViewById<Button>(R.id.btnBackToDashboard)

        // Populate Spinner with categories
        val categories = arrayOf("Food", "Transport", "Entertainment", "Bills", "Shopping", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spCategory.adapter = adapter

        val calendar = Calendar.getInstance()

        // Date Picker
        etDate.setOnClickListener {
            DatePickerDialog(this, { _, y, m, d ->
                val date = String.format("%04d-%02d-%02d", y, m + 1, d)
                etDate.setText(date)
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        // Time Pickers
        etStartTime.setOnClickListener {
            TimePickerDialog(this, { _, h, m ->
                etStartTime.setText(String.format("%02d:%02d", h, m))
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }

        etEndTime.setOnClickListener {
            TimePickerDialog(this, { _, h, m ->
                etEndTime.setText(String.format("%02d:%02d", h, m))
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }

        // Back to Dashboard Button
        btnBack.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        // Save Button
        btnSave.setOnClickListener {
            val amount = etAmount.text.toString()
            val desc = etDescription.text.toString()
            val category = spCategory.selectedItem.toString()
            val date = etDate.text.toString()
            val startTime = etStartTime.text.toString()
            val endTime = etEndTime.text.toString()

            if (amount.isNotEmpty() && desc.isNotEmpty() && date.isNotEmpty() && startTime.isNotEmpty() && endTime.isNotEmpty()) {
                Toast.makeText(this, "Expense Saved!", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "All fields required!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Action bar back arrow
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
