package dev.colbster937.eaglercraft;

import net.lax1dude.eaglercraft.EagRuntime;

public class EaglercraftVersion {
	public static final String STORAGE_KEY = "_dev_colbster937_eaglercraft_b1.3-01_";
	public static final String EAGLER_VERSION = "25w46a";
	public static final String MINECRAFT_VERSION = "b1.3_01";
	public static final String PROJECT_GITHUB = "https://github.com/SaiyanGonzalez01/Minecraft-Old-School";
	public static final String PROJECT_AUTHOR = "Colbster937";
	public static final String PROJECT_BRAND = "colbster";
	public static final String LANG_CONTEXT = "eaglercraft";
	
	public static final String[] getTitleString() {
		return new String[] {
			"Beta 1.3_01 Port by " + PROJECT_AUTHOR,
			"Eaglercraft " + EAGLER_VERSION + " [" + EagRuntime.getPlatformType() + "]"
		};
	}
}
