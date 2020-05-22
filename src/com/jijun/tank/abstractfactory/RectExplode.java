package com.jijun.tank.abstractfactory;

import java.awt.Graphics;

import com.jijun.tank.Audio;
import com.jijun.tank.ResourceMgr;
import com.jijun.tank.TankFrame;

public class RectExplode extends BaseExplode{

	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	private boolean living = true;
	TankFrame tf = null;
	
	private int step = 0;
	
	private int x,y;
	public RectExplode(int x, int y, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
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
			tf.explodes.remove(this);
	}
	
}
