package net.minecraft.src;

import java.io.IOException;

import net.lax1dude.eaglercraft.internal.PlatformApplication;
import net.lax1dude.eaglercraft.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.internal.vfs2.VFile2;

public class ScreenShotHelper {
	public static String saveScreenshot(VFile2 var0, int var1, int var2) {
		return "Saved screenshot as " + PlatformApplication.saveScreenshot();
	}

	public ScreenShotHelper(VFile2 var1, int var2, int var3, int var4) throws IOException {

	}

	public void func_21189_a(ByteBuffer var1, int var2, int var3, int var4, int var5) throws IOException {
		
	}

	public void func_21191_a() throws IOException {
		
	}

	public String func_21190_b() throws IOException {
		return "Saved screenshot as " + PlatformApplication.saveScreenshot();
	}
}
