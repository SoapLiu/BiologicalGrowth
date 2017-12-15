package edu.neu.csye6200.bg;

import java.awt.Point;
import java.util.ArrayList;

public class BGGeneration {

	private BGRule rule = null;
	private BGStem root = null;
	protected ArrayList<BGStem> stemList = new ArrayList<BGStem>();
		
	/**
	 * Constructor
	 * @param growFactor
	 * @param children
	 * @param length
	 * @param interAngle
	 */
	public BGGeneration(double growFactor, int children, int length, int interAngle) {
		rule = new BGRule(growFactor, children, length, interAngle);
		Point rootStart = new Point(600, 800);
		double rootLength = 50;
		int angle = 90;
		root = add(new BGStem(rootStart, rootLength, angle));
	}
	
	//copy a generation
	public BGGeneration(BGGeneration generation) {
		rule = generation.rule;
		for(BGStem stem : generation.stemList) {
			stem.curIndex = stemList.size();
			add(stem.copy());
		}
		
		root = stemList.get(0);
	}
	
	public ArrayList<BGStem> getList() {
		return stemList;
	}

	public BGStem add(BGStem stem) {
		stem.curIndex = stemList.size();
		stemList.add(stem);
		return stem;
	}
	
	public BGGeneration createNextGen() {
		 return rule.getNextGen(this);
	}
	
	//print a tree by string
	public void printFullTree() {
		
		for (BGStem stem : stemList) {
		     System.out.println(stem);
		}
	}

	
}
