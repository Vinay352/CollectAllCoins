package com.tutorial.mario.black;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

public abstract class BlackMain {
	public int x,y;
	public int width,height;
	
	public boolean solid;
	
	public int velX,velY;
	
	public Id id;
	
	public Handler handler;
	
	public BlackMain(int x,int y,int width,int height,boolean solid, Id id,Handler handler){
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
	
	/*public void die(){
		handler.removeCoin(this);
	}*/

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

	public boolean isSolid() {
		return solid;
	}
	
	public Id getId(){
		return id;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public Rectangle getBounds(){
		return new Rectangle(getX(),getY(),width,height);
	}
}
