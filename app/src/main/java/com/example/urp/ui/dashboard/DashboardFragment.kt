package com.example.urp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.urp.MainActivity.Companion.activityScores
import com.example.urp.R

class DashboardFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })

        // Pie chart for each activity's score
        var allZero = true
        for (activityScore in activityScores) if (activityScore != 0) allZero = false

        if (allZero) for (i in activityScores.indices) activityScores[i] = 1

        val pie = AnyChart.pie()
        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Walking", activityScores[0]))
        data.add(ValueDataEntry("Drinking Water", activityScores[1]))
        data.add(ValueDataEntry("Pushups", activityScores[2]))
        data.add(ValueDataEntry("Situps", activityScores[3]))
        data.add(ValueDataEntry("Streching", activityScores[4]))
        pie.data(data)

        if (allZero) for (i in activityScores.indices) activityScores[i] = 0

        // Column chart perhaps for recent daily scores
        val columnChart = AnyChart.column()
        val columnData: MutableList<DataEntry> = ArrayList()
        columnData.add(ValueDataEntry("Day 1", activityScores[0]))
        columnData.add(ValueDataEntry("Day 2", activityScores[1]))
        columnData.add(ValueDataEntry("Day 3", activityScores[2]))
        columnData.add(ValueDataEntry("Day 4", activityScores[3]))
        columnData.add(ValueDataEntry("Day 5", activityScores[4]))
        columnChart.data(columnData)

        // Line chart perhaps for overall daily scores
        val lineChart = AnyChart.line()
        val lineData: MutableList<DataEntry> = ArrayList()
        lineData.add(ValueDataEntry("Date 1", activityScores[0]))
        lineData.add(ValueDataEntry("Date 2", activityScores[1]))
        lineData.add(ValueDataEntry("Date 3", activityScores[2]))
        lineData.add(ValueDataEntry("Date 4", activityScores[3]))
        lineData.add(ValueDataEntry("Date 5", activityScores[4]))
        lineChart.data(lineData)

        val anyChartView = root.findViewById(R.id.any_chart_view) as AnyChartView
        anyChartView.setChart(pie)
        return root
    }
}
