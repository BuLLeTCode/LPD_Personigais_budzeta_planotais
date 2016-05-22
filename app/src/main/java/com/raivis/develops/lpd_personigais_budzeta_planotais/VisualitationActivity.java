package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.graphics.DashPathEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisualitationActivity extends AppCompatActivity {

    private XYPlot plot;
    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.simple_xy_plot_example);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigh = dm.heightPixels;

        getWindow().setLayout((int)(width*.8f), (int)(heigh*.6f));

        plot = (XYPlot) findViewById(R.id.plot);
        db = new DBHandler(this);

        // create a couple arrays of y-values to plot:
        List<Outcome> userOutcomes = db.getAllOutcomes();
        List<Double> outcomeNumbers = new ArrayList<Double>();

        for(Outcome out : userOutcomes) {
            String [] outInfo = out.getOutcomeInfo();
            outcomeNumbers.add(Double.parseDouble(outInfo[1]));
        }

        List<Income> userIncomes = db.getAllIncomes();
        List<Double> incomeNumbers = new ArrayList<Double>();

        for(Income in : userIncomes)
        {
            String[] inInfo = in.getIncomeInfo();
            incomeNumbers.add(Double.parseDouble(inInfo[1]));
        }

//        Number[] series1Numbers = {1, 4, 2, 8, 4, 16, 8, 32, 16, 64};

        // turn the above arrays into XYSeries':
        // (Y_VALS_ONLY means use the element index as the x value)
        XYSeries series1 = new SimpleXYSeries(outcomeNumbers,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Izdevumi");

        XYSeries series2 = new SimpleXYSeries(incomeNumbers,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Ienākumi");

        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_labels);

        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_labels_2);
        //Min 3 points for Interpolation
        if(incomeNumbers.size() >= 3 && outcomeNumbers.size() >= 3)
        {
            series1Format.setInterpolationParams(
                    new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

            series2Format.setInterpolationParams(
                    new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));
        }

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);
        plot.addSeries(series2, series2Format);

        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);

        // rotate domain labels 45 degrees to make them more compact horizontally:
        plot.getGraphWidget().setDomainLabelOrientation(-45);
    }
}
