package com.jijun.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.jijun.tank.cor.GameObject;
import com.jijun.tank.strategy.FireStrategy;

public class Tank extends GameObject {
	private int x=200, y=200;
	int oldX,oldY;
	Dir dir = Dir.DOWN;
	private static final int SPEED = 5;
	private boolean moving = true;
	public GameModel gm;
	FireStrategy fs;
	private boolean living = true;
	public static int WIDTH = ResourceMgr.goodTankU.getWidth();
	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
	private Group group ;
	public Rectangle rec = new Rectangle();
	
	private Random random = new Random();
	
	public Tank(int x, int y, Dir dir, Group group, GameModel gm) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.gm = gm;
		rec.x = this.x;
		rec.y = this.y;
		rec.width = WIDTH;
		rec.height = HEIGHT;
		
		if(group == Group.GOOD){
			try {
				String goodName = (String)PropertyMgr.getObject("goodFS");
				fs = (FireStrategy)Class.forName(goodName).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			String badFS = (String)PropertyMgr.getObject("badFS");
			try {
				fs = (FireStrategy)Class.forName(badFS).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public boolean isLiving() {
		return living;
	}

	public void setLiving(boolean living) {
		this.living = living;
	}

	public boolean isMoving() {
		return moving;
	}


	public void setMoving(boolean moving) {
		this.moving = moving;
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
	public static int getSpeed() {
		return SPEED;
	}
	public void paint(Graphics g) {
		if(!living) gm.objects.remove(this);
		switch (dir) {
		case LIFT:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL:ResourceMgr.badTankL, this.x, this.y, null);
			break;
		case RIGTH:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR:ResourceMgr.badTankR, this.x, this.y, null);
			break;
		case UP:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU:ResourceMgr.badTankU, this.x, this.y, null);
			break;
		case DOWN:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD:ResourceMgr.badTankD, this.x, this.y, null);
			break;
		default:
			break;
		}
		
		move();
		
		
	}

	private void move() {
		if(!moving){
			return;
		}

		oldX = x;
		oldY = y;
		switch (this.dir){
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


		if(this.x < 2){this.x = 2;}
		if(this.x > TankFrame.GAME_WIDTH-this.WIDTH-2){this.x = TankFrame.GAME_WIDTH - this.WIDTH-2;}
		if(this.y < 28){this.y = 28;}
		if(this.y > TankFrame.GAME_HEIGHT-this.HEIGHT-2){this.y = TankFrame.GAME_HEIGHT- this.HEIGHT -2;}
		
		rec.x = x;
		rec.y = y;


		if(this.group == Group.BAD && random.nextInt(100) > 95) {
			this.fire();
		}
		if(this.group == Group.BAD && random.nextInt(100) > 95){
			randomDir();
		}
		
	}

	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}

	public void fire() {
		fs.fire(this);
	}

	public void die() {
		this.living = false;
	}


	public void backXY() {
		x = oldX;
		y = oldY;
	}
}
