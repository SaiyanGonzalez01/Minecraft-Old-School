package net.minecraft.src;

public class RecipesWeapons {
	private String[][] recipePatterns = new String[][]{{"X", "XAX", "#"}};
	private Object[][] recipeItems = new Object[][]{Item.ingotIron}, {Item.greatswordIron}};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('#'), Item.stick, Character.valueOf('X'), var3, Character.valueOf('A'), Item.swordpaper});
			}
		}

	
	}
}
