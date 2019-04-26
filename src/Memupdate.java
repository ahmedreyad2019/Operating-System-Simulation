import java.time.LocalTime;
import java.util.Queue;
import InputOutput.PrinterDevice;
import memory.Memory;
import process.Dispatcher;

public class Memupdate implements Runnable {
	int counter = 0;
	Memory m;
	TaskManager g;
	LocalTime avg;
	Graphs graph = new Graphs("Memory");
	Graphs graphCPU = new Graphs("CPU");

	public Memupdate(Memory m, TaskManager g) {
		this.m = m;
		this.g = g;
	}

	@SuppressWarnings("unchecked")
	public String toString(Queue<process.Process> q1) {
		Queue<process.Process> q2 = null;
		try {
			q2 = q1.getClass().newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		for (process.Process e : q1) {
			q2.add(e);
		}
		String q = "";
		while (!q2.isEmpty()) {
			q += q2.remove() + "\n";
		}
		return q;
	}

	@Override
	public void run() {
		graph.go();
		graphCPU.go();
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			g.getLblSss().setText(m.toString());
			g.textPane.setText(toString(PrinterDevice.s.getQueue()));
			int i = (100 - (Memory.getFreeSpace() * 100 / Memory.getSize()));
			g.textPane_3.setText(i + "%");
			graph.updateData(i);
			graphCPU.updateData(Dispatcher.getRunning_Queue().isEmpty() ? 0 : 100);
			if (i == 0) {
				g.textPane_8.setOpaque(false);
				g.textPane_7.setOpaque(false);
				g.textPane_6.setOpaque(false);
				g.textPane_5.setOpaque(false);
				g.textPane_4.setOpaque(false);
			}

			if (i <= 20 && i > 0) {
				g.textPane_8.setOpaque(true);
				g.textPane_7.setOpaque(false);
				g.textPane_6.setOpaque(false);
				g.textPane_5.setOpaque(false);
				g.textPane_4.setOpaque(false);
			}
			if (i <= 40 && i > 20) {
				g.textPane_8.setOpaque(true);
				g.textPane_7.setOpaque(true);
				g.textPane_6.setOpaque(false);
				g.textPane_5.setOpaque(false);
				g.textPane_4.setOpaque(false);
			}
			if (i <= 60 && i > 40) {
				g.textPane_8.setOpaque(true);
				g.textPane_7.setOpaque(true);
				g.textPane_6.setOpaque(true);
				g.textPane_5.setOpaque(false);
				g.textPane_4.setOpaque(false);
			}
			if (i <= 80 && i > 60) {
				g.textPane_8.setOpaque(true);
				g.textPane_7.setOpaque(true);
				g.textPane_6.setOpaque(true);
				g.textPane_5.setOpaque(true);
				g.textPane_4.setOpaque(false);
			}
			if (i < 100 && i > 80) {
				g.textPane_8.setOpaque(true);
				g.textPane_7.setOpaque(true);
				g.textPane_6.setOpaque(true);
				g.textPane_5.setOpaque(true);
				g.textPane_4.setOpaque(true);
			}
			g.lblNewLabel.setText(Dispatcher.avg + "s");
			g.textPane_1.setText(toString(Dispatcher.getReady_Queue()));
			g.textPane_2.setText(toString(Dispatcher.getRunning_Queue()));
			g.textPane_9.setText(toString(Dispatcher.getBlocked_Queue()));
			g.repaint();
		}
	}
}
