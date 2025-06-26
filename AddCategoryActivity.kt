package com.example.partthree

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        val etCategoryName = findViewById<EditText>(R.id.etCategoryName)
        val btnSave = findViewById<Button>(R.id.btnSaveCategory)

        btnSave.setOnClickListener {
            val category = etCategoryName.text.toString()
            if (category.isNotEmpty()) {
                Toast.makeText(this, "Category '$category' added!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Category name required!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
