package com.jijun.tank;

import com.jijun.tank.observe.TankFireEvent;
import com.jijun.tank.observe.TankFireHandler;
import com.jijun.tank.observe.TankFireObserver;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame{

	public static final int GAME_WIDTH = 1080, GAME_HEIGHT = 700;

	GameModel gm = GameModel.getInstance();
	TankFireObserver tf = new TankFireHandler();

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
		gm.paint(g);
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
//					gm.getTank().fire();
					tf.actionOnFire(new TankFireEvent(gm.getTank()));
				default:
					break;
			}
			setMainTankDir();
		}

		public void setMainTankDir(){
			if(!bL && !bR && !bU && !bD){
				gm.getTank().setMoving(false);
			}else{
				gm.getTank().setMoving(true);
				if(bL) gm.getTank().setDir(Dir.LIFT);
				if(bR) gm.getTank().setDir(Dir.RIGTH);
				if(bU) gm.getTank().setDir(Dir.UP);
				if(bD) gm.getTank().setDir(Dir.DOWN);
			}

		}
	}
}
