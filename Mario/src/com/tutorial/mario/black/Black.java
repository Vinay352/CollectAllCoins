package com.tutorial.mario.black;

import java.awt.Graphics;

import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

public class Black extends BlackMain{
	public Black(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Game.black.getBufferedImage(), x, y, width, height, null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
