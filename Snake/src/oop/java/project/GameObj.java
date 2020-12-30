package oop.java.project;

public class GameObj {
	
	private int xPos;
	private int yPos;
	
	public GameObj() {
		xPos=yPos=0;
	}
	
	public GameObj(int xPos,int yPos) {
		this.xPos=xPos;
		this.yPos=yPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
}
