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
//import com.anychart.R
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.urp.R

//import com.anychart.AnyChart
//import com.anychart.AnyChartView
//import com.anychart.DataEntry
//import com.anychart.Pie
//import com.anychart.ValueDataEntry

class DashboardFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })
        val pie = AnyChart.pie()

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Running", 100))
        data.add(ValueDataEntry("Drinking Water", 200))
        data.add(ValueDataEntry("Pushups", 50))
        pie.data(data)

        val anyChartView = root.findViewById(R.id.any_chart_view) as AnyChartView
        anyChartView.setChart(pie)
        return root
    }
}
