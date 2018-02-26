package rq4;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import demo.DemoPanel;

public class ChartFeaturesOOB extends ApplicationFrame {

	/**
	 * Constructs a new demonstration application.
	 *
	 * @param title
	 *            the frame title.
	 */
	public ChartFeaturesOOB(final String title) {

		super(title);
		setContentPane(createDemoPanel());

	}

	private void setChartStyle(XYPlot plot) {
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinePaint(Color.black);
		plot.setDomainGridlinePaint(Color.black);
		XYItemRenderer renderer1 = plot.getRenderer();
		renderer1.setSeriesPaint(0, Color.BLUE);
		renderer1.setSeriesPaint(1, Color.BLACK);
		renderer1.setSeriesPaint(2, Color.RED);
		renderer1.setSeriesPaint(3, Color.GREEN);
		renderer1.setSeriesPaint(4, Color.CYAN);
		renderer1.setSeriesPaint(5, Color.MAGENTA);
		renderer1.setSeriesPaint(6, Color.darkGray);
		renderer1.setSeriesPaint(7, Color.ORANGE);
		renderer1.setSeriesStroke(0, new BasicStroke(1.1f));
		renderer1.setSeriesStroke(1, new BasicStroke(1.1f));
		renderer1.setSeriesStroke(2, new BasicStroke(1.1f));
		renderer1.setSeriesStroke(3, new BasicStroke(1.1f));
		renderer1.setSeriesStroke(4, new BasicStroke(1.1f));
		renderer1.setSeriesStroke(5, new BasicStroke(1.1f));
		renderer1.setSeriesStroke(6, new BasicStroke(1.1f));
		renderer1.setSeriesStroke(7, new BasicStroke(1.1f));
	}

	/**
	 * Creates a combined chart.
	 *
	 * @return the combined chart.
	 */
	private DemoPanel createDemoPanel() {

		DemoPanel panel = new DemoPanel(new GridLayout(3, 2));

		String dt1 = "Neutron";
		String dt2 = "Tomcat";
		String dt3 = "JGroups";
		
		
		// create subplot 1...
		XYDataset data1 = createDatasetFeatures(dt1, true);
		// final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1,
		// renderer1);
		JFreeChart chart1 = ChartFactory.createXYLineChart(dt1+" (Root)", "", "# Nodes", data1,
				PlotOrientation.VERTICAL, true, true, false);
		setChartStyle((XYPlot) chart1.getPlot());

		// chart 2
		XYDataset data2 = createDatasetFeatures(dt1, false);
		JFreeChart chart2 = ChartFactory.createXYLineChart(dt1+"(Sub Root)", "", "", data2, PlotOrientation.VERTICAL,
				true, true, false);
		setChartStyle((XYPlot) chart2.getPlot());

		// create subplot 1...
		XYDataset data3 = createDatasetFeatures(dt2, true);
		// final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1,
		// renderer1);
		JFreeChart chart3 = ChartFactory.createXYLineChart(dt2+" (Root)", "", "# Nodes", data3,
				PlotOrientation.VERTICAL, true, true, false);
		setChartStyle((XYPlot) chart3.getPlot());

		// chart 2
		XYDataset data4 = createDatasetFeatures(dt2, false);
		JFreeChart chart4 = ChartFactory.createXYLineChart(dt2+" (Sub Root)", "", "", data4, PlotOrientation.VERTICAL,
				true, true, false);
		setChartStyle((XYPlot) chart4.getPlot());

		// create subplot 1...
		XYDataset data5 = createDatasetFeatures(dt3, true);
		// final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1,
		// renderer1);
		JFreeChart chart5 = ChartFactory.createXYLineChart(dt3+" (Root)", "", "# Nodes", data5,
				PlotOrientation.VERTICAL, true, true, false);
		setChartStyle((XYPlot) chart5.getPlot());

		// chart 2
		XYDataset data6 = createDatasetFeatures(dt3, false);
		JFreeChart chart6 = ChartFactory.createXYLineChart(dt3+" (Sub Root)", "", "", data6,
				PlotOrientation.VERTICAL, true, true, false);
		setChartStyle((XYPlot) chart6.getPlot());


		panel.add(new ChartPanel(chart1));
		panel.add(new ChartPanel(chart2));
		panel.add(new ChartPanel(chart3));
		panel.add(new ChartPanel(chart4));
		panel.add(new ChartPanel(chart5));
		panel.add(new ChartPanel(chart6));

		panel.addChart(chart1);
		panel.addChart(chart2);
		panel.addChart(chart3);
		panel.addChart(chart4);
		panel.addChart(chart5);
		panel.addChart(chart6);

		
		
		// panel.addChart(chart2);
		// panel.addChart(chart3);
		// panel.addChart(chart4);

		panel.setPreferredSize(new Dimension(800, 600));
		return panel;

	}

