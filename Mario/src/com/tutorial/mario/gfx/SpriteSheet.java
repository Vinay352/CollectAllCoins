package com.tutorial.mario.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	/*The BufferedImage subclass describes an Image with an accessible buffer of image data. 
	 *A BufferedImage is comprised of a ColorModel and a Raster of image data. 
	 * */
	private BufferedImage sheet;
	
	public SpriteSheet(String path){
		try{
			//get the image from the path specified from the res folder.
			sheet=ImageIO.read(getClass().getResource(path));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public BufferedImage getSprite(int x, int y){
		return sheet.getSubimage(x*32-32, y*32-32, 32, 32);
	}
}
