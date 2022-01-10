package com.bws.officeapp.timesheet;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Mekko;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Orientation;
import com.bws.officeapp.R;

import java.util.ArrayList;
import java.util.List;

public class MekkoChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        getSupportActionBar().hide();
        TextView textUserName = findViewById(R.id.textUserName);
        textUserName.setText(getResources().getText(R.string.WELCOME_TO_TIME_SHEET_APP));

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));


        Mekko mekkoChart = AnyChart.mekko();


        List<DataEntry> data = new ArrayList<>();
        data.add(new CustomDataEntry("Goya", Integer.parseInt("15") , Integer.parseInt("5"), Integer.parseInt("10")));
        data.add(new CustomDataEntry("AEML", Integer.parseInt("18") , Integer.parseInt("5"), Integer.parseInt("5")));
        data.add(new CustomDataEntry("LIMS", Integer.parseInt("15") , Integer.parseInt("10"), Integer.parseInt("5")));
        data.add(new CustomDataEntry("Office App", Integer.parseInt("15") , Integer.parseInt("5"), Integer.parseInt("7")));

        Set set = Set.instantiate();
        set.data(data);
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Data = set.mapAs("{ x: 'x', value: 'value3' }");
      //  Mapping series4Data = set.mapAs("{ x: 'x', value: 'value4' }");

        mekkoChart.mekko(series1Data)
                .name("Allocated Time");

        mekkoChart.mekko(series2Data)
                .name("Spend Time");

        mekkoChart.mekko(series3Data)
                .name("Remain Time");

       // mekkoChart.mekko(series4Data).name("Laptop");

        mekkoChart.xAxis(0).orientation(Orientation.TOP);

      //  mekkoChart.labels().format("2D{%Value}h");
        mekkoChart.labels().format("{%Value} Day");

        mekkoChart.tooltip().format("{%seriesName}: {%Value} Day");

        anyChartView.setChart(mekkoChart);
    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
            //setValue("value4", value4);
        }
    }
}