package com.jijun.tank.abstractfactory;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.jijun.tank.Dir;
import com.jijun.tank.Group;

public abstract class BaseTank {

	public Group group = Group.BAD;
	public Dir dir = Dir.UP;
	public Rectangle rec = new Rectangle();
	
	public abstract void paint(Graphics g);

	public Group getGroup() {
		return this.group;
	}

	public abstract void die();

	public abstract int getX();

	public abstract int getY();
}
