
package oop.java.project;


import javax.swing.JFrame;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	
	private static int HEIGHT=640;
	private static int WIDTH=616;
	
	public GUI() {
		super();
		init();
	}
	
	public void init() {
		this.setTitle("Snake");
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null); // center the frame
		this.setResizable(false);
		this.add(new Panel()); // add panel to the frame 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
	    java.awt.EventQueue.invokeLater(new Runnable() {
	    	public void run() {
	    		try {
	    			new GUI().setVisible(true);
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		}
	    	}
	    });
	}
}
	
	     



