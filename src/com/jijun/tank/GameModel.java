package com.jijun.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    public List<Bullet> bullets = new ArrayList<>();
    public List<Tank> tanks = new ArrayList<>();
    public List<Explode> explodes = new ArrayList<>();
    Tank myTank = new Tank(200, 300, Dir.DOWN, Group.GOOD, this);

    public GameModel(){
        int cont = Integer.parseInt((String)PropertyMgr.getObject("initTankCont"));
        for(int i = 1; i <= cont; i++){
            tanks.add(new Tank(50*i, 100, Dir.DOWN, Group.BAD, this));
        }
    }

    public Tank getTank(){
        return myTank;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹数量：" + bullets.size(), 20, 60);
        g.drawString("坦克数量：" + tanks.size(), 20, 80);
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
}
