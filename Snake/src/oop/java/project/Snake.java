package oop.java.project;



import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake  extends GameObj{
	
	private Rectangle rect1;
	private ArrayList <Rectangle> snake;
	private Direction direction;
	private int score;
	
	public Snake(int x,int y) {
		super(x,y);
		snake=new ArrayList<>();
		rect1=new Rectangle(x,y,Commons.getSize(),Commons.getSize());
		snake.add(rect1);
		setDirection(null);
		setScore(0);
	}

	/**
	 * @return snake 
	 */
	
	public ArrayList<Rectangle> getSnake() {
		return snake;
	}


	/**
	 * @param snake
	 */
	public void setSnake(ArrayList<Rectangle> snake) {
		this.snake = snake;
	}
	
	public int size() {
		
		return snake.size();
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
	public void removeLast() {
		snake.remove(size()-1);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void move() {
		
		switch(getDirection()) {
		
		case UP : 
			this.up();
			break;
		case RIGHT : 
			this.right();;
			break;
		
		case LEFT: 
			this.left();
			break;
			
		case DOWN : 
			this.down();
			break;
		}
		this.removeLast();
	}

	public void grow() {
		
		switch(getDirection()) {
		
		case UP : 
			this.up();
			break;
		case RIGHT : 
			this.right();;
			break;
		
		case LEFT: 
			this.left();
			break;
			
		case DOWN : 
			this.down();
			break;
		}
	}


	public void right() {
		// TODO Auto-generated method stub
		setDirection(Direction.RIGHT);
		Rectangle top=snake.get(0);
		Rectangle temp=new Rectangle(top.x+Commons.getSize(),top.y,Commons.getSize(),Commons.getSize());
		snake.add(0,temp);
	}


	public void left() {
		// TODO Auto-generated method stub
		setDirection(Direction.LEFT);
		Rectangle top=snake.get(0);
		Rectangle temp=new Rectangle(top.x-Commons.getSize(),top.y,Commons.getSize(),Commons.getSize());
		snake.add(0,temp);
	}


	public void down() {
		// TODO Auto-generated method stub
		setDirection(Direction.DOWN);
		Rectangle top=snake.get(0);
		Rectangle temp=new Rectangle(top.x,top.y+Commons.getSize(),Commons.getSize(),Commons.getSize());
		snake.add(0,temp);
		
	}


	public void up() {
		// TODO Auto-generated method stub
		setDirection(Direction.UP);
		Rectangle top=snake.get(0);
		Rectangle temp=new Rectangle(top.x,top.y-Commons.getSize(),Commons.getSize(),Commons.getSize());
		snake.add(0,temp);
		
	}
	
	
	}


