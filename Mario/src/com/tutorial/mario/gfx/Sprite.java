package com.tutorial.mario.gfx;

import java.awt.image.BufferedImage;

public class Sprite {
	public SpriteSheet sheet;
	
	public BufferedImage image;
	
	public Sprite(SpriteSheet sheet,int x, int y){
		image=sheet.getSprite(x, y);
	}
	
	//The function below is to render our sprite.
	public BufferedImage getBufferedImage(){
		return image;
	}
	
}
