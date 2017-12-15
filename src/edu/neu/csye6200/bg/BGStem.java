package edu.neu.csye6200.bg;

import java.awt.Point;

public class BGStem {
	
	//create Point to represent start and end points of a stem  
	protected Point start = new Point(0, 0);
	protected Point end = new Point(0, 0);
	
	protected double length = 100.0;
	protected double angle = 0;
	protected double parentAngle = 0;
	protected double totalAngle = 0;
		
	protected int age = 0;
	protected int curIndex = -1;
	protected int parentIndex = -1;
	
	protected int[] children = new int[10];
	protected int childCnt = 0;

	/**
	 * constructor of BGStem
	 * @param start is start point
	 * @param length is stem's length
	 * @param angle  is stem's angle
	 */
	public BGStem(Point start, double length, double angle) {
		this.start = start;
		this.length = length;
		this.angle = angle;
	}
	
	/**
	 * copy a stem
	 * @param stem
	 */
	public BGStem(BGStem stem) {
		this.start = stem.start;
		this.end = stem.end;
		this.length = stem.length;
		this.angle = stem.angle;
		this.parentAngle = stem.parentAngle;
		this.totalAngle = stem.totalAngle;
		this.age = stem.age;
		this.curIndex = stem.curIndex;
		this.parentIndex = stem.parentIndex;
		this.childCnt = stem.childCnt;
		for (int i = 0; i < children.length; i++) {
			children[i] = stem.children[i];
		}
	}

	public boolean hasChild() {
		return (childCnt > 0);
	}
	
	public BGStem copy() {	 
		return new BGStem(this);
	}
	
	public void add(BGStem stem) {
		children[childCnt] = stem.curIndex;
		childCnt++;
	}
	
	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}
	
	/**
	 * calculate the end point of a stem 
	 * @return end point
	 */
	public Point setEnd() {
		double localAngle = (parentAngle + angle) * Math.PI / 180;
		int x = (int)(start.x + length * Math.cos(localAngle));
		int y = (int)(start.y - length * Math.sin(localAngle));
		setEnd(end);
		return end = new Point(x, y);
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getParentAngle() {
		return parentAngle;
	}

	public void setParentAngle(double parentAngle) {
		this.parentAngle = parentAngle;
	}

	public double getTotalAngle() {
		return parentAngle + angle;
	}

	public void setTotalAngle(double totalAngle) {
		this.totalAngle = totalAngle;
	}

	public int getCurIndex() {
		return curIndex;
	}

	public void setCurIndex(int curIndex) {
		this.curIndex = curIndex;
	}

	public int getParentIndex() {
		return parentIndex;
	}

	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}

	public int[] getChildren() {
		return children;
	}

	public void setChildren(int[] children) {
		this.children = children;
	}

	public int getChildCnt() {
		return childCnt;
	}

	public void setChildCnt(int childCnt) {
		this.childCnt = childCnt;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * print stems by string
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < age; i++)
			sb.append("-+");
		sb.append("BGStem[");
		sb.append("index="+curIndex);
		sb.append(",startPos="+ "(" + start.x + ", " + start.y + ")");
		sb.append(",length="+length);
		sb.append(",angle="+angle);
		sb.append(",gAngle="+getTotalAngle());
		sb.append(",child="+childCnt);
		sb.append("]");
		return sb.toString();
	}

}
