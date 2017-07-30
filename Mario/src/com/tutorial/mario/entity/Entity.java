package com.tutorial.mario.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

public abstract class Entity {
	/*An abstract class is a class that is declared 
	 * abstract —it may or may not include abstract methods.
	 * Abstract classes cannot be instantiated, but they can 
	 * be subclassed. When an abstract class is subclassed, 
	 * the subclass usually provides implementations for all 
	 * of the abstract methods in its parent class.
	 * */
	/*An abstract method is a method that is declared, 
	 * but contains no implementation.By using abstract classes, 
	 * you can inherit the implementation of other (non-abstract)
	 * methods. 
	 * */
	
	public int x,y;
	public int width,height;
	public int facing=0;//0-left, 1-right
	
	public boolean solid;
	
	public int velX,velY;
	
	public Id id;
	
	public boolean jumping =false;
	public boolean falling =true;
	
	public double gravity=0.0;
	
	public Handler handler;
	
	public Entity(int x,int y,int width,int height,boolean solid,Id id,Handler handler){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.solid=solid;
		this.id=id;
		this.handler=handler;
	}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public void die(){
		handler.removeEntity(this);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Id getId(){
		return id;
	}

	public boolean isSolid() {
		return solid;
	}

	/*public void setSolid(boolean solid) {
		this.solid = solid;
	}*/

	/*public int getVelX() {
		return velX;
	}*/

	public void setVelX(int velX) {
		this.velX = velX;
	}

	/*public int getVelY() {
		return velY;
	}*/

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(getX(),getY(),width,height);
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle(getX()+10,getY(),width-20,5);
	}
	
	public Rectangle getBoundsBottom(){
		return new Rectangle(getX()+10,getY()+width-5,width-20,5);
	}
	
	public Rectangle getBoundsLeft(){
		return new Rectangle(getX(),getY()+10,5,height-20);
	}
	
	public Rectangle getBoundsRight(){
		return new Rectangle(getX()+width-5,getY()+10,5,height-20);
	}
	
}
