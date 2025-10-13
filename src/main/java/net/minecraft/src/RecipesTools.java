package net.minecraft.src;

public class RecipesTools {
	private String[][] recipePatterns = new String[][]{{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}, {" X ", "  X", " # "}};
	private Object[][] recipeItems = new Object[][]{{Block.planks, Block.cobblestone, Item.ingotIron, Item.diamond, Item.ingotGold}, {Item.pickaxeWood, Item.pickaxeStone, Item.pickaxeSteel, Item.pickaxeDiamond, Item.pickaxeGold}, {Item.shovelWood, Item.shovelStone, Item.shovelSteel, Item.shovelDiamond, Item.shovelGold}, {Item.axeWood, Item.axeStone, Item.axeSteel, Item.axeDiamond, Item.axeGold}, {Item.hoeWood, Item.hoeStone, Item.hoeSteel, Item.hoeDiamond, Item.hoeGold}, {Item.sickleWood, Item.sickleStone, Item.sickleSteel, Item.sickleDiamond, Item.sickleGold}};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('#'), Item.stick, Character.valueOf('X'), var3});
			}
		}

		var1.addRecipe(new ItemStack(Item.shears), new Object[]{" #", "# ", Character.valueOf('#'), Item.ingotIron});
		var1.addRecipe(new ItemStack(Item.pickaxeObsidian, 1), new Object[]{"YX#", Character.valueOf('#'), Item.obsidianPlate, Character.valueOf('X'), Item.runeUpgrade, Character.valueOf('Y'), Item.pickaxeDiamond});
		var1.addRecipe(new ItemStack(Item.axeObsidian, 1), new Object[]{"YX#", Character.valueOf('#'), Item.obsidianPlate, Character.valueOf('X'), Item.runeUpgrade, Character.valueOf('Y'), Item.axeDiamond});
		var1.addRecipe(new ItemStack(Item.shovelObsidian, 1), new Object[]{"YX#", Character.valueOf('#'), Item.obsidianPlate, Character.valueOf('X'), Item.runeUpgrade, Character.valueOf('Y'), Item.shovelDiamond});
		var1.addRecipe(new ItemStack(Item.hoeObsidian, 1), new Object[]{"YX#", Character.valueOf('#'), Item.obsidianPlate, Character.valueOf('X'), Item.runeUpgrade, Character.valueOf('Y'), Item.hoeDiamond});
		var1.addRecipe(new ItemStack(Item.scytheObsidian, 1), new Object[]{"YX#", Character.valueOf('#'), Item.obsidianPlate, Character.valueOf('X'), Item.runeUpgrade, Character.valueOf('Y'), Item.scytheDiamond});
		var1.addRecipe(new ItemStack(Item.swordWoodUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.swordWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Block.cobblestone});
		var1.addRecipe(new ItemStack(Item.pickaxeWoodUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.pickaxeWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Block.cobblestone});
		var1.addRecipe(new ItemStack(Item.axeWoodUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.axeWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Block.cobblestone});
		var1.addRecipe(new ItemStack(Item.shovelWoodUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.shovelWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Block.cobblestone});
		var1.addRecipe(new ItemStack(Item.hoeWoodUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.hoeWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Block.cobblestone});
		var1.addRecipe(new ItemStack(Item.swordStoneUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.swordStone, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotIron});
		var1.addRecipe(new ItemStack(Item.pickaxeStoneUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.pickaxeStone, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotIron});
		var1.addRecipe(new ItemStack(Item.axeStoneUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.axeStone, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotIron});
		var1.addRecipe(new ItemStack(Item.shovelStoneUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.shovelStone, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotIron});
		var1.addRecipe(new ItemStack(Item.hoeStoneUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.hoeStone, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotIron});
		var1.addRecipe(new ItemStack(Item.swordSteelUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.swordWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotGold});
		var1.addRecipe(new ItemStack(Item.pickaxeSteelUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.pickaxeWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotGold});
		var1.addRecipe(new ItemStack(Item.axeSteelUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.axeWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotGold});
		var1.addRecipe(new ItemStack(Item.shovelSteelUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.shovelWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotGold});
		var1.addRecipe(new ItemStack(Item.hoeSteelUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.hoeWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotGold});
		var1.addRecipe(new ItemStack(Item.swordGoldUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.swordGold, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.redstone});
		var1.addRecipe(new ItemStack(Item.pickaxeGoldUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.pickaxeGold, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.redstone});
		var1.addRecipe(new ItemStack(Item.axeGoldUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.axeGold, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.redstone});
		var1.addRecipe(new ItemStack(Item.shovelGoldUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.shovelGold, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.redstone});
		var1.addRecipe(new ItemStack(Item.hoeGoldUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.hoeGold, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.redstone});
		var1.addRecipe(new ItemStack(Item.swordDiamondUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.swordDiamond, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ruby});
		var1.addRecipe(new ItemStack(Item.pickaxeDiamondUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.pickaxeDiamond, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ruby});
		var1.addRecipe(new ItemStack(Item.axeDiamondUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.axeDiamond, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ruby});
		var1.addRecipe(new ItemStack(Item.shovelDiamondUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.shovelDiamond, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ruby});
		var1.addRecipe(new ItemStack(Item.hoeDiamondUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.hoeDiamond, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ruby});
		
	}
}
