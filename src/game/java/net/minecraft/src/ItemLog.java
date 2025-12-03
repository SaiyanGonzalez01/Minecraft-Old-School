package net.minecraft.src;

public class ItemLog extends ItemBlock {
	public ItemLog(int var1) {
		super(var1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getIconIndex(ItemStack var1) {
		return Block.wood.getBlockTextureFromSideAndMetadata(2, var1.getItemDamage());
	}

	public int func_21012_a(int var1) {
		return var1;
	}
}
