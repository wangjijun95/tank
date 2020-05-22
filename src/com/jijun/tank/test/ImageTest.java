package com.jijun.tank.test;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.jijun.tank.ResourceMgr;

public class ImageTest {
	@Test
	public void test() {
		
		try {
			BufferedImage image = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
			assertNotNull(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
