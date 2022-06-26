import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Gameworkspace extends JPanel implements Runnable, KeyListener{

	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1080, HEIGHT = 1080;
	
	private Thread thread;
	
	private boolean starting;
	
	private boolean right = true, left = false, up = false, down = false;
	
	private GameBody x;
	private ArrayList<GameBody> snake;
	
	private Points point;
	private ArrayList<Points> points;
	
	private Random dotr;
	
	private int aCord = 10, bCord = 10, size = 15;
	private int ticks = 0;
	
	
	public Gameworkspace() {
		setFocusable(true);	
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);

		snake = new ArrayList<GameBody>();
		points = new ArrayList<Points>();
		
		dotr = new Random();
		
		start();
	}
	
	public void start() {
		starting = true;
		thread = new Thread(this);
		thread.start();
	}
	public void stop() {
		starting = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void tick() {
		if(snake.size() == 0) {
			x = new GameBody(aCord, bCord, 10);
			snake.add(x);
		}
		ticks++;
		if(ticks > 500000) {
			if(right) aCord++;
			if(left) aCord--;
			if(up) bCord --;
			if(down) bCord++;
			
			ticks = 0;
			
			x = new GameBody(aCord, bCord, 10);
			snake.add(x);
			
			if(snake.size() > size) {
				snake.remove(0);
			}
		}
		if(points.size() == 0) {
			int aCord = dotr.nextInt(108);
			int bCord = dotr.nextInt(108);
			
			point = new Points(aCord, bCord, 10);
			points.add(point);
		}
		
		for(int i = 0; i < points.size(); i++) {
			if(aCord == points.get(i).getaCord() && bCord == points.get(i).getbCord()) {
				size++;
				points.remove(i);
				i++;
			}
		}
		// end on snake body part
		for(int i = 0; i < snake.size(); i++) {
			if(aCord == snake.get(i).getaCord() && bCord == snake.get(i).getbCord()) {
				if(i != snake.size()-1) {
					System.out.println("Game Over");
					stop();
				}
			}
		}
		
		// end on border
		if(aCord < 0 || aCord > 108 || bCord < 0 || bCord > 108) {
			System.out.println("Game Over");
			stop();
		}
		
	}
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < WIDTH/10; i++) {
			g.drawLine(i * 10, 0, i * 10 , HEIGHT);
		}
		for(int i = 0; i < HEIGHT/10; i++) {
			g.drawLine(0, i * 10, HEIGHT , i * 10);
		}
		for(int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		for(int i = 0; i < points.size(); i++) {
			points.get(i).draw(g);
		}
		g.setColor(Color.RED);
		g.setFont(new Font("Ink Free", Font.BOLD, 25));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score:" + points, (WIDTH - metrics.stringWidth("Score: " + points))/2, g.getFont().getSize());
	}

	@Override
	public void run() {
		
		while(starting) {
			tick();
			repaint();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_UP && !down) {
			up = true;
			left = false;
			right = false;
		}
		if(key == KeyEvent.VK_DOWN && !up) {
			down = true;
			left = false;
			right = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
