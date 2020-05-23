package com.jijun.tank.observe;

public class TankFireHandler implements TankFireObserver {
    @Override
    public void actionOnFire(TankFireEvent e) {
        e.getTank().fire();
    }
}
