package rq4;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Janela extends JPanel implements Render {

	static JFrame frm = null;

	static int idxColor = 0;
	static Color[] colors = { Color.BLUE, Color.RED, Color.GREEN, Color.CYAN, Color.GRAY, Color.ORANGE };

	static XYSeries series = new XYSeries("normais");

	static XYPlot plot = new XYPlot();
	static XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	static JFreeChart chart = null;

	static XYSeriesCollection xyDataset = new XYSeriesCollection();

	static int idxSerie = 2;

	static double upperBoundX = 1.2;
	static double lowerBoundX = 0.00;
	static double upperBoundY = 1.2;
	static double lowerBoundY = 0.00;
	static double[] coordsX = new double[100];
	static double[] coordsY = new double[100];
	static double[][] pontosGeracaoCurva = new double[100][100];

	static Double C = 1.0;
	static Double sigma = 1.0;

	public Janela() {

	}

	public static void main(String args[]) {

		// double[][] vetTest = new double[100][100];
		// double[] coordsx = new double[100];
		// double[] coordsy = new double[100];
		// int ct = 0;
		// for(int i = 0; i < 100; i++){
		// coordsx[i] = i/new Double(100);
		// coordsy[i] = i/new Double(100);
		// for(int j = 0; j < 100; j++){
		//
		// if((i*j) < 4000){
		// vetTest[i][j] = -1;
		// }else{
		// vetTest[i][j] = 1;
		// }
		//
		// }
		// }

		FileReader in = null;
		try {
			in = new FileReader("RQ4/outs10000.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(in);

		double[][] vetTest = new double[20][20];
		double[] coordsx = new double[20];
		double[] coordsy = new double[20];

		for (int i = 1; i < 20; i++) {
			coordsx[i] = coordsx[i - 1] + 0.25;
		}

		for (int j = 1; j < 20; j++) {
			coordsy[j] = coordsy[j - 1] + 140;
		}

		String strAux = "";
		int ctX = 0;
		try {
			while ((strAux = br.readLine()) != null && ctX < 20) {
				StringTokenizer strTok = new StringTokenizer(strAux, "\t");

				for (int j = 0; j < 20; j++) {
					vetTest[ctX][j] = new Double(strTok.nextToken());
				}

				ctX++;

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean addPontos = false;

		if (addPontos)
			plotaModelo(vetTest);
		else {
			plotaModelo(vetTest);

			// xyDataset.removeSeries(0);
			// idxSerie--;
		}

		double[] z = new double[1];
		// ocsvm
		z[0] = 5.;
		// z[0] = -0.001;

		// contour(pontosGeracaoCurva, lowerBoundX, upperBoundX, lowerBoundY,
		// upperBoundY, coordsX, coordsY, 1, z);
		contour(vetTest, 0, 19, 0, 19, coordsx, coordsy, 1, z);

		// try {
		// saveChartToSVG(chart, "teste3.svg", 600, 400);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		System.out.println("Fim");
	}

	public static void criaCoordsXY() {

		double incrX = (upperBoundX - lowerBoundX) / 100.;
		double incrY = (upperBoundY - lowerBoundY) / 100.;

		for (int i = 0; i < 100; i++) {

			coordsX[i] = lowerBoundX + (i * incrX);
			coordsY[i] = lowerBoundY + (i * incrY);

		}

	}

	public static void plotaModelo(double[][] tes) {

//		XYSeries series = new XYSeries("normais");
//		XYSeries series2 = new XYSeries("novidade");
//
//		for (int i = 0; i < 100; i++) {
//			for (int j = 0; j < 100; j++) {
//				if (tes[i][j] < 0) {
//					if (i * j % 50 == 0) {
//						series.add(i / new Double(100), j / new Double(100));
//					}
//
//				}
//			}
//		}
//
//		for (int i = 0; i < 100; i++) {
//			for (int j = 0; j < 100; j++) {
//				if (tes[i][j] >= 0) {
//					if (i * j % 50 == 0) {
//						series2.add(i / new Double(100), j / new Double(100));
//					}
//				}
//			}
//		}
//
//		xyDataset.addSeries(series);
//		xyDataset.addSeries(series2);

		chart = ChartFactory.createScatterPlot("Artificial data set", // Title
				"X", // X-Axis label
				"Y", // Y-Axis label
				xyDataset, // Dataset
				PlotOrientation.VERTICAL, // data
				false, // include legend
				true, // tooltips
				false // urls
		);

		plot = chart.getXYPlot();
		plot.setRangeGridlinesVisible(false);
		plot.setDomainGridlinesVisible(false);

		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesLinesVisible(1, false);

		renderer.setSeriesPaint(0, Color.BLUE);
		renderer.setSeriesPaint(1, Color.GREEN);
		// Shape cross = ShapeUtils.createDiagonalCross(3, 1);
		// renderer.setSeriesShape(0, cross);

		plot.setRenderer(renderer);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setMouseZoomable(true);
		chartPanel.setBackground(Color.white);

		if (frm == null)
			frm = new JFrame();

		frm.setLayout(new BorderLayout());
		frm.setBackground(Color.white);
		frm.setForeground(Color.white);
		frm.add(chartPanel);
		frm.setBounds(new Rectangle(100, 120, 400, 300));
		frm.setVisible(true);
		frm.setDefaultCloseOperation(2);

	}

	public static void quicksort(Double[] vet, int left, int right) throws CloneNotSupportedException {
		int i, j;
		Double tmp;
		Double mid;

		i = left;
		j = right;
		mid = vet[(left + right) / 2];
		do {
			while (vet[i] < mid)
				i++;
			while (mid < vet[j])
				j--;
			if (i <= j) {
				tmp = new Double(vet[i].doubleValue());
				vet[i] = vet[j];
				vet[j] = tmp;
				i++;
				j--;
			}
		} while (i <= j);
		if (left < j)
			quicksort(vet, left, j);
		if (i < right)
			quicksort(vet, i, right);
	}

	@Override
	public void drawContour(double startX, double startY, double endX, double endY) {
		// TODO Auto-generated method stub

	}

	private static double[] h = new double[5];
	private static int[] sh = new int[5];
	private static double[] xh = new double[5];
	private static double[] yh = new double[5];

	public static void contour(double[][] d, int ilb, int iub, int jlb, int jub, double[] x, double[] y, int nc,
			double[] z) {
		int m1;
		int m2;
		int m3;
		int case_value;
		double dmin;
		double dmax;
		double x1 = 0.0;
		double x2 = 0.0;
		double y1 = 0.0;
		double y2 = 0.0;
		int i, j, k, m;

		// The indexing of im and jm should be noted as it has to start from
		// zero
		// unlike the fortran counter part
		int[] im = { 0, 1, 1, 0 };
		int[] jm = { 0, 0, 1, 1 };

		// Note that castab is arranged differently from the FORTRAN code
		// because
		// Fortran and C/C++ arrays are transposed of each other, in this case
		// it is more tricky as castab is in 3 dimension
		int[][][] castab = { { { 0, 0, 8 }, { 0, 2, 5 }, { 7, 6, 9 } }, { { 0, 3, 4 }, { 1, 3, 1 }, { 4, 3, 0 } },
				{ { 9, 6, 7 }, { 5, 2, 0 }, { 8, 0, 0 } } };

		for (j = (jub - 1); j >= jlb; j--) {
			for (i = ilb; i <= iub - 1; i++) {
				double temp1, temp2;
				temp1 = Math.min(d[i][j], d[i][j + 1]);
				temp2 = Math.min(d[i + 1][j], d[i + 1][j + 1]);
				dmin = Math.min(temp1, temp2);
				temp1 = Math.max(d[i][j], d[i][j + 1]);
				temp2 = Math.max(d[i + 1][j], d[i + 1][j + 1]);
				dmax = Math.max(temp1, temp2);

				if (dmax >= z[0] && dmin <= z[nc - 1]) {
					for (k = 0; k < nc; k++) {
						if (z[k] >= dmin && z[k] <= dmax) {
							for (m = 4; m >= 0; m--) {
								if (m > 0) {
									// The indexing of im and jm should be noted
									// as it has to
									// start from zero
									h[m] = d[i + im[m - 1]][j + jm[m - 1]] - z[k];
									xh[m] = x[i + im[m - 1]];
									yh[m] = y[j + jm[m - 1]];
								} else {
									h[0] = 0.25 * (h[1] + h[2] + h[3] + h[4]);
									xh[0] = 0.5 * (x[i] + x[i + 1]);
									yh[0] = 0.5 * (y[j] + y[j + 1]);
								}
								if (h[m] > 0.0) {
									sh[m] = 1;
								} else if (h[m] < 0.0) {
									sh[m] = -1;
								} else
									sh[m] = 0;
							}
							//
							// Note: at this stage the relative heights of the
							// corners and the
							// centre are in the h array, and the corresponding
							// coordinates are
							// in the xh and yh arrays. The centre of the box is
							// indexed by 0
							// and the 4 corners by 1 to 4 as shown below.
							// Each triangle is then indexed by the parameter m,
							// and the 3
							// vertices of each triangle are indexed by
							// parameters m1,m2,and
							// m3.
							// It is assumed that the centre of the box is
							// always vertex 2
							// though this isimportant only when all 3 vertices
							// lie exactly on
							// the same contour level, in which case only the
							// side of the box
							// is drawn.
							//
							//
							// vertex 4 +-------------------+ vertex 3
							// | \ / |
							// | \ m-3 / |
							// | \ / |
							// | \ / |
							// | m=2 X m=2 | the centre is vertex 0
							// | / \ |
							// | / \ |
							// | / m=1 \ |
							// | / \ |
							// vertex 1 +-------------------+ vertex 2
							//
							//
							//
							// Scan each triangle in the box
							//
							for (m = 1; m <= 4; m++) {
								m1 = m;
								m2 = 0;
								if (m != 4) {
									m3 = m + 1;
								} else {
									m3 = 1;
								}
								case_value = castab[sh[m1] + 1][sh[m2] + 1][sh[m3] + 1];
								if (case_value != 0) {
									switch (case_value) {
									case 1: // Line between vertices 1 and 2
										x1 = xh[m1];
										y1 = yh[m1];
										x2 = xh[m2];
										y2 = yh[m2];
										break;
									case 2: // Line between vertices 2 and 3
										x1 = xh[m2];
										y1 = yh[m2];
										x2 = xh[m3];
										y2 = yh[m3];
										break;
									case 3: // Line between vertices 3 and 1
										x1 = xh[m3];
										y1 = yh[m3];
										x2 = xh[m1];
										y2 = yh[m1];
										break;
									case 4: // Line between vertex 1 and side
											// 2-3
										x1 = xh[m1];
										y1 = yh[m1];
										x2 = xsect(m2, m3);
										y2 = ysect(m2, m3);
										break;
									case 5: // Line between vertex 2 and side
											// 3-1
										x1 = xh[m2];
										y1 = yh[m2];
										x2 = xsect(m3, m1);
										y2 = ysect(m3, m1);
										break;
									case 6: // Line between vertex 3 and side
											// 1-2
										x1 = xh[m3];
										y1 = yh[m3];
										x2 = xsect(m1, m2);
										y2 = ysect(m1, m2);
										break;
									case 7: // Line between sides 1-2 and 2-3
										x1 = xsect(m1, m2);
										y1 = ysect(m1, m2);
										x2 = xsect(m2, m3);
										y2 = ysect(m2, m3);
										break;
									case 8: // Line between sides 2-3 and 3-1
										x1 = xsect(m2, m3);
										y1 = ysect(m2, m3);
										x2 = xsect(m3, m1);
										y2 = ysect(m3, m1);
										break;
									case 9: // Line between sides 3-1 and 1-2
										x1 = xsect(m3, m1);
										y1 = ysect(m3, m1);
										x2 = xsect(m1, m2);
										y2 = ysect(m1, m2);
										break;
									default:
										break;
									}
									// Put your processing code here and comment
									// out the printf
									// printf("%f %f %f %f
									// %f\n",x1,y1,x2,y2,z[k]);
									// render.drawContour(x1,y1,x2,y2,z[k]);
									// sem o z[k]
									// render.drawContour(x1,y1,x2,y2);

									XYSeries s = new XYSeries("serie " + idxSerie);

									s.add(x1, y1);
									s.add(x2, y2);

									renderer.setSeriesShapesVisible(idxSerie, false);
									// renderer.setSeriesShapesFilled(idxSerie,
									// false);
									renderer.setSeriesLinesVisible(idxSerie, true);
									renderer.setSeriesStroke(idxSerie, new BasicStroke(1.5f));

									renderer.setSeriesPaint(idxSerie, Color.BLACK);

									idxSerie++;

									xyDataset.addSeries(s);
								}
							}
						}
					}
				}
			}
		}
	}

	private static double xsect(int p1, int p2) {
		return (h[p2] * xh[p1] - h[p1] * xh[p2]) / (h[p2] - h[p1]);
	}

	private static double ysect(int p1, int p2) {
		return (h[p2] * yh[p1] - h[p1] * yh[p2]) / (h[p2] - h[p1]);
	}

}

class ItemGenerator implements XYToolTipGenerator {
	public ItemGenerator() {
	}

	@Override
	public String generateToolTip(XYDataset dataset, int series, int category) {
		String giornata_sq = null;
		String punti_sq = null;

		// Number value = dataset.getValue(series, category);
		Number value = (int) dataset.getXValue(series, category);
		Number value1 = (int) dataset.getYValue(series, category);

		if (value != null && value1 != null) {
			giornata_sq = value.toString();
			punti_sq = value1.toString();
		}
		return giornata_sq + "° giornata, " + punti_sq + " punti";
	}
}