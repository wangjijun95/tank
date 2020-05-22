package com.jijun.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	private static final PropertyMgr instance = new PropertyMgr();
	static Properties prop = new Properties();
	static{
		try {
			prop.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private PropertyMgr(){}
	
	public static PropertyMgr getPropertyMgr(){return instance;}
	
	public static Object getObject(String key){
		return prop.get(key);
	}
	
	public static void main(String[] args) {
		System.out.println(getObject("initTankCont"));
	}
}
