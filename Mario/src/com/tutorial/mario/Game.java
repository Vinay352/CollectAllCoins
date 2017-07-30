package com.tutorial.mario;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.entity.Player;
import com.tutorial.mario.gfx.Sprite;
import com.tutorial.mario.gfx.SpriteSheet;
import com.tutorial.mario.input.KeyInput;
import com.tutorial.mario.tile.Wall;

public class Game extends Canvas implements Runnable{
	
	/*The distinction is that implements means that you're using 
	 * the elements of a Java Interface in your class, and extends means 
	 * that you are creating a subclass of the class you are extending. 
	 * You can only extend one class in your new class, but you can implement
	 * as many interfaces as you would like.
	 */
	
	/*The Runnable interface should be implemented by any class
	 * whose instances are intended to be executed by a thread.
	 * The class must define a method of no arguments called 
	 * run. This interface is designed to provide a common 
	 * protocol for objects that wish to execute code while 
	 * they are active.
	 */
	
	public static final int WIDTH=270;
	public static final int HEIGHT=WIDTH/14*10;
	public static final int SCALE=4;
	public static final String TITLE="Mario";
	
	private Thread thread;
	private boolean running=false;
	private BufferedImage image;
	
	public static Handler handler;
	public static SpriteSheet sheet;
	public static Camera cam;
	
	public static Sprite grass;
	public static Sprite player[]=new Sprite[10];
	public static Sprite coinImage;
	public static Sprite black;
	
	private void init(){
		handler=new Handler();
		sheet=new SpriteSheet("/spritesheet.png");
		cam=new Camera();
		
		addKeyListener(new KeyInput());
		
		grass=new Sprite(sheet,3,1);
		
		for(int i=0;i<player.length;i++){
			player[i]=new Sprite(sheet,i+1,16);
		}
		
		coinImage=new Sprite(sheet,1,8);
		black=new Sprite(sheet,2,8);
		
		handler.addEntity(new Player(40,412,64,64,true,Id.player,handler));
	    
		//handler.addTile(new Wall(200,200,64,64,true,Id.wall,handler));
		
		/*try {
			image=ImageIO.read(getClass().getResource("/level.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		handler.createLevel();
	}
	
	private synchronized void start(){
		/*This function is to start the thread. "synchronized" means that 
		 this thread wont face any kind of interruption from any other threads.
		 */
		//System.out.println("sadd");
		if(running)
			return;
		running=true;
		thread=new Thread(this,"Thread");
		thread.start();
	}
	
	private synchronized void stop(){
		/*This function is to stop the thread. "synchronized" means that 
		 this thread wont face any kind of interruption from any other threads.
		 */
		if(!running)
			return;
		running =false;
		try {
			thread.join();
			/*The join method allows one thread to wait for the
			 * completion of another. If t is a Thread object 
			 * whose thread is currently executing, t.join();
			 * causes the current thread to pause execution 
			 * until t's thread terminates.
			 * join() method to ensure all threads that started from 
			 * main must end in order in which they started and also 
			 * main should end in last. In other words waits for this 
			 * thread to die. 
			 * */
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		init();
		requestFocus();
		/*As for requestFocus(), this method is used to make the component 
		 * get input focus. This means that if you press any kind of key 
		 * or give any input, the input is heard by the respective Listener
		 * for that component.
		 * */
		//System.out.println("sadasdasd");
		// TODO Auto-generated method stub
		long lastTime=System.nanoTime();
		long timer=System.currentTimeMillis();
		/*The above two commands calculate time in ns and ms respectively.
		 * */
		double delta=0.0;
		double ns=1000000000.0/60.0;
		int frames=0;
		int ticks=0;
		while(running){
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			while(delta>=1){
				tick();   
				ticks++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				//System.out.println(frames+" frames per second "+ticks+" updates per second");
				frames=0;
				ticks=0;
			}
		}
		stop();//To stop the thread.
	}
	
	public void render(){
		/*This function is to render or draw the graphics.
		 * */
		BufferStrategy bs=getBufferStrategy();//return the buffer strategy used
		if(bs==null){
			createBufferStrategy(3);
			/*The last or the third buffer layer provides 
			 * the second buffer layer with images and graphics
			 * which in turn provides the same+some additional to 
			 * the first buffer layer which in turn provides this+
			 * some additional to the main screen
			 * */
			return;
		}
		Graphics g=bs.getDrawGraphics();
		/*Creates a graphics context for the drawing buffer. 
		 * This method may not be synchronized for performance
		 * reasons; use of this method by multiple threads should 
		 * be handled at the application level. Disposal of the 
		 * graphics object obtained must be handled by the application.
		 * */
		
		g.setColor(Color.BLACK);
		//Based on RGB values
		g.fillRect(0, 0,getWidth(), getHeight());
		
		//g.translate(cam.getX(), cam.getY());
		
		//g.setColor(Color.RED);
		
		g.fillRect(200, 200, getWidth(), getHeight());
		handler.render(g);
		
		g.dispose();
		bs.show();
		/*Without the above two commands, the output would be a 
		 * simple non-colored screen.
		 * */
	}
	
	public void tick(){
		/*This function is to update.
		 * */
		/*if(handler.b.size()+10==handler.noOfCoins){
			popup();
		}*/
		handler.tick();
		
		for(Entity e:handler.entity){
			if(e.getId()==Id.player){
				cam.tick(e);
			}
		}
	}
	
	private void popup() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null,"The Game Has Ended");
	}

	public int getFrameWidth(){
		return WIDTH*SCALE;
	}
	
	public int getFrameHeight(){
		return HEIGHT*SCALE;
	}
	
	
	public Game(){
		Dimension size=new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		/* The next three commands require Canvas to be extended by our class
		 */
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}
	
	public static void main(String[] args){
		Game game=new Game();
		JFrame frame=new JFrame(TITLE);
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);//Puts our frame in the middle of the screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//System.out.println("s");
		game.start();//this command is imp in order to run the run function
	}

	
}
