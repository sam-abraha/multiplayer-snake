package oop.java.project;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyListenerObj implements KeyListener{
	
	private Panel game;
	
	/**
	 * @param panel
	 * takes our panel as an arguments in order to add a KeyListener to our panel inside the panel class
	 */
	public KeyListenerObj(Panel game) {
		this.game=game;
	}
	
	
	/**
	 *  enables key events for every individual key used in game when key is pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
	    /**
	     * Each key press is described by a sequence of key events which are accompanied by a key code
	     */
	    
	    int keyCode = e.getKeyCode();
	    
	    switch(keyCode) { 
	    
	        case KeyEvent.VK_UP:
	        	if(game.getGameState()==GameState.RUNNING && game.getSnake().getDirection()!=Direction.DOWN)
		        	game.getSnake().setDirection(Direction.UP);
	            break;
	            
	        case KeyEvent.VK_DOWN:
	        	if(game.getGameState()==GameState.RUNNING && game.getSnake().getDirection()!=Direction.UP)
	        		game.getSnake().setDirection(Direction.DOWN);
	            break;
	            
	        case KeyEvent.VK_LEFT:
	        	if(game.getGameState()==GameState.RUNNING && game.getSnake().getDirection()!=Direction.RIGHT)
	        		game.getSnake().setDirection(Direction.LEFT);
	            break;
	            
	        case KeyEvent.VK_RIGHT:
	        	if(game.getGameState()==GameState.RUNNING && game.getSnake().getDirection()!=Direction.LEFT)
	        		game.getSnake().setDirection(Direction.RIGHT);
	            break;
	            
	        case KeyEvent.VK_W:
	        	if(game.getGameState()==GameState.RUNNING && game.getSnake_2().getDirection()!=Direction.DOWN)
	        		game.getSnake_2().setDirection(Direction.UP);
	        	break;
	        	
	        case KeyEvent.VK_A:
	        	if(game.getGameState()==GameState.RUNNING && game.getSnake_2().getDirection()!=Direction.RIGHT)
	        		game.getSnake_2().setDirection(Direction.LEFT);
	        	break;
	        	
	        case KeyEvent.VK_S:
	        	if(game.getGameState()==GameState.RUNNING && game.getSnake_2().getDirection()!=Direction.UP)
	        		game.getSnake_2().setDirection(Direction.DOWN);
	        	break;
	        	
	        case KeyEvent.VK_D:
	        	if(game.getGameState()==GameState.RUNNING && game.getSnake_2().getDirection()!=Direction.LEFT)
	        		game.getSnake_2().setDirection(Direction.RIGHT);
	        	break;
	            
	        case KeyEvent.VK_SPACE :
	        	if(game.getGameState()==GameState.START) {
	        		game.setGameState(GameState.MENU);
	        	}
	        	break;
	        	
	        
	        case KeyEvent.VK_ESCAPE:
	        	System.exit(1);
	        
	     }
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	} 

}
