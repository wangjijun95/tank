package com.jijun.tank;


public class Main {
	public static void main(String[] args) throws InterruptedException{
		TankFrame f = new TankFrame();
		int cont = Integer.parseInt((String)PropertyMgr.getObject("initTankCont"));
		for(int i = 1; i <= cont; i++){
			f.tanks.add(new Tank(50*i, 100, Dir.DOWN, Group.BAD, f));
		}
		while(true){
			Thread.sleep(25);
			f.repaint();
		}
	}
}
