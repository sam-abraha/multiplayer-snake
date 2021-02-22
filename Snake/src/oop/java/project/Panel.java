
package oop.java.project;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.Shape;

public class Panel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	private Snake snake;
	private Snake snake_2;
	private Apple apple;
	
	private Graphics g;
	
	private JButton sp_button;
	private JButton mp_button;
	
	private static final Color NEON_GREEN = new Color(102,255,102);
	private static final Color PURPLE = new Color(106,13,173);
	
	private int gameMode;
	private GameState gameState;
	

	private Timer timer;

	
	public Panel() {
		
		timer=new Timer(75,this); // due to the timer images will be repainted every 5 mi.seconds
		timer.start();
		
		
		snake=new Snake(Commons.WIDTH/2,(Commons.HEIGHT/2)-Commons.SIZE*12);
		snake_2=new Snake(Commons.WIDTH/2,(Commons.HEIGHT/2)+Commons.SIZE*12);
		apple=new Apple(Commons.WIDTH/2,Commons.HEIGHT/2);
		
		sp_button=new JButton();  
		sp_button.setVisible(false);  // set Button invisible since its only used in the menu
		mp_button=new JButton();
		mp_button.setVisible(false);  // set Button invisible since its only used in the menu
		
		
		
		this.addKeyListener(new KeyListenerObj(this));  //  panel is now able to visualize our key event
		this.setFocusable(true);                       // key events will only be dispatched to components with focus
		this.setSize(WIDTH,HEIGHT); // sets size of the panel 
		this.setGameState(GameState.START); // sets game state to start

	}
	
	
	 public GameState getGameState() {
		return gameState;
	}


	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Snake getSnake() {
		// TODO Auto-generated method stub
		return snake;
	}
	
	public void setGameMode(int x) {
		
		if(x==1 || x==2)
			gameMode=x;
	}
	
	public int getGameMode() {
		return gameMode;
	}
	

	public Snake getSnake_2() {
		return snake_2;
	}

	public void setSnake_2(Snake snake_2) {
		this.snake_2 = snake_2;
	}
	
	
	private JButton spButton() {
		
		sp_button.setText("SINGLEPLAYER");
		sp_button.setLocation(150,200);
		sp_button.setBackground(Color.WHITE);
		sp_button.setPreferredSize(new Dimension(300,50));
		sp_button.setVisible(true);
		sp_button.addActionListener(new ActionListener() { // ActionListener object
			public void actionPerformed(ActionEvent e) { // Action when button pressed
			setGameMode(1);
			setGameState(GameState.RUNNING);
			}
		});
			return sp_button; // returns button
	}
	
	private JButton mpButton() {
		
		mp_button.setText("MULTIPLAYER");
		mp_button.setLocation(150,300);
		mp_button.setBackground(Color.WHITE);
		mp_button.setPreferredSize(new Dimension(300,50));
		mp_button.setVisible(true);
		mp_button.addActionListener(new ActionListener() { // ActionListener object
			public void actionPerformed(ActionEvent e) { // Action when button pressed
				setGameMode(2);
				setGameState(GameState.RUNNING);
			}
		});
			return mp_button; // returns button
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		gameplay();
		collision();
		check_boundaries();
		
	}
	

	/**
	 *  paints images onto the panel
	 */
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(getGameState()!=GameState.GAMEOVER) {
			
			if(getGameState()==GameState.START) {
				
				try {                
				    BufferedImage img = ImageIO.read(new File("C:\\Users\\Lucas\\Downloads\\snake_java_img.jpg"));
				    g.drawImage(img,0,0,this);
				  } catch (IOException ex) {
				    //handle exception
				  	ex.printStackTrace();
				  }

				
				g.setColor(Color.WHITE);
				g.setFont(new Font("Ink Free",Font.BOLD, 30)); 
				g.drawString("SNAKE", (Commons.getWidth()/2)-3*Commons.getSize(), (Commons.getHeight()/2)-6*Commons.getSize());
				g.setFont(new Font("Ink Free",Font.BOLD, 20));
				g.drawString("made by ...",(Commons.getWidth()/2)-Commons.getSize()*6,(Commons.getHeight()/2)-3*Commons.getSize());	
				g.setColor(Color.RED);
				g.setFont(new Font("Ink Free",Font.BOLD, 30));
				g.drawString("PRESS SPACE TO START",(Commons.getWidth()/2)-9*Commons.getSize(), (Commons.getHeight()/2)+Commons.getSize()*4);
				
					
			}
			
			if(getGameState()==GameState.MENU) {
				
				try {                
				    BufferedImage img = ImageIO.read(new File("C:\\Users\\Lucas\\Downloads\\snake_java_img.jpg"));
				    g.drawImage(img,0,0,this);
				  } catch (IOException ex) {
				       // handle exception
				  	ex.printStackTrace();
				  }

				
				/**
				 * enable choosing  singleplayer or  multiplayer in a menu
				 */
				
				g.setColor(Color.RED);
				g.setFont(new Font("Ink Free",Font.BOLD, 30)); 
				g.drawString("CHOOSE A GAME MODE", 125, 100);
				
				this.add(sp_button);  // add singleplayer button
				this.add(spButton());
				
				this.add(mp_button);  // add multiplayer button
				this.add(mpButton());
			}
	
				
	        if(getGameState()==GameState.RUNNING) {
	        	
	     
	        	/**
	        	 *  remove buttons
	        	 */
	        	
	        	this.remove(sp_button);
	        	this.remove(mp_button);
	        	
				
				/**
				 * cast to 2D Graphics to draw 2D objects
				 */
				Graphics2D g2d=(Graphics2D)g;
				
				/**
				 *  set 2 different background colors depending on mode
				 */
				
				if(getGameMode()==1) {
					setBackground(Color.lightGray);
				}
				else
				{
					setBackground(PURPLE);
				}
				
				
				/**
				 *  a grid-like field will be created
				 */
				
				g.setColor(Color.WHITE);
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
				 *  draws both snakes and their score board
				 */
				
				g2d.setColor(Color.BLACK);
				for(Rectangle rect : snake.getSnake())
					g2d.fill(rect);
				
				g.setColor(Color.black);
				g.setFont(new Font("Ink Free",Font.BOLD | Font.ITALIC,15));
				g.drawString("SCORE :  " + snake.getScore(),25,15);
				
				
				if(getGameMode()==2) {
					g2d.setColor(Color.WHITE);
					for(Rectangle rect : snake_2.getSnake())
						g2d.fill(rect);
					
					g.setFont(new Font("Ink Free",Font.BOLD | Font.ITALIC,15));
					g.drawString("SCORE :  " + snake_2.getScore(),Commons.getWidth()-Commons.getSize()*5,15);
					
				}
				
				/**
				 * calls rainbow method
				 */
				rainbow(g2d);
					
        }
	}
		if(getGameState()==GameState.GAMEOVER)
		{
			
            setBackground(Color.BLACK);
            
            /**
             * draw screen when game is over
             */
			
			g.setColor(Color.RED);
			g.setFont(new Font("Ink Free",Font.BOLD,50));
			g.drawString("GAME OVER",150,200);
	
			
			g.setFont(new Font("Ink Free",Font.BOLD,20));
			g.drawString("PRESS ESC TO CLOSE", 200,500);
			
			if(getGameMode()==1) {
				
				g.setFont(new Font("Ink Free",Font.BOLD,30));
				g.setColor(Color.WHITE);
				g.drawString("Your Score : " + snake.getScore(), 200, 260);
				

			}
			 
			else {
				
				g.setFont(new Font("Ink Free",Font.BOLD,30));
				g.setColor(Color.WHITE);
				g.drawString("P1 Score : " + snake.getScore(), 200, 260);
				
				g.setColor(Color.WHITE);
				g.drawString("P2 Score : " + snake_2.getScore(), 200, 300);
				
				g.setColor(Color.RED);
				g.setFont(new Font("Ink Free",Font.BOLD,50));
				
				if(snake.getScore()>snake_2.getScore()) {
					
					g.drawString("P1 WON !!!",175, 400);	
				}
				
				if(snake.getScore()==snake_2.getScore()) {
					
					g.drawString("TIE GAME !!!",175, 400);	
				}
				
				if(snake.getScore() < snake_2.getScore()) {
					
					g.drawString("P2 WON !!!",175, 400);	
				
				}
			}
		}
	}
	
	/**
	 * draws the snake in rainbow colors
	 * @param g
	 */
	
	public void rainbow(Graphics g2d)  {
		
		if(snake.getScore() > 5) {
			 for (int i=0;i<snake.getSnake().size();i++) {
				  Random rand = new Random();
				  final float hue =rand.nextFloat();
				  final float saturation = 0.9f;
				  final float luminance=1.0f;
				  Color color=Color.getHSBColor(hue, saturation, luminance);
				  g2d.setColor(color);
				  ((Graphics2D) g2d).fill(snake.getSnake().get(i));
			   }
		}
		
		if(snake_2.getScore() > 5) {
			 for (int i=0;i<snake_2.getSnake().size();i++) {
				  Random rand = new Random();
				  final float hue =rand.nextFloat();
				  final float saturation = 0.9f;
				  final float luminance=1.0f;
				  Color color=Color.getHSBColor(hue, saturation, luminance);
				  g2d.setColor(color);
				  ((Graphics2D) g2d).fill(snake_2.getSnake().get(i));
			   }
		}
			 		 
	}
		
	/**
	 * sets snakes to the opposite position if boundaries crossed
	 */
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
		
		if(snake.getDirection() != Direction.NOT_MOVING && getGameState()!=GameState.GAMEOVER) {
			snake.move();
		}
		
		if(snake_2.getDirection() != Direction.NOT_MOVING && getGameState()!=GameState.GAMEOVER) {
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
	
	/**
	 * @return true if a collision took place
	 */
	public boolean collision() {
		
		Rectangle head = snake.getSnake().get(0);
		Rectangle head_2=snake_2.getSnake().get(0);
		
		/**
		 * player 1's snake collison, index starts with 1 beacuse we want to detect colliison between head and body
		 */
		
		for(int i=1;i<=snake.getSnake().size()-1;i++) {
			if(head.x==snake.getSnake().get(i).x && head.y==snake.getSnake().get(i).y) {
				setGameState(GameState.GAMEOVER);
				return true;
			}
		}
		
		if(getGameMode()==2) {
			
			/**
			 * player 2's snake self collision
			 */
			
			for(int i=1;i<=snake_2.getSnake().size()-1;i++) {
				if(head_2.x==snake_2.getSnake().get(i).x && head_2.y==snake_2.getSnake().get(i).y) {
					setGameState(GameState.GAMEOVER);
					return true;
				}
			}
			
			/**
			 * collison between player 1 and player 2 initiated by player 1
			 */
		
			
			for(int i=0;i<snake_2.getSnake().size()-1;i++) {
				if(head.x==snake_2.getSnake().get(i).x && head.y==snake_2.getSnake().get(i).y ) {
					setGameState(GameState.GAMEOVER);
					return true;
				}
			}
			
			/**
			 * collision between player 2 an player 1 initiated by player 2
			 */
			
			for(int i=0;i<snake.getSnake().size();i++) {
				if(head_2.x==snake.getSnake().get(i).x && head_2.y==snake.getSnake().get(i).y ) {
					setGameState(GameState.GAMEOVER);
					return true;
				}
			}
			
			

		}
		
		return false;
	}

	
	
	
	
	
	

	
	
}
