package net.minecraft.src;

public class RecipesArmor {
	private String[][] recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
	private Object[][] recipeItems = new Object[][]{{Item.leather, Block.fire, Item.ingotIron, Item.diamond, Item.ingotGold}, {Item.helmetLeather, Item.helmetChain, Item.helmetSteel, Item.helmetDiamond, Item.helmetGold}, {Item.plateLeather, Item.plateChain, Item.plateSteel, Item.plateDiamond, Item.plateGold}, {Item.legsLeather, Item.legsChain, Item.legsSteel, Item.legsDiamond, Item.legsGold}, {Item.bootsLeather, Item.bootsChain, Item.bootsSteel, Item.bootsDiamond, Item.bootsGold}};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('X'), var3});
			}
		}

		var1.addRecipe(new ItemStack(Item.amulet, 1), new Object[]{"X X", "X X", " # ", Character.valueOf('X'), Item.ingotIron, Character.valueOf('#'), Item.ingotGold});
		var1.addRecipe(new ItemStack(Item.amuletRuby, 1), new Object[]{"X X", "X X", " # ", Character.valueOf('X'), Item.ingotIron, Character.valueOf('#'), Item.ruby});
		var1.addRecipe(new ItemStack(Item.ring, 1), new Object[]{" # ", "X X", " X ", Character.valueOf('#'), Item.ingotGold, Character.valueOf('X'), Item.ingotIron});
		var1.addRecipe(new ItemStack(Item.ringDiamond, 1), new Object[]{" # ", "X X", " X ", Character.valueOf('#'), Item.diamond, Character.valueOf('X'), Item.ingotIron});
		var1.addRecipe(new ItemStack(Item.quiver, 1), new Object[]{" X#", "X #", " X#", Character.valueOf('#'), Item.leather, Character.valueOf('X'), Item.silk});
	}
}
