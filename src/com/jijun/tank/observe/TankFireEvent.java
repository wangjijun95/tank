package com.jijun.tank.observe;

import com.jijun.tank.Tank;
import com.jijun.tank.cor.GameObject;

public class TankFireEvent {
    Tank tank;

    public TankFireEvent(Tank tank){
        this.tank = tank;
    }

    public Tank getTank(){
        return this.tank;
    }
}
