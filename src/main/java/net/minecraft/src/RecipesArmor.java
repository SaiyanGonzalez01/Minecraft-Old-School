package net.minecraft.src;

public class RecipesArmor {
	private String[][] recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
	private Object[][] recipeItems = new Object[][]{{Item.leather, Item.chainlink, Item.ingotIron, Item.diamond, Item.ingotGold, Block.obsidian}, {Item.helmetLeather, Item.helmetChain, Item.helmetSteel, Item.helmetDiamond, Item.helmetGold, Item.helmetObsidian}, {Item.plateLeather, Item.plateChain, Item.plateSteel, Item.plateDiamond, Item.plateGold, Item.plateObsidian}, {Item.legsLeather, Item.legsChain, Item.legsSteel, Item.legsDiamond, Item.legsGold, Item.legsObsidian}, {Item.bootsLeather, Item.bootsChain, Item.bootsSteel, Item.bootsDiamond, Item.bootsGold, Item.bootsObsidian}};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('X'), var3});
			}
		}

		var1.addRecipe(new ItemStack(Item.helmetObsidian, 1), new Object[]{" Y ", "#X#", " # ", Character.valueOf('#'), Block.obsidian, Character.valueOf('X'), Item.helmetDiamond, Character.valueOf('Y'), Item.runeUpgrade});
		var1.addRecipe(new ItemStack(Item.plateObsidian, 1), new Object[]{" Y ", "#X#", " # ", Character.valueOf('#'), Block.obsidian, Character.valueOf('X'), Item.plateDiamond, Character.valueOf('Y'), Item.runeUpgrade});
		var1.addRecipe(new ItemStack(Item.legsObsidian, 1), new Object[]{" Y ", "#X#", " # ", Character.valueOf('#'), Block.obsidian, Character.valueOf('X'), Item.legsDiamond, Character.valueOf('Y'), Item.runeUpgrade});
		var1.addRecipe(new ItemStack(Item.bootsObsidian, 1), new Object[]{" Y ", "#X#", " # ", Character.valueOf('#'), Block.obsidian, Character.valueOf('X'), Item.bootsDiamond, Character.valueOf('Y'), Item.runeUpgrade});

	}
}
