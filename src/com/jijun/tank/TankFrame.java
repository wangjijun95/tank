package com.jijun.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.jijun.tank.abstractfactory.BaseBullet;
import com.jijun.tank.abstractfactory.BaseExplode;
import com.jijun.tank.abstractfactory.BaseTank;
import com.jijun.tank.abstractfactory.DefaultFactory;
import com.jijun.tank.abstractfactory.GameFactory;
import com.jijun.tank.abstractfactory.RectFactory;

public class TankFrame extends Frame{

	Tank myTank = new Tank(200, 300, Dir.DOWN, Group.GOOD, this);
	public List<BaseBullet> bullets = new ArrayList<>();
	public List<BaseTank> tanks = new ArrayList<>();
	public List<BaseExplode> explodes = new ArrayList<>();
	public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

	public GameFactory gf = new RectFactory();

	public TankFrame(){
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		addKeyListener(new MyKeyListener());
	}

	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.white);
		g.drawString("子弹数量：" + bullets.size(), 20, 50);
		g.drawString("敌人数量：" + tanks.size(), 20, 80);
		g.drawString("爆炸数量：" + explodes.size(), 20, 100);
		g.setColor(c);

		myTank.paint(g);
		for(int i=0; i<bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		for(int i=0; i<tanks.size(); i++){
			tanks.get(i).paint(g);
		}
		for(int i=0; i<bullets.size(); i++){
			for(int j=0; j<tanks.size(); j++)
				bullets.get(i).collideWith(tanks.get(j));
		}
		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
	}

	class MyKeyListener extends KeyAdapter {
		boolean bL = false;
		boolean bR = false;
		boolean bU = false;
		boolean bD = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
				case KeyEvent.VK_LEFT:
					bL = true;
					break;
				case KeyEvent.VK_RIGHT:
					bR = true;
					break;
				case KeyEvent.VK_UP:
					bU = true;
					break;
				case KeyEvent.VK_DOWN:
					bD = true;
					break;
				default:
					break;
			}
			setMainTankDir();
			new Thread(()->new Audio("audio/tank_move.wav").play()).start();
		}
		@Override
		public void keyReleased(KeyEvent e) {
//			System.out.println("key released");
			int key = e.getKeyCode();
			switch (key) {
				case KeyEvent.VK_LEFT:
					bL = false;
					break;
				case KeyEvent.VK_RIGHT:
					bR = false;
					break;
				case KeyEvent.VK_UP:
					bU = false;
					break;
				case KeyEvent.VK_DOWN:
					bD = false;
					break;
				case KeyEvent.VK_CONTROL:
					myTank.fire();
				default:
					break;
			}
			setMainTankDir();
		}

		public void setMainTankDir(){
			if(!bL && !bR && !bU && !bD){
				myTank.setMoving(false);
			}else{
				myTank.setMoving(true);
				if(bL) myTank.setDir(Dir.LIFT);
				if(bR) myTank.setDir(Dir.RIGTH);
				if(bU) myTank.setDir(Dir.UP);
				if(bD) myTank.setDir(Dir.DOWN);
			}

		}
	}
}
