package com.tutorial.mario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.tutorial.mario.black.Black;
import com.tutorial.mario.black.BlackMain;
import com.tutorial.mario.coin.Coin;
import com.tutorial.mario.coin.CoinMain;
import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.entity.Player;
import com.tutorial.mario.tile.Tile;
import com.tutorial.mario.tile.Wall;
import com.tutorial.mario.black.BlackMain;

public class Handler {
	
	public int noOfCoins=0;
	
	public LinkedList<Entity> entity=new LinkedList<Entity>();
	public LinkedList<Tile> tile=new LinkedList<Tile>();
	public LinkedList<CoinMain> coin=new LinkedList<CoinMain>();
	public LinkedList<BlackMain> b=new LinkedList<BlackMain>();
	
	/*public Handler(){
		createLevel();
	}*/
	
	public void render(Graphics g){
		for(Entity en:entity){
			en.render(g);
		}
		for(Tile ti:tile){
			ti.render(g);
		}
		for(CoinMain c:coin){
			c.render(g);
		}
		for(BlackMain v:b){
			v.render(g);
		}
	}
	
	public void tick(){
		for(Entity en:entity){
			en.tick();
		}
		for(Tile ti:tile){
			ti.tick();
		}
		for(CoinMain c:coin){
			c.tick();
		}
		for(BlackMain v:b){
			v.tick();
		}
	}
	
	public void addEntity(Entity en){
		entity.add(en);
	}
	
	public void removeEntity(Entity en){
		entity.remove(en);
	}
	
	public void addTile(Tile ti){
		tile.add(ti);
	}
	
	public void removeTile(Tile ti){
		tile.remove(ti);
	}
	
	public void addCoin(CoinMain c){
		coin.add(c);
	}
	
	public void removeCoin(CoinMain c){
		coin.remove(c);
	}
	
	public void addBlack(BlackMain c){
		b.add(c);
	}
	
	public void removeBlack(BlackMain c){
		b.remove(c);
	}
	
	public void append(CoinMain t){
		int x,y;
		x=t.getX();
		y=t.getY();
		addBlack(new Black(x,y,64,64,false,Id.bla,this));
	}
	
	public void createLevel(){
		for(int i=0;i<Game.WIDTH*Game.SCALE/64+1;i++){
			addTile(new Wall(i*64,Game.HEIGHT*Game.SCALE-64,64,64,true,Id.wall, this));
			
			if(i%2==0 && i<Game.WIDTH*Game.SCALE/64-1)
				addCoin(new Coin(i*64,Game.HEIGHT*Game.SCALE-64-64,64,64,true,Id.coins, this));
	
			if(i<Game.WIDTH*Game.SCALE/64-2){
				if(i%2==0)
					addCoin(new Coin((i+2)*64,Game.HEIGHT*Game.SCALE-64-64-64-64,64,64,true,Id.coins, this));
				addTile(new Wall((i+2)*64,Game.HEIGHT*Game.SCALE-64-128,64,64,true,Id.wall, this));
			}
			if(i<Game.WIDTH*Game.SCALE/64-4){
				if(i%2==0)
					addCoin(new Coin((i+4)*64,Game.HEIGHT*Game.SCALE-64-64-64-64-128,64,64,true,Id.coins, this));
				addTile(new Wall((i+4)*64,Game.HEIGHT*Game.SCALE-64-128-64-64,64,64,true,Id.wall, this));
			}
			if(i<Game.WIDTH*Game.SCALE/64-6){
				if(i%2==0)
					addCoin(new Coin((i+6)*64,Game.HEIGHT*Game.SCALE-64-64-64-64-128-128,64,64,true,Id.coins, this));
				addTile(new Wall((i+6)*64,Game.HEIGHT*Game.SCALE-64-128-64-64-64-64,64,64,true,Id.wall, this));
			}
			if(i<Game.WIDTH*Game.SCALE/64-8){
				if(i%2==0)
					addCoin(new Coin((i+8)*64,Game.HEIGHT*Game.SCALE-64-64-64-64-128-128-128,64,64,true,Id.coins, this));
				addTile(new Wall((i+8)*64,Game.HEIGHT*Game.SCALE-64-128-64-64-64-64-64-64,64,64,true,Id.wall, this));
			}
			if(i<Game.WIDTH*Game.SCALE/64-10){
				if(i%2==0)
					addCoin(new Coin((i+10)*64,Game.HEIGHT*Game.SCALE-64-64-64-64-128-128-128-128,64,64,true,Id.coins, this));
				addTile(new Wall((i+10)*64,Game.HEIGHT*Game.SCALE-64-128-64-64-64-64-64-64-64-64,64,64,true,Id.wall, this));
			}
			if(i<Game.WIDTH*Game.SCALE/64-12){
				if(i%2==0)
					addCoin(new Coin((i+12)*64,Game.HEIGHT*Game.SCALE-64-64-64-64-128-128-128-128-128,64,64,true,Id.coins, this));
				addTile(new Wall((i+12)*64,Game.HEIGHT*Game.SCALE-64-128-64-64-64-64-64-64-64-64-64-64,64,64,true,Id.wall, this));
			}
					
			/*if(i>10 && i<Game.WIDTH*Game.SCALE/64)
				addTile(new Wall(i*64,350,64,64,true,Id.wall, this));*/
		}
		noOfCoins = coin.size();
	}
	
	/*public void createLevel(BufferedImage level){
		int width=level.getWidth();
		int height=level.getHeight();
		
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				int pixel=level.getRGB(x,y);
				
				int red= ((pixel>>16) & 0xff);
				int green= ((pixel>>8) & 0xff);
				int blue= ((pixel) & 0xff);
				
				if(red==0 && green==0 && blue==0){
					addTile(new Wall(x*64,y*64,64,64,true,Id.wall,this));
					//black pixel
				}
				if(red==0 && green==0 && blue==255){
					addEntity(new Player(x*64,y*64,64,64,false,Id.player,this));
					//blue pixel
				}
			}
		}
	}*/
	
}
