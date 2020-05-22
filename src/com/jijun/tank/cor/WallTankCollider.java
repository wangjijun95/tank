package com.jijun.tank.cor;

import com.jijun.tank.Bullet;
import com.jijun.tank.Tank;
import com.jijun.tank.Wall;

public class WallTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Wall && o2 instanceof Tank){
            Wall b = (Wall)o1;
            Tank t = (Tank)o2;
            return collideWith(b,t);
        }else if(o1 instanceof Tank && o2 instanceof  Wall){
            collide(o2, o1);
        }
        return true;
    }

    private boolean collideWith(Wall wall, Tank tank) {
        if(tank.rec.intersects(wall.rec)) {
            tank.backXY();
        }
        return true;
    }
}
