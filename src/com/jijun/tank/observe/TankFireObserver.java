package com.jijun.tank.observe;

import java.io.Serializable;

public interface TankFireObserver extends Serializable {
    void actionOnFire(TankFireEvent e);
}
