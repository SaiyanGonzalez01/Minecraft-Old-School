package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public class BlastFurnaceRecipes {
	private static final BlastFurnaceRecipes smeltingBase = new BlastFurnaceRecipes();
	private Map smeltingList = new HashMap();

	public static final BlastFurnaceRecipes smelting() {
		return smeltingBase;
	}

	private BlastFurnaceRecipes() {
		this.addSmelting(Block.oreCopper.blockID, new ItemStack(Item.moltenCopper));
		this.addSmelting(Block.oreIron.blockID, new ItemStack(Item.moltenIron));
		this.addSmelting(Block.oreGold.blockID, new ItemStack(Item.moltenGold));
		this.addSmelting(Block.oreDiamond.blockID, new ItemStack(Item.diamond));
		this.addSmelting(Block.sand.blockID, new ItemStack(Block.glass));
		this.addSmelting(Block.cobblestone.blockID, new ItemStack(Block.stone));
		this.addSmelting(Item.ingotCopper.shiftedIndex, new ItemStack(Item.moltenCopper));
		this.addSmelting(Item.ingotIron.shiftedIndex, new ItemStack(Item.moltenIron));
		this.addSmelting(Item.ingotGold.shiftedIndex, new ItemStack(Item.moltenGold));
		this.addSmelting(Block.stone.blockID, new ItemStack(Block.stonePolished));
	}

	public void addSmelting(int var1, ItemStack var2) {
		this.smeltingList.put(Integer.valueOf(var1), var2);
	}

	public ItemStack getSmeltingResult(int var1) {
		return (ItemStack)this.smeltingList.get(Integer.valueOf(var1));
	}
}
