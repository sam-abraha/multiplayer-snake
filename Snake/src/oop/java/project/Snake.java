package oop.java.project;



import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {
	
	private Rectangle head;
	private ArrayList <Rectangle> snake;
	private Direction direction;
	private int score;
	
	public Snake(int xPos,int yPos) {
		snake=new ArrayList<>();
		head=new Rectangle(xPos,yPos,Commons.getSize(),Commons.getSize());
		snake.add(head);
		setDirection(Direction.NOT_MOVING);
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

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	

	
	/**
	 * calls up,left,right,down function suitable to the direction and deletes last element in the list
	 */
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
		case NOT_MOVING:
			break;
		default:
			break;
		}
		snake.remove(snake.size()-1);
	}
	
	

	/**
	 *  adds a new rectangle to the end of the list suitable with the dircetion
	 */
	public void grow() {
		
		Rectangle last=snake.get(snake.size()-1); // last element in the array list
		
		switch(getDirection()) {
		
		case UP : 
			snake.add(new Rectangle(last.x,last.y-Commons.getSize(),Commons.getSize(),Commons.getSize()));
			break;
		case RIGHT : 
			snake.add(new Rectangle(last.x+Commons.getSize(),last.y,Commons.getSize(),Commons.getSize()));
			break;
		
		case LEFT: 
			snake.add(new Rectangle(last.x-Commons.getSize(),last.y,Commons.getSize(),Commons.getSize()));
			break;
			
		case DOWN : 
			snake.add(new Rectangle(last.x,last.y+Commons.getSize(),Commons.getSize(),Commons.getSize()));
			break;
		default:
			break;
		}
	}

	
	/**
	 * creates a new rectangle when the desired direction is right and adds it to the list
	 */
	public void right() {
		// TODO Auto-generated method stub
		setDirection(Direction.RIGHT);
		Rectangle top=snake.get(0);
		Rectangle temp=new Rectangle(top.x+Commons.getSize(),top.y,Commons.getSize(),Commons.getSize());
		snake.add(0,temp);
	}


	/**
	 * creates a new rectangle when the desired direction is left and adds it to the list
	 */
	public void left() {
		// TODO Auto-generated method stub
		setDirection(Direction.LEFT);
		Rectangle top=snake.get(0);
		Rectangle temp=new Rectangle(top.x-Commons.getSize(),top.y,Commons.getSize(),Commons.getSize());
		snake.add(0,temp);
	}


	/**
	 * creates a new rectangle when the desired direction is down and adds it to the list
	 */
	public void down() {
		// TODO Auto-generated method stub
		setDirection(Direction.DOWN);
		Rectangle top=snake.get(0);
		Rectangle temp=new Rectangle(top.x,top.y+Commons.getSize(),Commons.getSize(),Commons.getSize());
		snake.add(0,temp);
		
	}


	/**
	 * creates a new rectangle when the desired direction is up and adds it to the list
	 */
	public void up() {
		// TODO Auto-generated method stub
		setDirection(Direction.UP);
		Rectangle top=snake.get(0);
		Rectangle temp=new Rectangle(top.x,top.y-Commons.getSize(),Commons.getSize(),Commons.getSize());
		snake.add(0,temp);
		
	}
	
	
	}


