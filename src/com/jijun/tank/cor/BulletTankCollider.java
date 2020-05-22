package com.jijun.tank.cor;

import com.jijun.tank.Bullet;
import com.jijun.tank.Explode;
import com.jijun.tank.GameModel;
import com.jijun.tank.Tank;

public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;
            return collideWith(b,t);
        }else if(o1 instanceof Tank && o2 instanceof  Bullet){
            collide(o2, o1);
        }
        return true;
    }

    private boolean collideWith(Bullet bullet, Tank tank) {
        if(tank.getGroup() == bullet.group) return true;
        if(bullet.rec.intersects(tank.rec)) {
            tank.die();
            bullet.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            GameModel.getInstance().add(new Explode(eX, eY));
            return false;
        }
        return true;
    }
}
