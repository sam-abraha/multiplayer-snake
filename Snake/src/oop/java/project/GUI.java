package oop.java.project;



import javax.swing.JFrame;

public class GUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static int HEIGHT=640;
	private static int WIDTH=620-4;
	private static int SIZE=20;
	
	public GUI() {
		super();
		this.setTitle("Game");
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null); 
		this.add(new Panel());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	

}


