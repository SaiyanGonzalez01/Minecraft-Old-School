package net.minecraft.src;

public class ItemPlanks extends ItemBlock {
	public ItemPlanks(int var1) {
		super(var1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getIconFromDamage(int var1) {
		return Block.planks.getBlockTextureFromSideAndMetadata(2, BlockPlanks.func_21034_c(var1));
	}

	public int getPlacedBlockMetadata(int var1) {
		return var1;
	}

	public String getItemNameIS(ItemStack var1) {
		return super.getItemName() + "." + ItemDye.dyeColors[BlockPlanks.func_21034_c(var1.getItemDamage())];
	}
}
