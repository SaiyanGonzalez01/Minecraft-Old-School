package net.minecraft.src;

import java.util.List;

public class WorldInfo {
	private long randomSeed;
	private int spawnX;
	private int spawnY;
	private int spawnZ;
	private long worldTime;
	private long field_22315_f;
	private long sizeOnDisk;
	private NBTTagCompound field_22313_h;
	private int field_22312_i;
	private String levelName;
	private int saveVersion;

	public WorldInfo(NBTTagCompound var1) {
		this.randomSeed = var1.getLong("RandomSeed");
		this.spawnX = var1.getInteger("SpawnX");
		this.spawnY = var1.getInteger("SpawnY");
		this.spawnZ = var1.getInteger("SpawnZ");
		this.worldTime = var1.getLong("Time");
		this.field_22315_f = var1.getLong("LastPlayed");
		this.sizeOnDisk = var1.getLong("SizeOnDisk");
		this.levelName = var1.getString("LevelName");
		this.saveVersion = var1.getInteger("version");
		if(var1.hasKey("Player")) {
			this.field_22313_h = var1.getCompoundTag("Player");
			this.field_22312_i = this.field_22313_h.getInteger("Dimension");
		}

	}

	public WorldInfo(long var1, String var3) {
		this.randomSeed = var1;
		this.levelName = var3;
	}

	public WorldInfo(WorldInfo var1) {
		this.randomSeed = var1.randomSeed;
		this.spawnX = var1.spawnX;
		this.spawnY = var1.spawnY;
		this.spawnZ = var1.spawnZ;
		this.worldTime = var1.worldTime;
		this.field_22315_f = var1.field_22315_f;
		this.sizeOnDisk = var1.sizeOnDisk;
		this.field_22313_h = var1.field_22313_h;
		this.field_22312_i = var1.field_22312_i;
		this.levelName = var1.levelName;
		this.saveVersion = var1.saveVersion;
	}

	public NBTTagCompound func_22299_a() {
		NBTTagCompound var1 = new NBTTagCompound();
		this.func_22291_a(var1, this.field_22313_h);
		return var1;
	}

	public NBTTagCompound func_22305_a(List var1) {
		NBTTagCompound var2 = new NBTTagCompound();
		EntityPlayer var3 = null;
		NBTTagCompound var4 = null;
		if(var1.size() > 0) {
			var3 = (EntityPlayer)var1.get(0);
		}

		if(var3 != null) {
			var4 = new NBTTagCompound();
			var3.writeToNBT(var4);
		}

		this.func_22291_a(var2, var4);
		return var2;
	}

	private void func_22291_a(NBTTagCompound var1, NBTTagCompound var2) {
		var1.setLong("RandomSeed", this.randomSeed);
		var1.setInteger("SpawnX", this.spawnX);
		var1.setInteger("SpawnY", this.spawnY);
		var1.setInteger("SpawnZ", this.spawnZ);
		var1.setLong("Time", this.worldTime);
		var1.setLong("SizeOnDisk", this.sizeOnDisk);
		var1.setLong("LastPlayed", System.currentTimeMillis());
		var1.setString("LevelName", this.levelName);
		var1.setInteger("version", this.saveVersion);
		if(var2 != null) {
			var1.setCompoundTag("Player", var2);
		}

	}

	public long getRandomSeed() {
		return this.randomSeed;
	}

	public int func_22293_c() {
		return this.spawnX;
	}

	public int func_22295_d() {
		return this.spawnY;
	}

	public int func_22300_e() {
		return this.spawnZ;
	}

	public long getWorldTime() {
		return this.worldTime;
	}

	public long func_22306_g() {
		return this.sizeOnDisk;
	}

	public NBTTagCompound func_22303_h() {
		return this.field_22313_h;
	}

	public int func_22290_i() {
		return this.field_22312_i;
	}

	public void func_22294_a(int var1) {
		this.spawnX = var1;
	}

	public void func_22308_b(int var1) {
		this.spawnY = var1;
	}

	public void func_22298_c(int var1) {
		this.spawnZ = var1;
	}

	public void setWorldTime(long var1) {
		this.worldTime = var1;
	}

	public void func_22297_b(long var1) {
		this.sizeOnDisk = var1;
	}

	public void func_22309_a(NBTTagCompound var1) {
		this.field_22313_h = var1;
	}

	public void func_22292_a(int var1, int var2, int var3) {
		this.spawnX = var1;
		this.spawnY = var2;
		this.spawnZ = var3;
	}

	public String getWorldName() {
		return this.levelName;
	}

	public void func_22287_a(String var1) {
		this.levelName = var1;
	}

	public int func_22296_k() {
		return this.saveVersion;
	}

	public void func_22289_d(int var1) {
		this.saveVersion = var1;
	}

	public long func_22301_l() {
		return this.field_22315_f;
	}
}
