package com.jijun.tank.strategy;

import com.jijun.tank.Audio;
import com.jijun.tank.Bullet;
import com.jijun.tank.Dir;
import com.jijun.tank.Group;
import com.jijun.tank.Tank;

public class FourDirFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank t) {
		int bX = t.getX() + t.WIDTH/2 - Bullet.WIDTH/2;
		int bY = t.getY() + t.HEIGHT/2 - Bullet.HEIGHT/2;
		Dir[] dirs = Dir.values();
		for(Dir d : dirs){
			new Bullet(bX, bY, d, t.getGroup(), t.gm);
		}
		if(t.getGroup() == Group.GOOD){new Thread(()->new Audio("audio/tank_fire.wav").play()).start();}
	}

}
