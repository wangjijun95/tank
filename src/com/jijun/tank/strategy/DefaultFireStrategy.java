package com.jijun.tank.strategy;

import com.jijun.tank.Audio;
import com.jijun.tank.Bullet;
import com.jijun.tank.Group;
import com.jijun.tank.Tank;

public class DefaultFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank t) {
		int bX = t.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = t.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		new Bullet(bX, bY, t.getDir(), t.getGroup());
		if(t.getGroup() == Group.GOOD){new Thread(()->new Audio("audio/tank_fire.wav").play()).start();}
	}

}
