package oop.java.project;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyListenerObj implements KeyListener{
	
	private Panel panel;
	
	/**
	 * @param panel
	 * takes our panel as an arguments in order to add a KeyListener to our panel inside the panel class
	 */
	public KeyListenerObj(Panel panel) {
		this.panel=panel;
	}
	
	
	/**
	 *  enables key events when in this case arrow keys are pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    
	   
	    /**
	     * Each key press is described by a sequence of key events which are accompanied by a key code
	     */
	    
	    
	    switch(keyCode) { 
	    
	        case KeyEvent.VK_UP:
	        	if(panel.isRunning() && panel.getSnake().getDirection()!=Direction.DOWN)
		        	panel.getSnake().setDirection(Direction.UP);
	            break;
	            
	        case KeyEvent.VK_DOWN:
	        	if(panel.isRunning() && panel.getSnake().getDirection()!=Direction.UP)
	        		panel.getSnake().setDirection(Direction.DOWN);
	            break;
	            
	        case KeyEvent.VK_LEFT:
	        	if(panel.isRunning() && panel.getSnake().getDirection()!=Direction.RIGHT)
	        		panel.getSnake().setDirection(Direction.LEFT);
	            break;
	            
	        case KeyEvent.VK_RIGHT:
	        	if(panel.isRunning() && panel.getSnake().getDirection()!=Direction.LEFT)
	        		panel.getSnake().setDirection(Direction.RIGHT);
	            break;
	            
	        case KeyEvent.VK_W:
	        	if(panel.isRunning() && panel.getSnake_2().getDirection()!=Direction.DOWN)
	        		panel.getSnake_2().setDirection(Direction.UP);
	        	break;
	        	
	        case KeyEvent.VK_A:
	        	if(panel.isRunning() && panel.getSnake_2().getDirection()!=Direction.RIGHT)
	        		panel.getSnake_2().setDirection(Direction.LEFT);
	        	break;
	        	
	        case KeyEvent.VK_S:
	        	if(panel.isRunning() && panel.getSnake_2().getDirection()!=Direction.UP)
	        		panel.getSnake_2().setDirection(Direction.DOWN);
	        	break;
	        	
	        case KeyEvent.VK_D:
	        	if(panel.isRunning() && panel.getSnake_2().getDirection()!=Direction.LEFT)
	        		panel.getSnake_2().setDirection(Direction.RIGHT);
	        	break;
	            
	        case KeyEvent.VK_SPACE :
	        	panel.setMenu(true);
	        	break;
	        	
	        
	        case KeyEvent.VK_ESCAPE:
	        	System.exit(1);
	        
	     }
	    panel.repaint();
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
