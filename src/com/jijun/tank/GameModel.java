package com.jijun.tank;

import com.jijun.tank.cor.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel{
    private static GameModel INSTANCE = new GameModel();
    static{
        INSTANCE.init();
    }
    private List<GameObject> objects = new ArrayList<>();
    Tank myTank;
    ColliderChain collide = new ColliderChain();

    private void init(){
        myTank = new Tank(200, 300, Dir.DOWN, Group.GOOD);
        // 初始化墙
        new Wall(150, 250, 200, 50);
        new Wall(550, 250, 200, 50);
        new Wall(300, 400, 50, 200);
        new Wall(550, 400, 50, 200);
        int cont = Integer.parseInt((String)PropertyMgr.getObject("initTankCont"));
        for(int i = 1; i <= cont; i++){
            new Tank(60*i, 100, Dir.DOWN, Group.BAD);
        }
    }

    private GameModel(){


    }
    public static GameModel getInstance() {
        return INSTANCE;
    }

    public void add(GameObject go){
        objects.add(go);
    }
    public void remove(GameObject go){
        objects.remove(go);
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

    public void save() {
        File f = new File("d:/myTank.data");
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(myTank);
            oos.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load() {
        File f = new File("d:/myTank.data");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(f));
            myTank = (Tank)ois.readObject();
            objects = (List)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
