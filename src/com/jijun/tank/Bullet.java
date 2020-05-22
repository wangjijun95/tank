package com.jijun.tank;

import com.jijun.tank.cor.GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;


public class Bullet extends GameObject {
	private static final int SPEED = 10;
	private boolean living = true;
	private GameModel gm = null;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	public Group group = Group.GOOD;
	public Rectangle rec = new Rectangle();
	
	private int x,y;
	private Dir dir;
	public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.gm = gm;
		rec.x = this.x;
		rec.y = this.y;
		rec.width = WIDTH;
		rec.height = HEIGHT;
		gm.objects.add(this);
	}
	
	public Group getGroup() {
		return group;
	}



	public void setGroup(Group group) {
		this.group = group;
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
	public Dir getDir() {
		return dir;
	}
	public void setDir(Dir dir) {
		this.dir = dir;
	}
	
	public void paint(Graphics g){
		if(!living){
			gm.objects.remove(this);
		}
		switch (dir) {
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, this.x, this.y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, this.x, this.y, null);
			break;
		case LIFT:
			g.drawImage(ResourceMgr.bulletL, this.x, this.y, null);
			break;
		case RIGTH:
			g.drawImage(ResourceMgr.bulletR, this.x, this.y, null);
			break;
		default:
			break;
		}
		move();
	}
	
	private void move(){
		switch (dir){
			case LIFT:
				x-=SPEED;
				break;
			case RIGTH:
				x+=SPEED;
				break;
			case UP:
				y-=SPEED;
				break;
			case DOWN:
				y+=SPEED;
				break;
			default:
				break;
		}
		rec.x = this.x;
		rec.y = this.y;
		if(x > TankFrame.GAME_WIDTH || x < 0 || y > TankFrame.GAME_HEIGHT || y < 0)
			living = false;
	}

	public void die() {
		this.living = false;
		
	}
}
