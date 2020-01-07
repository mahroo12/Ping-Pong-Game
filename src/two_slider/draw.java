package two_slider;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

//key listener for moving the slider
//action listener for the ball

import javax.swing.JPanel;

public class draw extends JPanel implements KeyListener, ActionListener {

	private boolean play = false; // the game shouldnt play by itself
	//private int score = 0; // initially the game will be 0

	//private int totalBricks = 21;

	private Timer timer; // timer class for how fast the ball should move
	private int delay = 8; // speed of the ball

	private int slider1 = 310; // starting position of the slider
	private int slider2 = 310; // starting position of the slider
	
	private int ballposx = 120; // x position of the ball
	private int ballposy = 350; // y position of the ball

	private int ballxdir = -1; // horizontal direction of the ball
	private int ballydir = -2; // vertical direction of the ball
	
	
	
	public draw() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		

	}
	/**
	 * will draw the ball, slider etc here
	 */
	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);


		
		// borders
		//TODO have to figure out which one is the top border
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		


		// paddle
		g.setColor(Color.green);
		g.fillRect(slider1, 550, 100, 8);
		
		// paddle2
		g.setColor(Color.red);
		g.fillRect(slider2, 10, 100, 8);

		// ball
		g.setColor(Color.yellow);
		g.fillOval(ballposx, ballposy, 20, 20);
		
		g.dispose();

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		
		if(play)
		{
			
			
			//used to intersect with the slider
			if(new Rectangle(ballposx, ballposy, 20, 20).intersects(new Rectangle(slider1, 550, 100, 8)))
			{
				ballydir = -ballydir;
			}
			
			
			if(new Rectangle(ballposx, ballposy, 20, 20).intersects(new Rectangle(slider2, 550, 100, 8)))
			{
				ballydir = -ballydir;
			}
			ballposx += ballxdir; // ball's x position increments by x direction
			ballposy += ballydir; // ball's y position increments by y direction
			// the ball's x position is less that 0 then it has hit the left border
			// therefore it changes its direction to opposite of that in x axis
			if(ballposx <0) //left border
			{
				ballxdir = -ballxdir;
			}
			if(ballposx > 670) //right border
			{
				ballxdir = -ballxdir;
			}
			
			
			if(ballposy <0) //top border
			{
				ballydir = -ballydir;
			}
			
		
		
		}
		repaint();
		
	}



	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// we want to increment the position of the slidebar
			// but dont want to go beyond the border
			if (slider1 >= 600) // 600 is the window's height

			{
				slider1 = 600;

			} else {
				moveRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (slider1 < 10) // 10 is the window's y position/x position
			// confused abt the bounds
			{
				slider1 = 10;

			} else {
				moveLeft();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			if(slider2<10)
			{
				slider2 = 10;
			}
			else {
				move_left();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if(slider2>600)
			{
				slider2 = 600;
			}
			else
			{
				move_right();
			}
		}
		
	}
	

	public void moveRight() {
		play = true;
		slider1 += 20;
	}

	public void moveLeft() {
		play = true;
		slider1 -= 20;
	}
	public void move_right() {
		play = true;
		slider2 += 20;
	}

	public void move_left() {
		play = true;
		slider2 -= 20;
	}
	
	
}


