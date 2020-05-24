package com.jijun.tank.cor;

import java.io.Serializable;

public interface Collider extends Serializable {
    boolean collide(GameObject o1, GameObject o2);
}
