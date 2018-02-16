package rq1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import demo.DemoPanel;
import entities.Sample;
import util.Util;

public class ChartImbalancing extends ApplicationFrame {

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public ChartImbalancing(final String title) {

        super(title);
        setContentPane(createDemoPanel());

        
        

    }
    
    private void setChartStyle(XYPlot plot){
    	plot.setBackgroundPaint(Color.white);
    	plot.setRangeGridlinePaint(Color.black);
    	plot.setDomainGridlinePaint(Color.black);
        XYItemRenderer renderer1 = plot.getRenderer();
        renderer1.setSeriesPaint( 0 , Color.BLUE );
        renderer1.setSeriesPaint( 1 , Color.RED);
        renderer1.setSeriesStroke(0, new BasicStroke(1.05f));
        renderer1.setSeriesStroke(1, new BasicStroke(1.05f));	
    }

    /**
     * Creates a combined chart.
     *
     * @return the combined chart.
     */
    private DemoPanel createDemoPanel() {

    	DemoPanel panel = new DemoPanel(new GridLayout(3, 2));
    	
    	
    	
        // create subplot 1...
        XYDataset data1 = createDataset("arffs/brackets.arff");
        //final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, renderer1);
        JFreeChart chart1 = ChartFactory.createXYLineChart("Brackets", "", "% data size", data1, PlotOrientation.VERTICAL, false, true, false);
        setChartStyle((XYPlot) chart1.getPlot());
        
        

        
        //chart 2
        XYDataset data2 = createDataset("arffs/camel.arff");
        JFreeChart chart2 = ChartFactory.createXYLineChart("Camel", "", "", data2, PlotOrientation.VERTICAL, false, true, false);
        setChartStyle((XYPlot) chart2.getPlot());
        
        
        //chart 3
        XYDataset data3 = createDataset("arffs/fabric.arff");
        JFreeChart chart3 = ChartFactory.createXYLineChart("Fabric", "", "% data size", data3, PlotOrientation.VERTICAL, false, true, false);
        setChartStyle((XYPlot) chart3.getPlot());
        
        
      //chart 3
        XYDataset data4 = createDataset("arffs/jgroups.arff");
        JFreeChart chart4 = ChartFactory.createXYLineChart("JGroups", "", "", data4, PlotOrientation.VERTICAL, false, true, false);
        setChartStyle((XYPlot) chart4.getPlot());

        //chart 5
        XYDataset data5 = createDataset("arffs/neutron.arff");
        JFreeChart chart5 = ChartFactory.createXYLineChart("Neutron", "time step", "% data size", data5, PlotOrientation.VERTICAL, false, true, false);
        setChartStyle((XYPlot) chart5.getPlot());
        
      //chart 6
        XYDataset data6 = createDataset("arffs/tomcat.arff");
        JFreeChart chart6 = ChartFactory.createXYLineChart("Tomcat", "time step", "", data6, PlotOrientation.VERTICAL, false, true, false);//();
        setChartStyle((XYPlot) chart6.getPlot());
        
        
        
        
        
        
        panel.add(new ChartPanel(chart1));
        panel.add(new ChartPanel(chart2));
        panel.add(new ChartPanel(chart3));
        panel.add(new ChartPanel(chart4));
        panel.add(new ChartPanel(chart5));
        panel.add(new ChartPanel(chart6));
//        panel.add(new ChartPanel(chart2));
//        panel.add(new ChartPanel(chart3));
//        panel.add(new ChartPanel(chart4));

        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.addChart(chart3);
        panel.addChart(chart4);
        panel.addChart(chart5);
        panel.addChart(chart6);
//        panel.addChart(chart2);
//        panel.addChart(chart3);
//        panel.addChart(chart4);

        panel.setPreferredSize(new Dimension(800, 600));
        return panel;

    }

    
    /**
     * Creates a sample dataset.
     *
     * @return Series 2.
     */
    private XYDataset createDataset(String ds) {

        // create dataset 1...
        final XYSeries series1 = new XYSeries("Neg");
        final XYSeries series2 = new XYSeries("Pos");
        
        Vector<Sample> vetSam = null;
        try {
			vetSam = Util.openDsArff(ds);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int neg = 0;
        int pos = 0;
        
        for(int i = 0; i < vetSam.size(); i++){
        	
        	Sample s = vetSam.get(i);
        	
        	if(s.getClassValue().equals("False")){
        		neg++;
        	}else{
        		pos++;
        	}
        	series1.add(i, (new Double(neg)/vetSam.size())*100);
        	series2.add(i, (new Double(pos)/vetSam.size())*100);
        	
        	
        }
        final XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series1);
        collection.addSeries(series2);
        return collection;

    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

    	final ChartImbalancing demo = new ChartImbalancing("CombinedDomainXYPlot Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}