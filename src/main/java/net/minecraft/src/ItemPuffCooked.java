package net.minecraft.src;

public class ItemPuffCooked extends ItemFood {
	public ItemPuffCooked(int var1, int var2, boolean var3, int var4) {
		super(var1, var2, var3);
		this.healAmount = var2;
		this.maxStackSize = var4;
	}
}
