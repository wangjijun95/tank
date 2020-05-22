package com.jijun.tank;

import com.jijun.tank.cor.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    public List<GameObject> objects = new ArrayList<>();
    Tank myTank = new Tank(200, 300, Dir.DOWN, Group.GOOD, this);
    ColliderChain collide = new ColliderChain();

    public GameModel(){
        int cont = Integer.parseInt((String)PropertyMgr.getObject("initTankCont"));
        for(int i = 1; i <= cont; i++){
            objects.add(new Tank(50*i, 100, Dir.DOWN, Group.BAD, this));
        }

    }

    public Tank getTank(){
        return myTank;
    }

    public void paint(Graphics g) {
//        Color c = g.getColor();
//        g.setColor(Color.white);
//        g.drawString("子弹数量：" + bullets.size(), 20, 60);
//        g.drawString("坦克数量：" + tanks.size(), 20, 80);
//        g.drawString("爆炸数量：" + explodes.size(), 20, 100);
//        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
        //添加碰撞检测
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                collide.collide(objects.get(i),objects.get(j));
            }
        }
    }
}
