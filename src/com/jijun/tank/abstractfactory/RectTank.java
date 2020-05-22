package com.jijun.tank.abstractfactory;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.jijun.tank.Audio;
import com.jijun.tank.Bullet;
import com.jijun.tank.Dir;
import com.jijun.tank.strategy.FireStrategy;
import com.jijun.tank.Group;
import com.jijun.tank.ResourceMgr;
import com.jijun.tank.TankFrame;

public class RectTank extends BaseTank {
	private int x=200, y=200;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 5;
	private boolean moving = true;
	TankFrame t;
	FireStrategy fs;
	private boolean living = true;
	public static int WIDTH = ResourceMgr.goodTankU.getWidth();
	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
	private Group group = Group.GOOD;
	Rectangle rec = new Rectangle();
	
	private Random random = new Random();
	
	public RectTank(int x, int y, Dir dir, Group group, TankFrame t) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.t = t;
		rec.x = this.x;
		rec.y = this.y;
		rec.width = WIDTH;
		rec.height = HEIGHT;
		
//		if(group == Group.GOOD){
//			try {
//				String goodName = (String)PropertyMgr.getObject("goodFS");
//				fs = (FireStrategy)Class.forName(goodName).newInstance();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}else{
//			String badFS = (String)PropertyMgr.getObject("badFS");
//			try {
//				fs = (FireStrategy)Class.forName(badFS).newInstance();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
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
		if(!living) t.tanks.remove(this);
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
//		fs.fire(this);
		int bX = this.getX() + this.WIDTH/2 - Bullet.WIDTH/2;
		int bY = this.getY() + this.HEIGHT/2 - Bullet.HEIGHT/2;
		Dir[] dirs = Dir.values();
		for(Dir d : dirs){
			t.gf.createBullet(bX, bY, d, this.getGroup(), this.t);
		}
		if(this.getGroup() == Group.GOOD){new Thread(()->new Audio("audio/tank_fire.wav").play()).start();}
	}

	public void die() {
		this.living = false;
	}

	
	
}
