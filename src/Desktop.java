import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import applications.*;
import memory.Memory;

class Desktop extends JFrame {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	JPanel p;
	JMenuBar mb;
	JButton close, min, max;
	Font f = new Font("Arial", Font.BOLD, 24);
	int pX, pY;
	JButton button;
	private JDesktopPane desktopPane;
	private JButton btnTaskManager;
	private JButton cal;
	private JButton bright;
	private BufferedImage img;

	public Desktop(Memory m) {
		button = new JButton("New button");
		createAndShowGUI(m);
	}

	private void createAndShowGUI(Memory m) {
		// Custom look and feel
		setTitle("Custom Titlebar");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		// Create JMenuBar
		mb = new JMenuBar();
		mb.setLayout(new BorderLayout());
		// Create panel
		p = new JPanel();
		p.setOpaque(false);
		p.setLayout(new GridLayout(1, 3));
		// Create buttons
		min = new JButton("-");
		min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// minimize
				setState(ICONIFIED);
			}
		});
		// set focus painted false
		// i don't like it, so i removed it
		// if you like, you can remove these steps
		min.setFocusPainted(false);
		// font, again if you don't like you can
		// remove these steps, also remove the Font object
		min.setFont(f);
		p.add(min);
		// To west, mac style!
		mb.add(p, BorderLayout.EAST);
		close = new JButton("x");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// terminate program
				System.exit(0);
			}
		});
		max = new JButton("+");
		max.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				maximize();
			}
		});
		max.setFocusPainted(false);
		max.setFont(f);
		p.add(max);
		close.setFocusPainted(false);
		close.setFont(f);
		// Add buttons
		p.add(close);
		// Add mouse listener for JMenuBar mb
		mb.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				// Get x,y and store them
				pX = me.getX();
				pY = me.getY();
			}
		});
		// Add MouseMotionListener for detecting drag
		mb.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent me) {
				// Set the location
				// get the current location x-co-ordinate and then get
				// the current drag x co-ordinate, add them and subtract most recent
				// mouse pressed x co-ordinate
				// do same for y co-ordinate
				setLocation(getLocation().x + me.getX() - pX, getLocation().y + me.getY() - pY);
			}
		});
		// Set the menu bar
		setJMenuBar(mb);
		this.pack();
		this.setVisible(true);
		// Set size, visibility,shape and center it
		setSize(1000, 1000);
		setVisible(true);
		setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 5, 5));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		try {
			img = ImageIO.read(new File("src/bg1png.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		desktopPane = new JDesktopPane() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7347178348888362416L;

			@Override
			protected void paintComponent(Graphics grphcs) {
				super.paintComponent(grphcs);
				grphcs.drawImage(img, 0, 0, null);
			}

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(img.getWidth(), img.getHeight());
			}
		};
		desktopPane.setFont(new Font("Calibri", Font.PLAIN, 12));
		desktopPane.setSize(new Dimension(1000, 1000));
		desktopPane.setLayout(null);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		JButton btnCalendar = new JButton("Calendar");
		btnCalendar.setBounds(new Rectangle(200, 200, 0, 0));
		btnCalendar.setBackground(Color.WHITE);
		btnCalendar.setBounds(30, 14, 91, 40);
		btnCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CalendarProgram();
			}
		});
		desktopPane.add(btnCalendar);
		cal = new JButton("Cal");
		cal.setBounds(new Rectangle(200, 200, 0, 0));
		cal.setBackground(Color.WHITE);
		cal.setBounds(200, 14, 91, 40);
		cal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new calcGUI();
			}
		});
		desktopPane.add(cal);
		bright = new JButton("bright");
		bright.setBounds(new Rectangle(200, 200, 0, 0));
		bright.setBackground(Color.WHITE);
		bright.setBounds(600, 14, 91, 40);
		bright.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new bright();
			}
		});
		desktopPane.add(bright);
		// A specialized layered pane to be used with JInternalFrames
		btnTaskManager = new JButton("task manager");
		btnTaskManager.setBounds(new Rectangle(200, 200, 0, 0));
		btnTaskManager.setBackground(Color.WHITE);
		btnTaskManager.setBounds(400, 14, 91, 40);
		btnTaskManager.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TaskManager(m);
			}
		});
		desktopPane.add(btnTaskManager);
		repaint();
		revalidate();
	}

	private void maximize() {
		// Get GraphicsEnvironment object for getting GraphicsDevice object
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		// Get the screen devices
		GraphicsDevice[] g = env.getScreenDevices();
		// I only have one, the first one
		// If current window is full screen, set fullscreen window to null
		// else set the current screen
		g[0].setFullScreenWindow(g[0].getFullScreenWindow() == this ? null : this);
	}
}