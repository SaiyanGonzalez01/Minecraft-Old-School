package net.minecraft.src;

public class ItemMolten extends Item {
	private final Item ingot;

	public ItemMolten(int var1, Item var2) {
		super(var1);
		this.ingot = var2;
	}

	public Item getIngot() {
		return this.ingot;
	}
}
