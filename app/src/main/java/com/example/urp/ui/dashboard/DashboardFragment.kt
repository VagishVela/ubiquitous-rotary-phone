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
import com.example.urp.MainActivity
import com.example.urp.MainActivity.Companion.activityScores
import com.example.urp.MainActivity.Companion.dailyScores
import com.example.urp.R
import java.util.*
import kotlin.collections.ArrayList

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
        val dailyScoresClone: Queue<Int> = copy(dailyScores)
        val columnChart = AnyChart.column()
        val columnData: MutableList<DataEntry> = ArrayList()
        if (!dailyScoresClone.isEmpty())
            columnData.add(ValueDataEntry("Day 1", dailyScoresClone.poll()))
        if (!dailyScoresClone.isEmpty())
            columnData.add(ValueDataEntry("Day 2", dailyScoresClone.poll()))
        if (!dailyScoresClone.isEmpty())
            columnData.add(ValueDataEntry("Day 3", dailyScoresClone.poll()))
        if (!dailyScoresClone.isEmpty())
            columnData.add(ValueDataEntry("Day 4", dailyScoresClone.poll()))
        if (!dailyScoresClone.isEmpty())
            columnData.add(ValueDataEntry("Day 5", dailyScoresClone.poll()))
        columnChart.data(columnData)

        // Line chart perhaps for overall daily scores
        val lineChart = AnyChart.line()
        val lineData: MutableList<DataEntry> = ArrayList()
        for ((i, dailyScore) in MainActivity.overallDailyScores.withIndex())
            lineData.add(ValueDataEntry("Date ${i + 1}", dailyScore))
        lineChart.data(lineData)

        val pieChartView = root.findViewById(R.id.any_chart_view) as AnyChartView
        pieChartView.setChart(pie)
        val columnChartView = root.findViewById(R.id.any_chart_view1) as AnyChartView
        columnChartView.setChart(columnChart)
        val lineChartView = root.findViewById(R.id.any_chart_view2) as AnyChartView
        lineChartView.setChart(lineChart)
        return root
    }

    private fun copy(queue: Queue<Int>) = LinkedList<Int>(queue)
}
