package edu.neu.csye6200.bg;

import java.util.ArrayList;
import java.util.Observable;
import javax.swing.SwingUtilities;

//observable, runnable
public class BGGenerationSet extends Observable implements Runnable {

	private BGCanvas obscanvas;	
	private int count = 0;

	private ArrayList<BGGeneration> genList = new ArrayList<BGGeneration>();
	private static BGGeneration lastGen = null;
		
	/**
	 * Constructor
	 */
	public BGGenerationSet() {
		initGen();
	}
	
	/**
	 * add a observer 
	 */
	public void initGen() {
		obscanvas = new BGCanvas();
		addObserver(obscanvas);
	}
			
	@Override
	public void run() {

		//create a new generation based on rule you select
		if(WolfApp.getRule() == 1) {
			BGGeneration gen1 = new BGGeneration(1.2, 3, 20, 20);
			lastGen = gen1;
			genList.add(lastGen);
			
			//send an ArrayList to observer - BGCanvas
			setChanged();
			notifyObservers(genList);
			
			//slow down the generation speed
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//refresh the display
			obscanvas.repaint();
			SwingUtilities.updateComponentTreeUI(WolfApp.getFrame());
		} else if(WolfApp.getRule() == 2) {
			BGGeneration gen2 = new BGGeneration(1.1, 2, 20, 33);
			lastGen = gen2;
			genList.add(lastGen);
			
			//send an ArrayList to observer - BGCanvas
			setChanged();
			notifyObservers(genList);
			
			//slow down the generation speed
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//refresh the display
			obscanvas.repaint();
			SwingUtilities.updateComponentTreeUI(WolfApp.getFrame());
		} else if(WolfApp.getRule() == 3){
			BGGeneration gen3 = new BGGeneration(1.2, 3, 15, 60);
			lastGen = gen3;
			genList.add(lastGen);
			
			//send an ArrayList to observer - BGCanvas
			setChanged();
			notifyObservers(genList);
			
			//slow down the generation speed
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//refresh the display
			obscanvas.repaint();
			SwingUtilities.updateComponentTreeUI(WolfApp.getFrame());	
		}
		
		while((count < WolfApp.getMaxGen()) && (!WolfApp.isEnd())) {

			if(WolfApp.isStop()) {
				if(!WolfApp.isCont1nue()) {
					System.out.println("stop");
					continue;
				}
			}
			
			BGGeneration nxtGen = lastGen.createNextGen();
		    genList.add(nxtGen);
		    lastGen = nxtGen;
		    count++;
		    
			//send an ArrayList to observer - BGCanvas
			setChanged();
			notifyObservers(genList);
			
			//slow down the generation speed
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//refresh the display
			obscanvas.repaint();
			SwingUtilities.updateComponentTreeUI(WolfApp.getFrame());					
		}
		
	}
	
}
