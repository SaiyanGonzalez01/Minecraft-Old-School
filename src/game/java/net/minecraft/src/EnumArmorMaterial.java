package net.minecraft.src;

public enum EnumArmorMaterial {
	LEATHER(1, new int[] { 1, 3, 2, 1 }),
	CHAIN(2, new int[] { 2, 4, 3, 1 }),
	IRON(4, new int[] { 2, 6, 5, 2 }),
	GOLD(6, new int[] { 3, 7, 5, 2 }),
	DIAMOND(8, new int[] { 3, 8, 6, 3 });

	private final int durabilityMultiplier;
	private final int[] damageReduction;

	EnumArmorMaterial(int durabilityMultiplier, int[] damageReduction) {
		this.durabilityMultiplier = durabilityMultiplier;
		this.damageReduction = damageReduction;
	}

	public int getDurabilityMultiplier() {
		return this.durabilityMultiplier;
	}

	public int getDamageReductionAmount(int armorType) {
		return this.damageReduction[armorType];
	}
}
