import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import memory.Memory;

public class TaskManager extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextPane lblSss;
	public JTextPane textPane;
	public JTextPane textPane_1;
	public JTextPane textPane_2;
	public JTextPane textPane_3;
	public JTextPane lblNewLabel;
	private JLabel lblMemoryUtilization;
	private JLabel lblAverageTimeOf;
	public JTextPane textPane_8;
	public JTextPane textPane_7;
	public JTextPane textPane_6;
	public JTextPane textPane_5;
	public JTextPane textPane_4;
	private JTabbedPane tabbedPane;
	private JPanel panel_2;
	public JTextPane textPane_9;

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextPane getLblSss() {
		return lblSss;
	}

	public void setLblSss(JTextPane lblSss) {
		this.lblSss = lblSss;
	}

	public TaskManager(Memory m) {
		Memupdate mem = new Memupdate(m, this);
		Thread b = new Thread(mem);
		b.start();
		setTitle("Task Manager");
		setBounds(new Rectangle(50, 50, 650, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.NORTH);
		panel = new JPanel();
		tabbedPane.addTab("Memory", null, panel, null);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 800));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(289, 13, 300, 601);
		panel.add(scrollPane);
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		lblSss = new JTextPane();
		lblSss.setEditable(false);
		panel_1.add(lblSss);
		lblSss.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblSss.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel = new JTextPane();
		lblNewLabel.setToolTipText("Semaphore");
		lblNewLabel.setEditable(false);
		lblNewLabel.setBounds(23, 42, 156, 22);
		panel.add(lblNewLabel);
		lblAverageTimeOf = new JLabel("Avg waiting time");
		lblAverageTimeOf.setBounds(23, 13, 156, 16);
		panel.add(lblAverageTimeOf);
		lblMemoryUtilization = new JLabel("Memory Utilization");
		lblMemoryUtilization.setBounds(23, 94, 156, 16);
		panel.add(lblMemoryUtilization);
		textPane_3 = new JTextPane();
		textPane_3.setToolTipText("Semaphore");
		textPane_3.setEditable(false);
		textPane_3.setBounds(23, 133, 68, 22);
		panel.add(textPane_3);
		textPane_4 = new JTextPane();
		textPane_4.setBackground(Color.RED);
		textPane_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPane_4.setToolTipText("danger 80-100");
		textPane_4.setEditable(false);
		textPane_4.setBounds(154, 94, 68, 22);
		panel.add(textPane_4);
		textPane_5 = new JTextPane();
		textPane_5.setBackground(Color.ORANGE);
		textPane_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPane_5.setToolTipText("alert 60-80");
		textPane_5.setEditable(false);
		textPane_5.setBounds(154, 115, 68, 22);
		panel.add(textPane_5);
		textPane_6 = new JTextPane();
		textPane_6.setBackground(Color.YELLOW);
		textPane_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPane_6.setToolTipText("moderate 40-60");
		textPane_6.setEditable(false);
		textPane_6.setBounds(154, 136, 68, 22);
		panel.add(textPane_6);
		textPane_7 = new JTextPane();
		textPane_7.setBackground(new Color(204, 255, 0));
		textPane_7.setToolTipText("good 20-40");
		textPane_7.setEditable(false);
		textPane_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPane_7.setBounds(154, 157, 68, 22);
		panel.add(textPane_7);
		textPane_8 = new JTextPane();
		textPane_8.setBackground(Color.GREEN);
		textPane_8.setToolTipText("Safe 0-20");
		textPane_8.setEditable(false);
		textPane_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPane_8.setBounds(154, 178, 68, 22);
		panel.add(textPane_8);
		panel_2 = new JPanel();
		tabbedPane.addTab("Queues", null, panel_2, null);
		panel_2.setLayout(null);
		textPane = new JTextPane();
		textPane.setBounds(496, 42, 119, 444);
		panel_2.add(textPane);
		textPane.setEditable(false);
		textPane_1 = new JTextPane();
		textPane_1.setBounds(161, 43, 152, 444);
		panel_2.add(textPane_1);
		textPane_1.setEditable(false);
		textPane_2 = new JTextPane();
		textPane_2.setBounds(12, 42, 137, 444);
		panel_2.add(textPane_2);
		textPane_2.setEditable(false);
		JLabel lblPrinterQueue = new JLabel("Printer Queue");
		lblPrinterQueue.setBounds(518, 13, 97, 16);
		panel_2.add(lblPrinterQueue);
		JLabel lblReadyQueue = new JLabel("Ready Queue");
		lblReadyQueue.setBounds(171, 13, 142, 16);
		panel_2.add(lblReadyQueue);
		JLabel lblRunningQueue = new JLabel("Running Queue");
		lblRunningQueue.setBounds(26, 13, 97, 16);
		panel_2.add(lblRunningQueue);
		textPane_9 = new JTextPane();
		textPane_9.setEditable(false);
		textPane_9.setBounds(332, 42, 152, 444);
		panel_2.add(textPane_9);
		JLabel lblBlockedQueue = new JLabel("Blocked Queue");
		lblBlockedQueue.setBounds(342, 12, 142, 16);
		panel_2.add(lblBlockedQueue);
		setVisible(true);
	}
}
