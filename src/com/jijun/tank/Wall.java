package com.jijun.tank;

import com.jijun.tank.cor.GameObject;

import java.awt.*;

public class Wall extends GameObject {

    int w, h;
    public Rectangle rec;
    public Wall(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rec = new Rectangle(x, y, w, h);
        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, w, h);
        g.setColor(c);
    }
}
