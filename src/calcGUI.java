import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import applications.Calculator;

public class calcGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtInput;
	private JTextField operation;
	private JTextField txtInput_2;
	Calculator calcc;
	boolean set = false;

	public calcGUI() {

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(300, 500));
		JButton btnCalculate = new JButton("Calculate");
		panel.add(btnCalculate, BorderLayout.NORTH);
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		txtInput = new JTextField();
		txtInput.setPreferredSize(new Dimension(50, 50));
		panel_1.add(txtInput);
		operation = new JTextField();
		panel_1.add(operation);
		operation.setPreferredSize(new Dimension(50, 50));
		txtInput_2 = new JTextField();
		panel_1.add(txtInput_2);
		txtInput_2.setPreferredSize(new Dimension(50, 50));
		JLabel output = new JLabel();
		panel_1.add(output);
		btnCalculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtInput.getText().length() != 0 && operation.getText().length() != 0
						&& txtInput_2.getText().length() != 0) {
					int a = Integer.parseInt(txtInput.getText());
					String op = operation.getText();
					int b = Integer.parseInt(txtInput_2.getText());
					calcc = new Calculator(a, op, b);
					set = true;
					repaint();
					validate();
				}
			}
		});
		pack();
		setVisible(true);
	}
}