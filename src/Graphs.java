import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.demo.charts.RealtimeExampleChart;
import org.knowm.xchart.style.Styler.ChartTheme;

/**
 * Real-time XY Chart
 *
 * <p>
 * Demonstrates the following:
 *
 * <ul>
 * <li>real-time chart updates with SwingWrapper
 * <li>Matlab Theme
 */
public class Graphs implements ExampleChart<XYChart>, RealtimeExampleChart {
	Double timer = (double) 0;
	List<Double> x;
	private XYChart xyChart;
	public String title;
	private List<Double> yData;

	public Graphs(String title) {
		this.title = title;
	}

	void go() {
		final SwingWrapper<XYChart> swingWrapper = new SwingWrapper<XYChart>(getChart());
		swingWrapper.displayChart();
		// Simulate a data feed
		TimerTask chartUpdaterTask = new TimerTask() {
			@Override
			public void run() {
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						swingWrapper.repaintChart();
					}
				});
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(chartUpdaterTask, 0, 100);
	}

	@Override
	public XYChart getChart() {
		yData = getRandomData(0);
		x = getRandomData(0);
		// Create Chart
		xyChart = new XYChartBuilder().width(500).height(400).theme(ChartTheme.GGPlot2).title(this.title).build();
		xyChart.addSeries(this.title, x, yData);
		return xyChart;
	}

	public void updateData(int uti) {
		// Get some new data
		List<Double> newData = getRandomData(uti);
		yData.addAll(newData);
		x.add(timer++);
		// Limit the total number of points
		xyChart.updateXYSeries(this.title, x, yData, null);
	}

	private List<Double> getRandomData(int uti) {
		List<Double> data = new CopyOnWriteArrayList<Double>();
		for (int i = 0; i < 1; i++) {
			data.add((double) uti);
		}
		return data;
	}

	@Override
	public void updateData() {
	}
}