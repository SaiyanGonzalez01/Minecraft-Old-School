package net.minecraft.src;

public class RecipesCrafting {
	public void addRecipes(CraftingManager var1) {
		var1.addRecipe(new ItemStack(Block.crate), new Object[]{"###", "# #", "###", Character.valueOf('#'), Block.planks});
		var1.addRecipe(new ItemStack(Block.stoneOvenIdle), new Object[]{"###", "# #", "###", Character.valueOf('#'), Block.cobblestone});
		var1.addRecipe(new ItemStack(Block.workbench), new Object[]{"##", "##", Character.valueOf('#'), Block.planks});
		var1.addRecipe(new ItemStack(Block.sandStone), new Object[]{"##", "##", Character.valueOf('#'), Block.sand});
		var1.addRecipe(new ItemStack(Block.copperFurnaceIdle), new Object[]{"XXX", "X X", "###", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.ingotCopper});
		var1.addRecipe(new ItemStack(Block.anvil), new Object[]{"###", " X ", "XXX", Character.valueOf('#'), Block.blockSteel, Character.valueOf('X'), Item.ingotIron});
	}
}
