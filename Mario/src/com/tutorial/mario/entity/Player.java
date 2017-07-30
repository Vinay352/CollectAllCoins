package com.tutorial.mario.entity;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Timer;

import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.coin.CoinMain;
import com.tutorial.mario.tile.Tile;

public class Player extends Entity{
	
	private int frame=0;
	private int frameDelay=0;
	
	private boolean animate=false;
	
	public int n=0;
	
	private static Handler h;
	public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		if(facing==0){
			g.drawImage(Game.player[frame].getBufferedImage(), x, y, width, height, null);
		}else if(facing==1){
			g.drawImage(Game.player[frame+5].getBufferedImage(), x, y, width, height, null);
		}
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
		x+=velX;
		y+=velY;
		if(x<=0)
			x=0;
		/*if(y<=0)
			y=0;*/
		if(x+width>=1080)
			x=1080-width;
		if(y+height>=771)
			y=771-height;
		
		if(velX!=0)
			animate=true;
		else
			animate=false;
		
		for(CoinMain t:handler.coin){
			if(t.getId()==Id.coins){
				if(getBoundsTop().intersects(t.getBounds())){
					t.setSolid(false);
					handler.append(t);
					n++;
				}
				if(getBoundsBottom().intersects(t.getBounds())){
					t.setSolid(false);
					handler.append(t);
					n++;
				}
				if(getBoundsLeft().intersects(t.getBounds())){
					t.setSolid(false);
					handler.append(t);
					n++;
				}
				if(getBoundsRight().intersects(t.getBounds())){
					t.setSolid(false);
					handler.append(t);
					n++;
				}
			}
		}
		
		for(Tile t:handler.tile){
			if(!t.solid)
				break;
			if(t.getId()==Id.wall){
				if(getBoundsTop().intersects(t.getBounds())){
					setVelY(0);
					if(jumping){
						jumping=false;
						gravity=0.8;
						falling=true;
					}
					/*y=t.getY()+t.height;*/
				}
				if(getBoundsBottom().intersects(t.getBounds())){
					setVelY(0);
					if(falling)
						falling=false;
					/*y=t.getY()-t.height;*/
				}
				else{
					if(!falling && !jumping){
						gravity=0.8;
						falling=true;
					}
				}
				if(getBoundsLeft().intersects(t.getBounds())){
					setVelX(0);
					x=t.getX()+t.width;				
				}
				if(getBoundsRight().intersects(t.getBounds())){
					setVelX(0);
					x=t.getX()-t.width;
				}
			}
		} 
		if(jumping){
			gravity-=0.1;
			setVelY((int)-gravity);
			if(gravity<=0.0){
				jumping=false;
				falling=true;
			}
		}
		if(falling){
			gravity+=0.1;
			setVelY((int)gravity);
		}
		if(animate){
			frameDelay++;
			if(frameDelay>=3){
				frame++;
				if(frame>=5){
					frame=0;
				}
				frameDelay=0;
			}
		}
		
	}
	
}
