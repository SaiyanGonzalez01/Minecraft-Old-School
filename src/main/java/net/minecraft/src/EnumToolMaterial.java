package net.minecraft.src;

public enum EnumToolMaterial {
	WOOD(0, 60, 2.0F, 0),
	STONE(1, 132, 4.0F, 1),
	IRON(2, 250, 6.0F, 2),
	EMERALD(3, 1562, 8.0F, 3),
	GOLD(1, 500, 12.0F, 1),
	UPWOOD(0, 80, 2.5F, 0),
	UPSTONE(1, 162, 4.5F, 1),
	UPIRON(2, 300, 6.5F, 2),
	UPEMERALD(3, 1720, 8.5F, 3),
	UPGOLD(1, 550, 12.5F, 1),
	OBSIDIAN(4, 2562, 13.0F, 4),
	UPOBSIDIAN(5, 2864, 14.0F, 5);
	
	private final int harvestLevel;
	private final int maxUses;
	private final float efficiencyOnProperMaterial;
	private final int damageVsEntity;

	private EnumToolMaterial(int var3, int var4, float var5, int var6) {
		this.harvestLevel = var3;
		this.maxUses = var4;
		this.efficiencyOnProperMaterial = var5;
		this.damageVsEntity = var6;
	}

	public int getMaxUses() {
		return this.maxUses;
	}

	public float getEfficiencyOnProperMaterial() {
		return this.efficiencyOnProperMaterial;
	}

	public int getDamageVsEntity() {
		return this.damageVsEntity;
	}

	public int getHarvestLevel() {
		return this.harvestLevel;
	}
}
