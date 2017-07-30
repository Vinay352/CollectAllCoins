package com.tutorial.mario.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.tutorial.mario.Game;
import com.tutorial.mario.entity.Entity;

public class KeyInput implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key=e.getKeyCode(); 
		for(Entity en:Game.handler.entity){
			switch(key){
			case KeyEvent.VK_W:
				//en.setVelY(-5);
				if(!en.jumping){
					en.jumping=true;
					en.gravity=9.0;
				}
				break;
			case KeyEvent.VK_J:
				//en.setVelY(-5);
				if(!en.jumping){
					en.jumping=true;
					en.gravity=6.0;
				}
				break;
			/*case KeyEvent.VK_S:
				en.setVelY(5);
				break;*/
			case KeyEvent.VK_A:
				en.setVelX(-5);
				en.facing=0;
				break;
			case KeyEvent.VK_D:
				en.setVelX(5);
				en.facing=1;
				break;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key=e.getKeyCode();
		for(Entity en:Game.handler.entity){ 
			switch(key){
			case KeyEvent.VK_W:
				en.setVelY(0);
				break;
			/*case KeyEvent.VK_S:
				en.setVelY(0);
				break;*/
			case KeyEvent.VK_A:
				en.setVelX(0);
				break;
			case KeyEvent.VK_D:
				en.setVelX(0);
				break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
