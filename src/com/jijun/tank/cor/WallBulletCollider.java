package com.jijun.tank.cor;

import com.jijun.tank.Bullet;
import com.jijun.tank.Wall;

public class WallBulletCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Wall && o2 instanceof Bullet){
            Wall b = (Wall)o1;
            Bullet t = (Bullet)o2;
            return collideWith(b,t);
        }else if(o1 instanceof Bullet && o2 instanceof  Wall){
            collide(o2, o1);
        }
        return true;
    }

    private boolean collideWith(Wall wall, Bullet bullet) {
        if(bullet.rec.intersects(wall.rec)) {
            bullet.die();
        }
        return true;
    }
}
