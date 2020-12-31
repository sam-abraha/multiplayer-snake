package oop.java.project;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;

public class Panel extends JPanel implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;

	private Snake snake;
	private Snake snake_2;
	private Apple apple;
	
	private JButton sp_button;
	private JButton mp_button;
	
	private static final Color NEON_GREEN = new Color(102,255,102);
	private static final Color PURPLE = new Color(106,13,173);
	
	private boolean isRunning;
	private boolean menu;
	private boolean start;
	private int GameState;
	private boolean gameOver;

	private Timer timer;

	
	public Panel() {
		
		timer=new Timer(75,this);                         // due to the timer images will be repainted every 5 mi.seconds
		timer.start();
		snake=new Snake(Commons.getWidth()/2,(Commons.getHeight()/2)-Commons.getSize()*12);
		snake_2=(new Snake(Commons.getWidth()/2,(Commons.getHeight()/2)+Commons.getSize()*12));
		apple=new Apple(Commons.getWidth()/2,Commons.getHeight()/2);
		
		sp_button=new JButton();
		sp_button.setVisible(false);
		
		mp_button=new JButton();
		mp_button.setVisible(false);
		
		this.addKeyListener(new KeyListenerObj(this));  //  panel is now able to visualize our key event
		this.setFocusable(true);                       // key events will only be dispatched to components with focus
		this.setSize(WIDTH,HEIGHT);
		setRunning(false);
		setGameOver(false);
		setMenu(false);
		setStart(true);

	}
	
	
	public Snake getSnake() {
		// TODO Auto-generated method stub
		return snake;
	}
	
	public void setGameState(int x ) {
		
		if(x==1 || x==2)
			GameState=x;
	}
	
	public int getGameState() {
		return GameState;
	}
	
	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}
	
	public boolean isStart() {
		return start;
	}


	public void setStart(boolean start) {
		this.start = start;
	}

	public Snake getSnake_2() {
		return snake_2;
	}

	public void setSnake_2(Snake snake_2) {
		this.snake_2 = snake_2;
	}
	
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}


	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		gameplay();
		check_boundaries();
		
	}
	
	private JButton spButton() {
		
		sp_button.setText("SINGLEPLAYER");
		sp_button.setLocation(Commons.getWidth()/2-9*Commons.getSize(), (Commons.getHeight()/2)+Commons.getSize()*6);
		sp_button.setBackground(Color.WHITE);
		sp_button.setVisible(true);
		sp_button.addActionListener(new ActionListener() { // ActionListener object
			public void actionPerformed(ActionEvent e) { // Action when button pressed
			setGameState(1);
			sp_button.setVisible(false);
			setRunning(true);
			}
		});
			return sp_button; // returns button
		}
	
	private JButton mpButton() {
		
		mp_button.setText("MULTIPLAYER");
		mp_button.setLocation((Commons.getWidth()/2)+3*Commons.getSize(), (Commons.getHeight()/2)+Commons.getSize()*6);
		mp_button.setBackground(Color.WHITE);
		mp_button.setVisible(true);
		mp_button.addActionListener(new ActionListener() { // ActionListener object
			public void actionPerformed(ActionEvent e) { // Action when button pressed
			setGameState(2);
			setMenu(false);
			setRunning(true);
			}
		});
			return mp_button; // returns button
		}
	
	
	
	/**
	 *  paint our images onto the panel
	 */
	
	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		
		if(!isGameOver()) {
			if(isStart()) {
				this.setBackground(Color.BLACK);
				g.setFont(new Font("TimesRoman",Font.BOLD, 30)); 
				g.setColor(NEON_GREEN);
				g.drawString(" S N A K E ", (Commons.getWidth()/2)-4*Commons.getSize(), (Commons.getHeight()/2)-6*Commons.getSize());
				g.setFont(new Font("TimesRoman",Font.BOLD, 20));
			//	g.drawString("Made by ...",(Commons.getWidth()/2)-Commons.getSize()*6,(Commons.getHeight()/2)-3*Commons.getSize());	
				g.setColor(Color.WHITE);
				g.setFont(new Font("TimesRoman",Font.BOLD, 30));
				g.drawString("PRESS SPACE TO START",(Commons.getWidth()/2)-9*Commons.getSize(), (Commons.getHeight()/2)+Commons.getSize()*4);
				
					
			}
			
			if(isMenu()) {
				
				/**
				 * enable singleplayer and multiplayer in a menu
				 */
				this.add(sp_button);
				this.add(spButton());
				
				this.add(mp_button);
				this.add(mpButton());
				
			}
				
	        if(isRunning()) {
	        	
	        	/**
	        	 * set prior game stages false 
	        	 */
	        	
	        	this.setStart(false);
	        	this.setMenu(false);
	        	
	        	/**
	        	 *  set buttons unvisible
	        	 */
	        	
	        	sp_button.setVisible(false);
	        	mp_button.setVisible(false);
	        	
				/**
				 * score is visible in the top left corner
				 */
	        	
	        	
				g.setColor(Color.black);
				g.setFont(new Font("TimesRoman",Font.BOLD | Font.ITALIC,15));
				g.drawString("SCORE :  " + snake.getScore(),25,15);
				
				
				Graphics2D g2d=(Graphics2D)g;
				
				/**
				 *  set 2 different background colors depending on mode
				 */
				
				if(getGameState()==1) {
					setBackground(NEON_GREEN);
				}
				else
				{
					setBackground(PURPLE);
				}
				
				
				/**
				 *  a grid-like field will be created
				 */
				
				g.setColor(Color.white);
				for(int i=0;i<=(Commons.getWidth()/Commons.getSize());i++) {
					g.drawLine(0, i*Commons.getSize(), Commons.getWidth(), i*Commons.getSize());
				}
				
				for(int i=0;i<=(Commons.getHeight()/Commons.getSize());i++) {
					g.drawLine(i*Commons.getSize(), 0, i*Commons.getSize(), Commons.getHeight());
				}
				
				
				/**
				 *  apple will be painted red
				 */
				
				g.setColor(Color.red.brighter());
				g.fillRect(apple.getxPos(),apple.getyPos(),Commons.getSize(),Commons.getSize());
				
				
				/**
				 *  draw the second player and his score board
				 */
				
				g2d.setColor(Color.BLACK);
				for(Rectangle rect : snake.getSnake())
					g2d.fill(rect);
				
				if(getGameState()==2) {
					g2d.setColor(Color.WHITE);
					for(Rectangle rect : snake_2.getSnake())
						g2d.fill(rect);
					
					g.setFont(new Font("TimesRoman",Font.BOLD | Font.ITALIC,15));
					g.drawString("SCORE :  " + snake_2.getScore(),Commons.getWidth()-Commons.getSize()*5,15);
					
				}
        }
	}
		else
		{
			
		}

	
	}
	
	
	public void check_boundaries() {
		
		if(snake.getSnake().get(0).x < 0) {
			snake.getSnake().get(0).x=Commons.getWidth()-Commons.getSize();
		}
		
		if(snake.getSnake().get(0).x > Commons.getWidth()-Commons.getSize()) {
			snake.getSnake().get(0).x=0;
		}
		
		if(snake.getSnake().get(0).y < 0) {
			snake.getSnake().get(0).y=Commons.getHeight()-Commons.getSize();
		}
		
		if(snake.getSnake().get(0).y > Commons.getWidth()-Commons.getSize()) {
			snake.getSnake().get(0).y=0;
		}
		
		if(snake_2.getSnake().get(0).x < 0) {
			snake_2.getSnake().get(0).x=Commons.getWidth()-Commons.getSize();
		}
		
		if(snake_2.getSnake().get(0).x > Commons.getWidth()-Commons.getSize()) {
			snake_2.getSnake().get(0).x=0;
		}
		
		if(snake_2.getSnake().get(0).y < 0) {
			snake_2.getSnake().get(0).y=Commons.getHeight()-Commons.getSize();
		}
		
		if(snake_2.getSnake().get(0).y > Commons.getWidth()-Commons.getSize()) {
			snake_2.getSnake().get(0).y=0;
		}
	
	}

					

	
	
	public void gameplay() {
		if(isEaten()) {
	     	apple.setRandomPosition();
		}
		
		if(snake.getDirection()!=null) {
			snake.move();
		}
		
		if(snake_2.getDirection()!=null) {
			snake_2.move();
		}
	}
	
	
	/**
	 * @return true if snake's position euqals the apple's position
	 */
	public boolean isEaten() {
		
		for(Rectangle rect : snake.getSnake()) {
			if(rect.x==apple.getxPos() && rect.y==apple.getyPos()) {
				snake.grow();
				snake.setScore(snake.getScore() + 1);
				return true;
			}
		}
		
		for(Rectangle rect : snake_2.getSnake()) {
			if(rect.x==apple.getxPos() && rect.y==apple.getyPos()) {
				snake_2.grow();
				snake_2.setScore(snake_2.getScore() + 1);
				return true;
			}
		}
		
		return false;
		
	}


	
	
	}

	


	
