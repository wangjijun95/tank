package com.jijun.tank.cor;

import com.jijun.tank.Bullet;
import com.jijun.tank.Explode;
import com.jijun.tank.Tank;

public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank tank1 = (Tank)o1;
            Tank tank2 = (Tank)o2;
            if(tank1.rec.intersects(tank2.rec)){
                tank1.backXY();
                tank2.backXY();
            }
        }
        return true;
    }

}
