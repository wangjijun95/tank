package com.jijun.tank;

import com.jijun.tank.cor.GameObject;

import java.awt.Graphics;


public class Explode extends GameObject {

	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	private boolean living = true;
	GameModel gm = null;
	
	private int step = 0;
	
	private int x,y;
	public Explode(int x, int y, GameModel gm) {
		super();
		this.x = x;
		this.y = y;
		this.gm = gm;
		new Thread(()->new Audio("audio/explode.wav").play()).start();
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
	
	public void paint(Graphics g){
		g.drawImage(ResourceMgr.explodes[step++], this.x, this.y, null);
		if(step >= ResourceMgr.explodes.length)
			gm.objects.remove(this);
	}
	
}
