package oop.java.project;


import java.util.Random;

public class Apple extends GameObj {
	
	
	public Apple(int x, int y)
	{
		super(x,y);
	}

	
	/**
	 * sets apple to a random positon
	 */
	public void setRandomPosition() {
		Random x= new Random();
		int xPos=x.nextInt(Commons.getHeight());   // apple needs to be a multiple of the cell size in order to fit
		this.setxPos((xPos/Commons.getSize())*Commons.getSize());
		
		
		Random y=new Random();
		int yPos=y.nextInt(Commons.getHeight());
		this.setyPos((yPos/Commons.getSize())*Commons.getSize());
		
	}
	

}
