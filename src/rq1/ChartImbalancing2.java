package rq1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.util.Vector;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import entities.Sample;
import util.Util;

public class ChartImbalancing2 extends ApplicationFrame {

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public ChartImbalancing2(final String title) {

        super(title);
        final JFreeChart chart = createCombinedChart();
        final ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);

    }

    /**
     * Creates a combined chart.
     *
     * @return the combined chart.
     */
    private JFreeChart createCombinedChart() {

        // create subplot 1...
        final XYDataset data1 = createDataset("arffs/brackets.arff");
        final XYItemRenderer renderer1 = new StandardXYItemRenderer();
        renderer1.setSeriesPaint( 0 , Color.BLUE );
        renderer1.setSeriesPaint( 1 , Color.RED);
        renderer1.setSeriesStroke(0, new BasicStroke(1.05f));
        renderer1.setSeriesStroke(1, new BasicStroke(1.05f));
        final NumberAxis rangeAxis1 = new NumberAxis("brackets");
        rangeAxis1.setRange(0, 100); 
        final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, renderer1);
        subplot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        
//        final XYTextAnnotation annotation = new XYTextAnnotation("Hello!", 50.0, 10000.0);
//        annotation.setFont(new Font("SansSerif", Font.PLAIN, 9));
//        annotation.setRotationAngle(Math.PI / 4.0);
//        subplot1.addAnnotation(annotation);
        
        // create subplot 2...
        final XYDataset data2 = createDataset("arffs/camel.arff");
        final XYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setSeriesPaint( 0 , Color.BLUE );
        
        renderer2.setSeriesPaint( 1 , Color.RED);
        renderer2.setSeriesStroke(0, new BasicStroke(1.05f));
        renderer2.setSeriesStroke(1, new BasicStroke(1.05f));
        final NumberAxis rangeAxis2 = new NumberAxis("camel");
        rangeAxis2.setRange(0, 100);
        //rangeAxis2.setAutoRangeIncludesZero(false);
        final XYPlot subplot2 = new XYPlot(data2, null, rangeAxis2, renderer2);
        subplot2.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        
     // create subplot 3...
        final XYDataset data3 = createDataset("arffs/fabric.arff");
        final XYItemRenderer renderer3 = new StandardXYItemRenderer();
        renderer3.setSeriesPaint( 0 , Color.BLUE );
        renderer3.setSeriesPaint( 1 , Color.RED);
        renderer3.setSeriesStroke(0, new BasicStroke(1.05f));
        renderer3.setSeriesStroke(1, new BasicStroke(1.05f));
        final NumberAxis rangeAxis3 = new NumberAxis("fabric");
        rangeAxis3.setRange(0, 100);
        //rangeAxis2.setAutoRangeIncludesZero(false);
        final XYPlot subplot3 = new XYPlot(data3, null, rangeAxis3, renderer3);
        subplot3.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

        // create subplot 4...
        final XYDataset data4 = createDataset("arffs/jgroups.arff");
        final XYItemRenderer renderer4 = new StandardXYItemRenderer();
        renderer4.setSeriesPaint( 0 , Color.BLUE );
        renderer4.setSeriesPaint( 1 , Color.RED);
        renderer4.setSeriesStroke(0, new BasicStroke(1.05f));
        renderer4.setSeriesStroke(1, new BasicStroke(1.05f));
        final NumberAxis rangeAxis4 = new NumberAxis("jgroups");
        rangeAxis4.setRange(0, 100);
        //rangeAxis2.setAutoRangeIncludesZero(false);
        final XYPlot subplot4 = new XYPlot(data4, null, rangeAxis4, renderer4);
        subplot4.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

        // create subplot 5...
        final XYDataset data5 = createDataset("arffs/neutron.arff");
        final XYItemRenderer renderer5 = new StandardXYItemRenderer();
        renderer5.setSeriesPaint( 0 , Color.BLUE );
        renderer5.setSeriesPaint( 1 , Color.RED);
        renderer5.setSeriesStroke(0, new BasicStroke(1.05f));
        renderer5.setSeriesStroke(1, new BasicStroke(1.05f));
        final NumberAxis rangeAxis5 = new NumberAxis("neutron");
        rangeAxis5.setRange(0, 100);
        //rangeAxis2.setAutoRangeIncludesZero(false);
        final XYPlot subplot5 = new XYPlot(data5, null, rangeAxis5, renderer5);
        subplot5.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

        
        // create subplot 6...
        final XYDataset data6 = createDataset("arffs/tomcat.arff");
        final XYItemRenderer renderer6 = new StandardXYItemRenderer();
        renderer6.setSeriesPaint( 0 , Color.BLUE );
        renderer6.setSeriesPaint( 1 , Color.RED);
        renderer6.setSeriesStroke(0, new BasicStroke(1.05f));
        renderer6.setSeriesStroke(1, new BasicStroke(1.05f));
        final NumberAxis rangeAxis6 = new NumberAxis("tomcat");
        rangeAxis6.setRange(0, 100);
        //rangeAxis2.setAutoRangeIncludesZero(false);
        final XYPlot subplot6 = new XYPlot(data6, null, rangeAxis6, renderer6);
        subplot6.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        
        // parent plot...
        final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("Domain"));
        plot.setGap(10.0);
        
        // add the subplots...
        plot.add(subplot1, 1);
        plot.add(subplot2, 1);
        plot.add(subplot3, 1);
        plot.add(subplot4, 1);
        plot.add(subplot5, 1);
        plot.add(subplot6, 1);
        
        plot.setOrientation(PlotOrientation.VERTICAL);

        // return a new chart containing the overlaid plot...
        return new JFreeChart("CombinedDomainXYPlot Demo",
                              JFreeChart.DEFAULT_TITLE_FONT, plot, true);

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

        final ChartImbalancing2 demo = new ChartImbalancing2("CombinedDomainXYPlot Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}