package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import net.lax1dude.eaglercraft.internal.vfs2.VFile2;

public class SaveHandler implements ISaveHandler {
	private static final Logger field_22156_a = Logger.getLogger("Minecraft");
	private final VFile2 field_22155_b;
	private final VFile2 field_22158_c;
	private final long field_22157_d = System.currentTimeMillis();

	public SaveHandler(VFile2 var1, String var2, boolean var3) {
		this.field_22155_b = new VFile2(var1, var2);
		this.field_22158_c = new VFile2(this.field_22155_b, "players");

		this.func_22154_d();
	}

	private void func_22154_d() {
		try {
			VFile2 var1 = new VFile2(this.field_22155_b, "session.lock");
			DataOutputStream var2 = new DataOutputStream(var1.getOutputStream());

			try {
				var2.writeLong(this.field_22157_d);
			} finally {
				var2.close();
			}

		} catch (IOException var7) {
			var7.printStackTrace();
			throw new RuntimeException("Failed to check session lock, aborting");
		}
	}

	protected VFile2 func_22153_a() {
		return this.field_22155_b;
	}

	public void func_22150_b() {
		try {
			VFile2 var1 = new VFile2(this.field_22155_b, "session.lock");
			DataInputStream var2 = new DataInputStream(var1.getInputStream());

			try {
				if(var2.readLong() != this.field_22157_d) {
					throw new MinecraftException("The save is being accessed from another location, aborting");
				}
			} finally {
				var2.close();
			}

		} catch (IOException var7) {
			throw new MinecraftException("Failed to check session lock, aborting");
		}
	}

	public IChunkLoader func_22149_a(WorldProvider var1) {
		if(var1 instanceof WorldProviderHell) {
			VFile2 var2 = new VFile2(this.field_22155_b, "DIM-1");
			return new ChunkLoader(var2, true);
		} else {
			return new ChunkLoader(this.field_22155_b, true);
		}
	}

	public WorldInfo func_22151_c() {
		VFile2 var1 = new VFile2(this.field_22155_b, "level.dat");
		if(var1.exists()) {
			try {
				NBTTagCompound var2 = CompressedStreamTools.func_1138_a(var1.getInputStream());
				NBTTagCompound var3 = var2.getCompoundTag("Data");
				return new WorldInfo(var3);
			} catch (Exception var4) {
				var4.printStackTrace();
			}
		}

		return null;
	}

	public void func_22148_a(WorldInfo var1, List var2) {
		NBTTagCompound var3 = var1.func_22305_a(var2);
		NBTTagCompound var4 = new NBTTagCompound();
		var4.setTag("Data", var3);

		try {
			VFile2 var5 = new VFile2(this.field_22155_b, "level.dat_new");
			VFile2 var6 = new VFile2(this.field_22155_b, "level.dat_old");
			VFile2 var7 = new VFile2(this.field_22155_b, "level.dat");
			CompressedStreamTools.writeGzippedCompoundToOutputStream(var4, var5.getOutputStream());
			if(var6.exists()) {
				var6.delete();
			}

			var7.renameTo(var6);
			if(var7.exists()) {
				var7.delete();
			}

			var5.renameTo(var7);
			if(var5.exists()) {
				var5.delete();
			}
		} catch (Exception var8) {
			var8.printStackTrace();
		}

	}

	public void func_22152_a(WorldInfo var1) {
		NBTTagCompound var2 = var1.func_22299_a();
		NBTTagCompound var3 = new NBTTagCompound();
		var3.setTag("Data", var2);

		try {
			VFile2 var4 = new VFile2(this.field_22155_b, "level.dat_new");
			VFile2 var5 = new VFile2(this.field_22155_b, "level.dat_old");
			VFile2 var6 = new VFile2(this.field_22155_b, "level.dat");
			CompressedStreamTools.writeGzippedCompoundToOutputStream(var3, var4.getOutputStream());
			if(var5.exists()) {
				var5.delete();
			}

			var6.renameTo(var5);
			if(var6.exists()) {
				var6.delete();
			}

			var4.renameTo(var6);
			if(var4.exists()) {
				var4.delete();
			}
		} catch (Exception var7) {
			var7.printStackTrace();
		}

	}
}
