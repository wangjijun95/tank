package com.jijun.tank.cor;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{
    List<Collider> colls = new LinkedList<>();

    public ColliderChain(){
        add(new BulletTankCollider());
        add(new TankTankCollider());
        add(new WallBulletCollider());
        add(new WallTankCollider());
    }

    public void add(Collider c){
        colls.add(c);
    }

    public void remove(Collider c){
        colls.remove(c);
    }

    public boolean collide(GameObject o1, GameObject o2){
        for (int i = 0; i < colls.size(); i++) {
           if(!colls.get(i).collide(o1,o2)){
               return false;
           };
        }
        return true;
    }
}
