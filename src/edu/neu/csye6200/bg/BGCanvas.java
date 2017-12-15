package edu.neu.csye6200.bg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import javax.swing.JPanel;

//observer

public class BGCanvas extends JPanel implements Observer{
	
	
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(BGCanvas.class.getName());
	
	private long counter = 0L;
	
	static ArrayList<BGGeneration> genList;
	static ArrayList<BGStem> stemList;
	
	public Graphics2D g2d;
				
	public void paint(Graphics g) {
		drawCanvas(g);
	}
	

	public void drawCanvas(Graphics g) {
		log.info("Drawing canvas" + counter);
		g2d = (Graphics2D) g;

		/**
		 * before set a rule to a generation, there is nothing send to this canvas, genList points to null, 
		 * so I use try-catch to jump NullPointerException before send a genList to this canvas
		 */
		try {
				stemList = genList.get(genList.size()-1).stemList;
				g2d.setColor(Color.BLACK);
				g2d.fillRect(0, 0, 1200, 1000);

			for(BGStem stem : stemList) {
				
				// rule 1
				if(WolfApp.getRule() == 1) {
					g2d.setColor(new Color((int)(Math.random()*10), (int)(Math.random()*100), (int)(Math.random()*200)));
				}
				
				// rule 2
				if(WolfApp.getRule() == 2) {
					if(stem.getAge() == 0) {
						g2d.setColor(new Color(200, 200, 200));
						g2d.setStroke(new BasicStroke(3));
						
					} else {
						g2d.setColor(new Color(20*stem.getAge(), 20*stem.getAge(), 20*stem.getAge()));
						g2d.setStroke(new BasicStroke(1));
						}
				}
				
				// rule 3
				if(WolfApp.getRule() == 3) {
					
					if(stem.getAge() < 5) {
						g2d.setStroke(new BasicStroke(1));
						if(stem.getAge() == 0) {
							g2d.setStroke(new BasicStroke(10));
						}
						g2d.setColor(new Color(255, 255, 0));
					} else 
						g2d.setColor(new Color(255, 0, 0));
					
				}
				//draw line
				g2d.drawLine(stem.getStart().x, stem.getStart().y, stem.setEnd().x, stem.setEnd().y);
			}
			
			counter++;
			
			} catch(NullPointerException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		genList = (ArrayList<BGGeneration>) arg; 
//		genList.get(genList.size()-1).printFullTree();	
	}

}
