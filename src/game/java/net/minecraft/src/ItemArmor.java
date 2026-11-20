package net.minecraft.src;

public class ItemArmor extends Item {
	private static final int[] maxDamageArray = new int[]{11, 16, 15, 13};
	public final EnumArmorMaterial armorMaterial;
	public final int armorType;
	public final int damageReduceAmount;
	public final int renderIndex;

	public ItemArmor(int var1, EnumArmorMaterial armorMaterial, int var3, int var4) {
		super(var1);
		this.armorMaterial = armorMaterial;
		this.armorType = var4;
		this.renderIndex = var3;
		this.damageReduceAmount = armorMaterial.getDamageReductionAmount(var4);
		this.maxDamage = maxDamageArray[var4] * 3 * armorMaterial.getDurabilityMultiplier();
		this.maxStackSize = 1;
	}
}
