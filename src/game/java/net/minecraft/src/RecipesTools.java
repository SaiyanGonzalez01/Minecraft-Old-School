package net.minecraft.src;

public class RecipesTools {
	private String[][] recipePatterns = new String[][]{{"XXX", "Y# ", " # "}, {" X ", "Y# ", " # "}, {"XX ", "X#Y", " # "}, {"XX ", "Y# ", " # "}, {"XXX", "Y#X", " # "}};
	private Object[][] recipeItems = new Object[][]{{Block.planks, Block.cobblestone}, {Item.pickaxeWood, Item.pickaxeStone}, {Item.shovelWood, Item.shovelStone}, {Item.axeWood, Item.axeStone}, {Item.hoeWood, Item.hoeStone}, {Item.hammerWood, Item.hammerStone}};

	public void addRecipes(CraftingManager var1) {
		//for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
		//	Object var3 = this.recipeItems[0][var2];

		//	for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
		//		Item var5 = (Item)this.recipeItems[var4 + 1][var2];
		//		var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('#'), Item.stick, Character.valueOf('X'), var3});
			//}
		//}

		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('#'), Item.stick, Character.valueOf('Y'), Item.fibers, Character.valueOf('X'), var3});
			}
		}

	}
}
