package edu.neu.csye6200.bg;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public abstract class BGApp implements ActionListener, WindowListener {
	
	protected static JFrame frame;
	
	public BGApp() {
		initGUI();
	}
	
	public void initGUI() {
		frame = new JFrame();
		frame.setSize(1200, 900);
		frame.setResizable(true);
		frame.setTitle("Biological Growth Tree");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(this);
		frame.setLayout(new BorderLayout());
		frame.add(getOperationPanel(), BorderLayout.NORTH);
	}
	
	protected abstract JPanel getOperationPanel();

	public void showUI() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				frame.setVisible(true);
				
			}
			
		});
	}

	public void exit() {
		frame.dispose();
		System.exit(0);
	}
	
}

