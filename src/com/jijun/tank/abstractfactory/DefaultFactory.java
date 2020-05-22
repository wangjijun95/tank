//package com.jijun.tank.abstractfactory;
//
//import com.jijun.tank.Bullet;
//import com.jijun.tank.Dir;
//import com.jijun.tank.Explode;
//import com.jijun.tank.Group;
//import com.jijun.tank.Tank;
//import com.jijun.tank.TankFrame;
//
//public class DefaultFactory extends GameFactory{
//
//	@Override
//	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
//		return new Tank(x, y, dir, group, tf);
//	}
//
//	@Override
//	public BaseExplode createExplode(int x, int y, TankFrame tf) {
//		return new Explode(x, y, tf);
//	}
//
//	@Override
//	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
//		return new Bullet(x, y, dir, group, tf);
//	}
//
//}
