package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.lax1dude.eaglercraft.internal.vfs2.VFile2;

public class RegionFileCache {
	private static final Map field_22195_a = new HashMap();

	public static synchronized RegionFile func_22193_a(VFile2 var0, int var1, int var2) {
		VFile2 var3 = new VFile2(var0, "region");
		VFile2 var4 = new VFile2(var3, "r." + (var1 >> 5) + "." + (var2 >> 5) + ".mcr");
		Reference var5 = (Reference)field_22195_a.get(var4);
		RegionFile var6;

		if(var5 != null) {
			var6 = (RegionFile)var5.get();
			if(var6 != null) {
				return var6;
			}
		}

		if(field_22195_a.size() >= 256) {
			func_22192_a();
		}

		var6 = new RegionFile(var4);
		field_22195_a.put(var4, new SoftReference(var6));
		return var6;
	}

	public static synchronized void func_22192_a() {
		saveRegionFiles();

		field_22195_a.clear();
	}

	public static synchronized void saveRegionFiles() {
		Iterator var0 = field_22195_a.values().iterator();

		while(var0.hasNext()) {
			Reference var1 = (Reference)var0.next();

			try {
				RegionFile var2 = (RegionFile)var1.get();
				if(var2 != null) {
					var2.func_22196_b();
				}
			} catch (IOException var3) {
				var3.printStackTrace();
			}
		}
	}

	public static int func_22191_b(VFile2 var0, int var1, int var2) {
		RegionFile var3 = func_22193_a(var0, var1, var2);
		return var3.func_22209_a();
	}

	public static DataInputStream func_22194_c(VFile2 var0, int var1, int var2) {
		RegionFile var3 = func_22193_a(var0, var1, var2);
		return var3.func_22210_a(var1 & 31, var2 & 31);
	}

	public static DataOutputStream func_22190_d(VFile2 var0, int var1, int var2) {
		RegionFile var3 = func_22193_a(var0, var1, var2);
		return var3.func_22205_b(var1 & 31, var2 & 31);
	}
}
