package edu.neu.csye6200.bg;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WolfApp extends BGApp {

	private Logger log = Logger.getLogger(WolfApp.class.getName());
	
	private JPanel operationPanel;
	private JButton startBtn;
	private JButton stopBtn;
	private JButton continueBtn;
	private JButton endBtn;
	private JButton exitBtn;
	private JLabel cntLb;
	private JLabel ruleLb;
	private JLabel stLb;
	private JTextField cntTxt;
	private JComboBox<String> ruleBox;
	
	private BGCanvas canvas;
	private BGGenerationSet BGGSet;
	
	static boolean start;
	static boolean stop;
	static boolean cont1nue;
	static boolean end;
	static boolean exit;

	static int rule = 0;
	static int maxGen = 0;
	
	private Thread thread;

	public WolfApp() {
		log.info("App started");
		canvas = new BGCanvas();
		frame.add(canvas, BorderLayout.CENTER);
		showUI();
	}
			
	@Override
	protected JPanel getOperationPanel() {
		operationPanel = new JPanel();
		operationPanel.setLayout(new FlowLayout());

		startBtn = new JButton("start");
		stopBtn = new JButton("stop");
		continueBtn = new JButton("continue");
		endBtn = new JButton("end");
		exitBtn = new JButton("exit");
		cntLb = new JLabel("Gen count:");
		cntTxt = new JTextField("10");
		ruleLb = new JLabel("Rule:");
		stLb = new JLabel("The best count of generation is 10.");
		ruleBox = new JComboBox<String>();

		ruleBox.addItem("null");
		ruleBox.addItem("Rule 1");
		ruleBox.addItem("Rule 2");
		ruleBox.addItem("Rule 3");
		
		operationPanel.add(startBtn);
		operationPanel.add(stopBtn);
		operationPanel.add(continueBtn);
		operationPanel.add(endBtn);
		operationPanel.add(exitBtn);
		operationPanel.add(cntLb);
		operationPanel.add(cntTxt);
		operationPanel.add(ruleLb);
		operationPanel.add(ruleBox);
		operationPanel.add(stLb);

		startBtn.addActionListener(this);
		stopBtn.addActionListener(this);
		continueBtn.addActionListener(this);
		endBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		cntTxt.addActionListener(this);
		ruleBox.addActionListener(this);
		
		// filter invalid key of Generation count field
		cntTxt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				if(key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9) {
					
				} else {
					e.consume(); 
				}
			}
		});
		
		return operationPanel;
	}
	
	
	@Override
	public void windowOpened(WindowEvent e) {
		log.info("Window opened");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		log.info("Window closing");	
	}

	@Override
	public void windowClosed(WindowEvent e) {
		log.info("Window closed");		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		log.info("Window iconified");		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		log.info("Window deiconified");		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		log.info("Window activated");		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		log.info("Window deactivated");		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("Action performed" + e);
		
		// create a Runnable reference
		BGGSet = new BGGenerationSet();
		thread = new Thread(BGGSet);

		if(e.getSource() == startBtn) {
			System.out.println("start pressed");
			
			maxGen = Integer.parseInt(cntTxt.getText());

			// before running, valued the input
			if ((maxGen > 10)) {
				JOptionPane.showMessageDialog(null, "Enter a number less than 10.",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
			if(rule == 0) {
				JOptionPane.showMessageDialog(null, "Select a rule before running.",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}

			start = true;
			end = false;
			thread.start();
		} 
		
		if(e.getSource() == stopBtn) {
			System.out.println("stop pressed");
			stop = true;
			cont1nue = false;

		} 
		
		if(e.getSource() == continueBtn) {
			System.out.println("continue pressed");
			stop = false;
			cont1nue = true;

		} 

		if(e.getSource() == endBtn) {
			System.out.println("end pressed");
			end = true;

		} 

		if(e.getSource() == exitBtn) {
			System.out.println("exit pressed");
			exit();
		}
		
		// Select a generation rule 
		if(e.getSource() == ruleBox) {
			int rulex = ruleBox.getSelectedIndex();
			switch(rulex) {
			case 0: 
				rule = 0;
				log.info("No rule was selected");
				break;
			case 1: 
				rule = 1;
				log.info("Rule 1 was selected");
				break;
			case 2: 
				rule = 2;
				log.info("Rule 2 was selected");
				break;
			case 3: 
				rule = 3;
				log.info("Rule 3 was selected");
				break;
			}
		}
	}
	
	public static boolean isStart() {
		return start;
	}

	public static void setStart(boolean start) {
		WolfApp.start = start;
	}

	public static boolean isStop() {
		return stop;
	}

	public static void setStop(boolean stop) {
		WolfApp.stop = stop;
	}

	public static boolean isCont1nue() {
		return cont1nue;
	}

	public static void setCont1nue(boolean cont1nue) {
		WolfApp.cont1nue = cont1nue;
	}

	public static boolean isEnd() {
		return end;
	}

	public static void setEnd(boolean end) {
		WolfApp.end = end;
	}

	public static boolean isExit() {
		return exit;
	}

	public static void setExit(boolean exit) {
		WolfApp.exit = exit;
	}

	public static int getRule() {
		return rule;
	}

	public static void setRule(int rule) {
		WolfApp.rule = rule;
	}

	public static int getMaxGen() {
		return maxGen;
	}

	public static void setMaxGen(int maxGen) {
		WolfApp.maxGen = maxGen;
	}

	// get this frame
	public static JFrame getFrame() {
		return frame;
	}


	public static void main(String[] args) {
		WolfApp wa = new WolfApp();
	}


}
