package oop.java.project;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Obstacle extends GameObj {

	private Rectangle obst;
	private ArrayList <Rectangle> obstacle;
	
	public Obstacle(int xPos,int yPos) {
		super(xPos,yPos);
		obstacle=new ArrayList<>();
		obst.x=this.getxPos();
		obst.y=this.getyPos();
		obstacle.add(obst);
	}
}