	/**
	 * Creates a sample dataset.
	 *
	 * @return Series 2.
	 */
	private XYDataset createDatasetFeatures(String ds, boolean isRoot) {

		// create dataset 1...
		final XYSeries series1 = new XYSeries("FIX");
		final XYSeries series2 = new XYSeries("NS");
		final XYSeries series3 = new XYSeries("ND");
		final XYSeries series4 = new XYSeries("NF");
		final XYSeries series5 = new XYSeries("Entrophy");
		final XYSeries series6 = new XYSeries("LA");
		final XYSeries series7 = new XYSeries("LD");
		final XYSeries series8 = new XYSeries("LT");
		final XYSeries series9 = new XYSeries("NDEV");
		final XYSeries series10 = new XYSeries("AGE");
		final XYSeries series11 = new XYSeries("NUC");
		final XYSeries series12 = new XYSeries("EXP");
		final XYSeries series13 = new XYSeries("REXP");
		final XYSeries series14 = new XYSeries("SEXP");

		boolean addAtt1 = false;
		boolean addAtt2 = false;
		boolean addAtt3 = false;
		boolean addAtt4 = false;
		boolean addAtt5 = false;
		boolean addAtt6 = false;
		boolean addAtt7 = false;
		boolean addAtt8 = false;
		boolean addAtt9 = false;
		boolean addAtt10 = false;
		boolean addAtt11 = false;
		boolean addAtt12 = false;
		boolean addAtt13 = false;
		boolean addAtt14 = false;

		BufferedReader br = null;

		String file = "";
		if (isRoot) {
			file = "roots" + ds + ".txt";
		} else {
			file = "subRoots" + ds + ".txt";
		}

		try {
			br = new BufferedReader(new FileReader("RQ4/" + file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HashMap<Integer, Integer> hasOccorrenciasMesmoValor = new HashMap<>();
		for (int i = 0; i <= 50; i++) {
			hasOccorrenciasMesmoValor.put(i, 0);
		}

		StringTokenizer strTok = null;

		Integer[] atts = new Integer[14];
		double incrY = 0.1;
		String strAux = "";
		int ct = 0;
		try {

			while ((strAux = br.readLine()) != null) {

				// reset hash ocorrencias
				for (int i = 0; i <= 50; i++) {
					hasOccorrenciasMesmoValor.put(i, 0);
				}

				strTok = new StringTokenizer(strAux, "\t");

				for (int i = 0; i < 14; i++) {
					atts[i] = new Integer(strTok.nextToken());
				}

				for (int i = 0; i < 14; i++) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) + 1);
				}

				// adiciona valores nas series
				int i = 0;
				if (atts[i] > 0) {
					addAtt1 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series1.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));
				} else {
					series1.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt2 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series2.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));
				} else {
					series2.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt3 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series3.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));
				} else {
					series3.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt4 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series4.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));

				} else {
					series4.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt5 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series5.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));

				} else {
					series5.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt6 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series6.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));

				} else {
					series6.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt7 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series7.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));

				} else {
					series7.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt8 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series8.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));

				} else {
					series8.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt9 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series9.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));

				} else {
					series9.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt10 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series10.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));

				} else {
					series10.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt11 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series11.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));

				} else {
					series11.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt12 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series12.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));

				} else {
					series12.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt13 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series13.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));

				} else {
					series13.add(ct, atts[i]);
				}

				i++;
				if (atts[i] > 0) {
					addAtt14 = true;
				}
				if (hasOccorrenciasMesmoValor.get(atts[i]) > 0 && atts[i] != 0) {
					hasOccorrenciasMesmoValor.put(atts[i], hasOccorrenciasMesmoValor.get(atts[i]) - 1);
					series14.add(ct, atts[i] + (hasOccorrenciasMesmoValor.get(atts[i]) * incrY));

				} else {
					series14.add(ct, atts[i]);
				}

				ct++;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final XYSeriesCollection collection = new XYSeriesCollection();

		if (addAtt1) {
			collection.addSeries(series1);
		}

		if (addAtt2) {
			collection.addSeries(series2);
		}

		if (addAtt3) {
			collection.addSeries(series3);
		}
		if (addAtt4) {
			collection.addSeries(series4);
		}
		if (addAtt5) {
			collection.addSeries(series5);
		}
		if (addAtt6) {
			collection.addSeries(series6);
		}
		if (addAtt7) {
			collection.addSeries(series7);
		}
		if (addAtt8) {
			collection.addSeries(series8);
		}
		if (addAtt9) {
			collection.addSeries(series9);
		}
		if (addAtt10) {
			collection.addSeries(series10);
		}
		if (addAtt11) {
			collection.addSeries(series11);
		}
		if (addAtt12) {
			collection.addSeries(series12);
		}
		if (addAtt13) {
			collection.addSeries(series13);
		}
		if (addAtt14) {
			collection.addSeries(series14);
		}

		return collection;

	}

	// ****************************************************************************
	// * JFREECHART DEVELOPER GUIDE *
	// * The JFreeChart Developer Guide, written by David Gilbert, is available
	// *
	// * to purchase from Object Refinery Limited: *
	// * *
	// * http://www.object-refinery.com/jfreechart/guide.html *
	// * *
	// * Sales are used to provide funding for the JFreeChart project - please *
	// * support us so that we can continue developing free software. *
	// ****************************************************************************

	/**
	 * Starting point for the demonstration application.
	 *
	 * @param args
	 *            ignored.
	 */
	public static void main(final String[] args) {

		final ChartFeaturesOOB demo = new ChartFeaturesOOB("CombinedDomainXYPlot Demo");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}
	
	static public final String saveChartToSVG(final JFreeChart chart,
			String fileName, final int width, final int height)
			throws IOException {
		String result = null;
		
		
		DemoPanel d = new DemoPanel(new GridLayout(3, 2));
		

		if (chart != null) {
			if (fileName == null) {
				final String chartTitle = chart.getTitle().getText();
				if (chartTitle != null) {
					fileName = chartTitle;
				} else {
					fileName = "chart";
				}
			}
			result = fileName + ".svg";

			final DOMImplementation domImpl = GenericDOMImplementation
					.getDOMImplementation();
			final Document document = domImpl.createDocument(null, "svg", null);
			final SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

			// svgGenerator.getGeneratorContext().setEmbeddedFontsOn(true);
			// //embed fonts

			// set anti-aliasing bug fix for SVG generator:
			// setting rendering hints of svgGenerator doesn't help as it seems
			// to use the rendering hints from the chart
			// final boolean antiAlias = isAntiAliasOn(chart);
			final RenderingHints rh = chart.getRenderingHints();

			// if (antiAlias) {
			rh.put(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			rh.put(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_OFF); // fix
			// } else {
			// rh.put(RenderingHints.KEY_ANTIALIASING,
			// RenderingHints.VALUE_ANTIALIAS_OFF);
			// rh.put(RenderingHints.KEY_TEXT_ANTIALIASING,
			// RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //fix
			// }

			// svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			// RenderingHints.VALUE_ANTIALIAS_ON);
			// svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			// RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
			// svgGenerator.setRenderingHint(RenderingHints.KEY_RENDERING,
			// RenderingHints.VALUE_RENDER_QUALITY);
			// svgGenerator.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
			// RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			// svgGenerator.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			// RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			// svgGenerator.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
			// RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

			chart.draw(svgGenerator,
					new Rectangle2D.Double(0, 0, width, height),
					new ChartRenderingInfo());

			// undo anti-aliasing bugfix settings for chart to use correct
			// settings:
			// if (antiAlias) {
			// rh.put(RenderingHints.KEY_ANTIALIASING,
			// RenderingHints.VALUE_ANTIALIAS_ON);
			// rh.put(RenderingHints.KEY_TEXT_ANTIALIASING,
			// RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			// } else {
			rh.put(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_OFF);
			rh.put(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
			// }

			final boolean useCSS = true;
			Writer out = null;
			try {
				out = new OutputStreamWriter(new BufferedOutputStream(
						new FileOutputStream(new File(result), false)),
						"iso-8859-1"); // UTF-8
				svgGenerator.stream(out, useCSS);
			} finally {
				svgGenerator.dispose();
				// IOUtils.close(out);
				out.close();
			}
		}// else: input unavailable

		return result;
	}// saveChartToSVG()

}