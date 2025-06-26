package com.example.partthree

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class ReportsActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var tvSummary: TextView
    private lateinit var btnBack: Button
    private lateinit var tvBadge: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        barChart = findViewById(R.id.barChart)
        tvSummary = findViewById(R.id.tvSummary)
        btnBack = findViewById(R.id.btnBackToDashboard)
        tvBadge = findViewById(R.id.tvBadge)

        tvSummary.text = "Spending summary will go here"

        btnBack.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        setupBarChart()
    }

    private fun setupBarChart() {
        val entries = listOf(
            BarEntry(0f, 120f),
            BarEntry(1f, 90f),
            BarEntry(2f, 60f),
            BarEntry(3f, 150f)
        )

        val dataSet = BarDataSet(entries, "Spending per Category").apply {
            color = Color.BLUE
            valueTextSize = 12f
        }

        val barData = BarData(dataSet)
        barChart.data = barData

        val categories = listOf("Food", "Transport", "Utilities", "Entertainment")
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = IndexAxisValueFormatter(categories)

        val leftAxis: YAxis = barChart.axisLeft

        val minGoalLine = LimitLine(50f, "Min Goal").apply {
            lineColor = Color.GREEN
            lineWidth = 2f
            textColor = Color.GREEN
            textSize = 12f
        }

        val maxGoalLine = LimitLine(140f, "Max Goal").apply {
            lineColor = Color.RED
            lineWidth = 2f
            textColor = Color.RED
            textSize = 12f
        }

        leftAxis.removeAllLimitLines()
        leftAxis.addLimitLine(minGoalLine)
        leftAxis.addLimitLine(maxGoalLine)

        leftAxis.axisMinimum = 0f

        barChart.axisRight.isEnabled = false
        barChart.description.isEnabled = false
        barChart.animateY(1000)
        barChart.invalidate()

        val totalSpending = entries.sumOf { it.y.toDouble() }
        tvSummary.text = "Total Spending: R$totalSpending"

        tvBadge.text = when {
            totalSpending <= 200 -> "🏅 Saver Badge"
            totalSpending <= 400 -> "💼 Budget Master"
            else -> "🔥 Big Spender!"
        }
    }
}
