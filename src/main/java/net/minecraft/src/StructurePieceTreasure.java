package net.minecraft.src;

public class StructurePieceTreasure extends WeightedRandomChoice {
	public int itemID;
	public int itemMetadata;
	public int minItemStack;
	public int maxItemStack;

	public StructurePieceTreasure(int var1, int var2, int var3, int var4, int var5) {
		super(var5);
		this.itemID = var1;
		this.itemMetadata = var2;
		this.minItemStack = var3;
		this.maxItemStack = var4;
	}
}
