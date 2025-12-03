package net.minecraft.src;

import dev.colbster937.eaglercraft.rp.TexturePack;

import net.lax1dude.eaglercraft.opengl.ImageData;

public class ColorizerFoliage {
	private static final int[] foliageBuffer = new int[65536];

	public static int getFoliageColor(double var0, double var2) {
		var2 *= var0;
		int var4 = (int)((1.0D - var0) * 255.0D);
		int var5 = (int)((1.0D - var2) * 255.0D);
		return foliageBuffer[var5 << 8 | var4];
	}

	public static int func_21175_a() {
		return 6396257;
	}

	public static int func_21174_b() {
		return 8431445;
	}

	static {
		try {
			ImageData var0 = ImageData.loadImageFile(TexturePack.getResourceAsStream("/misc/foliagecolor.png")).swapRB();
			var0.getRGB(0, 0, 256, 256, foliageBuffer, 0, 256);
		} catch (Exception var1) {
			var1.printStackTrace();
		}

	}
}
