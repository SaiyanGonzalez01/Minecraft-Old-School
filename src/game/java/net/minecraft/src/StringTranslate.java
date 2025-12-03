package net.minecraft.src;

import java.io.IOException;
import java.util.Properties;

import dev.colbster937.eaglercraft.rp.TexturePack;

import net.lax1dude.eaglercraft.HString;

public class StringTranslate {
	private static StringTranslate instance = new StringTranslate();
	private Properties translateTable = new Properties();

	private StringTranslate() {
		try {
			this.translateTable.load(TexturePack.getResourceAsStream("/lang/en_US.lang"));
		} catch (IOException var2) {
			var2.printStackTrace();
		}

	}

	public static StringTranslate getInstance() {
		return instance;
	}

	public String translateKey(String var1) {
		return this.translateTable.getProperty(var1, var1);
	}

	public String translateKeyFormat(String var1, Object... var2) {
		String var3 = this.translateTable.getProperty(var1, var1);
		return HString.format(var3, var2);
	}

	public String translateNamedKey(String var1) {
		return this.translateTable.getProperty(var1 + ".name", "");
	}

	public boolean hasTranslateKey(String var0) {
		return this.translateTable.containsKey(var0);
	}
}
