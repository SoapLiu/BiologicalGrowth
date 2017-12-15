package edu.neu.csye6200.bg;

public class BGRule {

	private double growFactor = 0;
	private int children = 0;
	private double length = 0;
	private double interAngle = 0;
	
	/**
	 * BGRule constructor
	 * @param growFactor is growing up speed of a stem
	 * @param children is amount of parent's child stems
	 * @param length is the length of stem
	 * @param interAngle is the angle of each child stem
	 */
	public BGRule(double growFactor, int children, double length, int interAngle) {
		this.growFactor = growFactor;
		this.children = children;
		this.length = length;
		this.interAngle = interAngle;
	}
	
	/**
	 * calculate the stem's length and their end points;
	 * @param nextGen
	 */
	public void growStems(BGGeneration nextGen) {
		for (BGStem stem : nextGen.stemList) {
			stem.length *= growFactor;
			if(stem.parentIndex > -1) {
				stem.setStart(nextGen.stemList.get(stem.parentIndex).setEnd());
			}
		}
	}

	/**find the tip of stems and add stems to them
	 * @param nextGen
	 */
	public void addStems(BGGeneration nextGen) {
		int nextSize = nextGen.stemList.size();
		for(int i = 0; i < nextSize; i++) {
			BGStem stem = nextGen.stemList.get(i);
			if(stem.hasChild()) continue;
			addChildStems(nextGen, stem);
		}
	}
	
	/**
	 * the method of add child stems to parent stems
	 * @param nextGen
	 * @param parentStem
	 */
	public void addChildStems(BGGeneration nextGen, BGStem parentStem) {
		double totalAngle = interAngle * (children - 1);
		double startAngle = - totalAngle / 2;
		
		for(int i = 0; i < children; i++) {
			double angle = startAngle + i * interAngle;
			BGStem childStem = new BGStem(parentStem.setEnd(), length, angle);

			childStem.setParentIndex(parentStem.getCurIndex());
			childStem.setParentAngle(parentStem.getTotalAngle());
			childStem.setAge(parentStem.getAge() + 1);
			
			parentStem.add(childStem);
			nextGen.add(childStem);
		}
		
	}
	
	/**
	 * create next generation based on parents generation
	 * @param gen
	 * @return get next generation
	 */
	public BGGeneration getNextGen(BGGeneration gen) {
		BGGeneration nextGen = new BGGeneration(gen);
		growStems(nextGen);
		addStems(nextGen);
		return nextGen;
	}

}
