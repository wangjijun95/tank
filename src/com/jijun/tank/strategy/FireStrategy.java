package com.jijun.tank.strategy;


import com.jijun.tank.Tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
	void fire(Tank t);
}
