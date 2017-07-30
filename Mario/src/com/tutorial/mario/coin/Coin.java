package com.tutorial.mario.coin;

import java.awt.Graphics;

import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.tile.Tile;

public class Coin extends CoinMain{
	public Coin(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Game.coinImage.getBufferedImage(), x, y, width, height, null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
