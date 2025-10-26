package net.minecraft.src;

public class RecipesFood {
	public void addRecipes(CraftingManager var1) {
		var1.addRecipe(new ItemStack(Item.bowlSoup), new Object[]{"Y", "X", "#", Character.valueOf('X'), Block.mushroomBrown, Character.valueOf('Y'), Block.mushroomRed, Character.valueOf('#'), Item.bowlEmpty});
		var1.addRecipe(new ItemStack(Item.bowlSoup), new Object[]{"Y", "X", "#", Character.valueOf('X'), Block.mushroomRed, Character.valueOf('Y'), Block.mushroomBrown, Character.valueOf('#'), Item.bowlEmpty});
		var1.addRecipe(new ItemStack(Item.cookie, 8), new Object[]{"#X#", Character.valueOf('X'), new ItemStack(Item.dyePowder, 1, 3), Character.valueOf('#'), Item.wheat});
		var1.addRecipe(new ItemStack(Item.bowlRice, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.netherrice, Character.valueOf('X'), Item.bowlEmpty});
		var1.addRecipe(new ItemStack(Item.bowlRicePork, 1), new Object[]{"#X", Character.valueOf('#'), Item.porkCooked, Character.valueOf('X'), Item.bowlRice});
		var1.addRecipe(new ItemStack(Item.bowlRiceEgg, 1), new Object[]{"#X", Character.valueOf('#'), Item.eggCooked, Character.valueOf('X'), Item.bowlRice});
		var1.addRecipe(new ItemStack(Item.porkSalted, 1), new Object[]{"#X", Character.valueOf('#'), Item.saltI, Character.valueOf('X'), Item.porkCooked});
		var1.addRecipe(new ItemStack(Item.porkstuffer, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.wheat, Character.valueOf('X'), Item.porkCooked});
		var1.addRecipe(new ItemStack(Item.fishstuffer, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.wheat, Character.valueOf('X'), Item.fishCooked});
		var1.addRecipe(new ItemStack(Item.fleshstuffer, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.wheat, Character.valueOf('X'), Item.flesh});
		var1.addRecipe(new ItemStack(Item.cookiePlain, 8), new Object[]{"##", Character.valueOf('#'), Item.wheat});
		var1.addRecipe(new ItemStack(Item.cookieSugar, 8), new Object[]{"#X#", Character.valueOf('#'), Item.wheat, Character.valueOf('X'), Item.sugar});
		var1.addRecipe(new ItemStack(Item.cake, 1), new Object[]{"AAA", "BEB", "CCC", Character.valueOf('A'), Item.bucketMilk, Character.valueOf('B'), Item.sugar, Character.valueOf('C'), Item.wheat, Character.valueOf('E'), Item.egg});
		var1.addRecipe(new ItemStack(Item.piePumpkin, 1), new Object[]{"ABA", "BEB", "CBC", Character.valueOf('A'), Item.bucketMilk, Character.valueOf('C'), Item.sugar, Character.valueOf('B'), Item.wheat, Character.valueOf('E'), Block.pumpkin});
		var1.addRecipe(new ItemStack(Item.pieBlueberry, 1), new Object[]{"AEA", "BEB", "CBC", Character.valueOf('A'), Item.bucketMilk, Character.valueOf('C'), Item.blueberry, Character.valueOf('B'), Item.wheat, Character.valueOf('E'), Item.sugar});
		var1.addRecipe(new ItemStack(Item.pieApple, 1), new Object[]{"AEA", "BEB", "CBC", Character.valueOf('A'), Item.bucketMilk, Character.valueOf('C'), Item.appleRed, Character.valueOf('B'), Item.wheat, Character.valueOf('E'), Item.sugar});
		var1.addRecipe(new ItemStack(Item.bread, 1), new Object[]{"###", Character.valueOf('#'), Item.wheat});
		var1.addRecipe(new ItemStack(Item.appleGold, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Block.blockGold, Character.valueOf('X'), Item.appleRed});
	}
}
