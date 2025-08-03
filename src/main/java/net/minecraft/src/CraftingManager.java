package net.minecraft.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CraftingManager {
	private static final CraftingManager instance = new CraftingManager();
	private List recipes = new ArrayList();

	public static final CraftingManager getInstance() {
		return instance;
	}

	private CraftingManager() {
		(new RecipesTools()).addRecipes(this);
		(new RecipesWeapons()).addRecipes(this);
		(new RecipesIngots()).addRecipes(this);
		(new RecipesFood()).addRecipes(this);
		(new RecipesCrafting()).addRecipes(this);
		(new RecipesArmor()).addRecipes(this);
		(new RecipesDyes()).addRecipes(this);
		this.addRecipe(new ItemStack(Item.paper, 3), new Object[]{"#X#", Character.valueOf('#'), Item.sawdust, Character.valueOf('X'), Item.bark});
		this.addRecipe(new ItemStack(Item.book, 1), new Object[]{"#", "#", "#", Character.valueOf('#'), Item.paper});
		this.addRecipe(new ItemStack(Block.fence, 2), new Object[]{"###", "###", Character.valueOf('#'), Item.stick});
		this.addRecipe(new ItemStack(Block.jukebox, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.diamond});
		this.addRecipe(new ItemStack(Block.musicBlock, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.redstone});
		this.addRecipe(new ItemStack(Block.bookShelf, 1), new Object[]{"###", "XXX", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.book});
		this.addRecipe(new ItemStack(Block.blockSnow, 1), new Object[]{"##", "##", Character.valueOf('#'), Item.snowball});
		this.addRecipe(new ItemStack(Block.blockClay, 1), new Object[]{"##", "##", Character.valueOf('#'), Item.clay});
		this.addRecipe(new ItemStack(Block.brick, 1), new Object[]{"##", "##", Character.valueOf('#'), Item.brick});
		this.addRecipe(new ItemStack(Block.glowStone, 1), new Object[]{"##", "##", Character.valueOf('#'), Item.lightStoneDust});
		this.addRecipe(new ItemStack(Block.cloth, 1), new Object[]{"##", "##", Character.valueOf('#'), Item.silk});
		this.addRecipe(new ItemStack(Block.tnt, 1), new Object[]{"X#X", "#X#", "X#X", Character.valueOf('X'), Item.gunpowder, Character.valueOf('#'), Block.sand});
		this.addRecipe(new ItemStack(Block.stairSingle, 3, 3), new Object[]{"###", Character.valueOf('#'), Block.cobblestone});
		this.addRecipe(new ItemStack(Block.stairSingle, 3, 0), new Object[]{"###", Character.valueOf('#'), Block.stone});
		this.addRecipe(new ItemStack(Block.stairSingle, 3, 1), new Object[]{"###", Character.valueOf('#'), Block.sandStone});
		this.addRecipe(new ItemStack(Block.stairSingle, 3, 2), new Object[]{"###", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Block.ladder, 2), new Object[]{"# #", "###", "# #", Character.valueOf('#'), Item.stick});
		this.addRecipe(new ItemStack(Item.doorWood, 1), new Object[]{"##", "##", "##", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Block.trapdoor, 2), new Object[]{"###", "###", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Item.doorSteel, 1), new Object[]{"##", "##", "##", Character.valueOf('#'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.sign, 1), new Object[]{"###", "###", " X ", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.stick});
		this.addRecipe(new ItemStack(Item.cake, 1), new Object[]{"AAA", "BEB", "CCC", Character.valueOf('A'), Item.bucketMilk, Character.valueOf('B'), Item.sugar, Character.valueOf('C'), Item.wheat, Character.valueOf('E'), Item.egg});
		this.addRecipe(new ItemStack(Item.sugar, 1), new Object[]{"#", Character.valueOf('#'), Item.reed});
		this.addRecipe(new ItemStack(Block.planks, 4), new Object[]{"#", Character.valueOf('#'), Block.wood});
		this.addRecipe(new ItemStack(Item.stick, 4), new Object[]{"#", "#", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{"X", "#", Character.valueOf('X'), Item.coal, Character.valueOf('#'), Item.stick});
		this.addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{"X", "#", Character.valueOf('X'), new ItemStack(Item.coal, 1, 1), Character.valueOf('#'), Item.stick});
		this.addRecipe(new ItemStack(Item.bowlEmpty, 4), new Object[]{"# #", " # ", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Block.rail, 16), new Object[]{"X X", "X#X", "X X", Character.valueOf('X'), Item.ingotIron, Character.valueOf('#'), Item.stick});
		this.addRecipe(new ItemStack(Block.railPowered, 6), new Object[]{"X X", "X#X", "XRX", Character.valueOf('X'), Item.ingotGold, Character.valueOf('R'), Item.redstone, Character.valueOf('#'), Item.stick});
		this.addRecipe(new ItemStack(Block.railDetector, 6), new Object[]{"X X", "X#X", "XRX", Character.valueOf('X'), Item.ingotIron, Character.valueOf('R'), Item.redstone, Character.valueOf('#'), Block.pressurePlateStone});
		this.addRecipe(new ItemStack(Item.minecartEmpty, 1), new Object[]{"# #", "###", Character.valueOf('#'), Item.ingotIron});
		this.addRecipe(new ItemStack(Block.pumpkinLantern, 1), new Object[]{"A", "B", Character.valueOf('A'), Block.pumpkin, Character.valueOf('B'), Block.torchWood});
		this.addRecipe(new ItemStack(Item.minecartCrate, 1), new Object[]{"A", "B", Character.valueOf('A'), Block.chest, Character.valueOf('B'), Item.minecartEmpty});
		this.addRecipe(new ItemStack(Item.minecartPowered, 1), new Object[]{"A", "B", Character.valueOf('A'), Block.stoneOvenIdle, Character.valueOf('B'), Item.minecartEmpty});
		this.addRecipe(new ItemStack(Item.boat, 1), new Object[]{"# #", "###", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Item.bucketEmpty, 1), new Object[]{"# #", " # ", Character.valueOf('#'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.flintAndSteel, 1), new Object[]{"A ", " B", Character.valueOf('A'), Item.ingotIron, Character.valueOf('B'), Item.flint});
		this.addRecipe(new ItemStack(Item.bread, 1), new Object[]{"###", Character.valueOf('#'), Item.wheat});
		this.addRecipe(new ItemStack(Block.stairCompactPlanks, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Item.fishingRod, 1), new Object[]{"  #", " #X", "# X", Character.valueOf('#'), Item.stick, Character.valueOf('X'), Item.silk});
		this.addRecipe(new ItemStack(Block.stairCompactCobblestone, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Block.cobblestone});
		this.addRecipe(new ItemStack(Item.painting, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Item.stick, Character.valueOf('X'), Block.cloth});
		this.addRecipe(new ItemStack(Item.appleGold, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Block.blockGold, Character.valueOf('X'), Item.appleRed});
		this.addRecipe(new ItemStack(Block.lever, 1), new Object[]{"X", "#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.stick});
		this.addRecipe(new ItemStack(Block.torchRedstoneActive, 1), new Object[]{"X", "#", Character.valueOf('#'), Item.stick, Character.valueOf('X'), Item.redstone});
		this.addRecipe(new ItemStack(Item.redstoneRepeater, 1), new Object[]{"#X#", "III", Character.valueOf('#'), Block.torchRedstoneActive, Character.valueOf('X'), Item.redstone, Character.valueOf('I'), Block.stone});
		this.addRecipe(new ItemStack(Item.pocketSundial, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.ingotGold, Character.valueOf('X'), Item.redstone});
		this.addRecipe(new ItemStack(Item.compass, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.ingotIron, Character.valueOf('X'), Item.redstone});
		this.addRecipe(new ItemStack(Item.mapItem, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Item.paper, Character.valueOf('X'), Item.compass});
		this.addRecipe(new ItemStack(Block.button, 1), new Object[]{"#", "#", Character.valueOf('#'), Block.stone});
		this.addRecipe(new ItemStack(Block.pressurePlateStone, 1), new Object[]{"##", Character.valueOf('#'), Block.stone});
		this.addRecipe(new ItemStack(Block.pressurePlatePlanks, 1), new Object[]{"##", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Block.dispenser, 1), new Object[]{"###", "#X#", "#R#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.bow, Character.valueOf('R'), Item.redstone});
		this.addRecipe(new ItemStack(Block.pistonBase, 1), new Object[]{"TTT", "#X#", "#R#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.ingotIron, Character.valueOf('R'), Item.redstone, Character.valueOf('T'), Block.planks});
		this.addRecipe(new ItemStack(Block.pistonStickyBase, 1), new Object[]{"S", "P", Character.valueOf('S'), Item.slimeBall, Character.valueOf('P'), Block.pistonBase});
		this.addRecipe(new ItemStack(Item.bed, 1), new Object[]{"###", "XXX", Character.valueOf('#'), Block.cloth, Character.valueOf('X'), Block.planks});
		this.addRecipe(new ItemStack(Block.brickGranite, 1), new Object[]{"##", "##", Character.valueOf('#'), Block.granite});
		this.addRecipe(new ItemStack(Item.bark, 4), new Object[]{" # ", " # ", Character.valueOf('#'), Block.wood});
		this.addRecipe(new ItemStack(Item.sawdust, 2), new Object[]{"#", Character.valueOf('#'), Item.bark});
		this.addRecipe(new ItemStack(Block.sawdustBlock, 1), new Object[]{"###", "###", "###", Character.valueOf('#'), Item.sawdust});
		this.addRecipe(new ItemStack(Item.porkSalted, 1), new Object[]{"#X", Character.valueOf('#'), Item.saltI, Character.valueOf('X'), Item.porkCooked});
		this.addRecipe(new ItemStack(Item.porkstuffer, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.wheat, Character.valueOf('X'), Item.porkCooked});
		this.addRecipe(new ItemStack(Item.fishstuffer, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.wheat, Character.valueOf('X'), Item.fishCooked});
		this.addRecipe(new ItemStack(Item.fleshstuffer, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.wheat, Character.valueOf('X'), Item.flesh});
		this.addRecipe(new ItemStack(Item.cookiePlain, 8), new Object[]{"##", Character.valueOf('#'), Item.wheat});
		this.addRecipe(new ItemStack(Item.cookieSugar, 8), new Object[]{"#X#", Character.valueOf('#'), Item.wheat, Character.valueOf('X'), Item.sugar});
		this.addRecipe(new ItemStack(Block.brickStone, 4), new Object[]{"##", "##", Character.valueOf('#'), Block.stone});
		this.addRecipe(new ItemStack(Block.pillarStone, 3), new Object[]{"#", "#", "#", Character.valueOf('#'), Block.stone});
		this.addRecipe(new ItemStack(Item.ironNugget, 9), new Object[]{"#", Character.valueOf('#'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.ingotIron, 1), new Object[]{"###", "###", "###", Character.valueOf('#'), Item.ironNugget});
		this.addRecipe(new ItemStack(Item.chainlink, 3), new Object[]{"# #", " # ", "# #", Character.valueOf('#'), Item.ironNugget});
		this.addRecipe(new ItemStack(Item.bleach, 2), new Object[]{"#X", Character.valueOf('#'), Item.bone, Character.valueOf('X'), Item.saltI});
		this.addRecipe(new ItemStack(Block.planksColored, 2), new Object[]{"#X", "X#", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.bleach});
		this.addRecipe(new ItemStack(Block.netherrackBrick, 1), new Object[]{"##", "##", Character.valueOf('#'), Block.netherrack});
		this.addRecipe(new ItemStack(Item.bowlRice, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.netherrice, Character.valueOf('X'), Item.bowlEmpty});
		this.addRecipe(new ItemStack(Item.bowlRicePork, 1), new Object[]{"#X", Character.valueOf('#'), Item.porkCooked, Character.valueOf('X'), Item.bowlRice});
		this.addRecipe(new ItemStack(Item.bowlRiceEgg, 1), new Object[]{"#X", Character.valueOf('#'), Item.eggCooked, Character.valueOf('X'), Item.bowlRice});
		this.addRecipe(new ItemStack(Item.cannonball, 1), new Object[]{"X#X", "#O#", "X#X", Character.valueOf('#'), Item.gunpowder, Character.valueOf('X'), Item.ingotIron, Character.valueOf('O'), Item.redstone});
		this.addRecipe(new ItemStack(Item.cannon, 1), new Object[]{"XX#", " ZY", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.ingotIron, Character.valueOf('Y'), Item.stick, Character.valueOf('Z'), Item.redstone});
		this.addRecipe(new ItemStack(Item.ingotGold, 1), new Object[]{"###", "###", "###", Character.valueOf('#'), Item.goldNugget});
		this.addRecipe(new ItemStack(Item.goldNugget, 9), new Object[]{"#", Character.valueOf('#'), Item.ingotGold});
		this.addRecipe(new ItemStack(Item.runeEmpty, 1), new Object[]{"#X#", "XZX", "#X#", Character.valueOf('#'), Item.diamond, Character.valueOf('X'), Item.ironNugget, Character.valueOf('Z'), Block.glowStone});
		this.addRecipe(new ItemStack(Item.runeUpgrade, 1), new Object[]{"ZXZ", "X#X", "ZXZ", Character.valueOf('#'), Item.runeEmpty, Character.valueOf('X'), Item.ruby, Character.valueOf('Z'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.swordWoodUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.swordWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Block.cobblestone});
		this.addRecipe(new ItemStack(Item.pickaxeWoodUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.pickaxeWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Block.cobblestone});
		this.addRecipe(new ItemStack(Item.axeWoodUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.axeWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Block.cobblestone});
		this.addRecipe(new ItemStack(Item.shovelWoodUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.shovelWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Block.cobblestone});
		this.addRecipe(new ItemStack(Item.hoeWoodUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.hoeWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Block.cobblestone});
		this.addRecipe(new ItemStack(Item.swordStoneUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.swordStone, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.pickaxeStoneUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.pickaxeStone, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.axeStoneUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.axeStone, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.shovelStoneUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.shovelStone, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.hoeStoneUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.hoeStone, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.swordSteelUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.swordWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotGold});
		this.addRecipe(new ItemStack(Item.pickaxeSteelUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.pickaxeWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotGold});
		this.addRecipe(new ItemStack(Item.axeSteelUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.axeWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotGold});
		this.addRecipe(new ItemStack(Item.shovelSteelUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.shovelWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotGold});
		this.addRecipe(new ItemStack(Item.hoeSteelUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.hoeWood, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ingotGold});
		this.addRecipe(new ItemStack(Item.swordGoldUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.swordGold, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.redstone});
		this.addRecipe(new ItemStack(Item.pickaxeGoldUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.pickaxeGold, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.redstone});
		this.addRecipe(new ItemStack(Item.axeGoldUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.axeGold, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.redstone});
		this.addRecipe(new ItemStack(Item.shovelGoldUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.shovelGold, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.redstone});
		this.addRecipe(new ItemStack(Item.hoeGoldUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.hoeGold, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.redstone});
		this.addRecipe(new ItemStack(Item.swordDiamondUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.swordDiamond, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ruby});
		this.addRecipe(new ItemStack(Item.pickaxeDiamondUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.pickaxeDiamond, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ruby});
		this.addRecipe(new ItemStack(Item.axeDiamondUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.axeDiamond, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ruby});
		this.addRecipe(new ItemStack(Item.shovelDiamondUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.shovelDiamond, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ruby});
		this.addRecipe(new ItemStack(Item.hoeDiamondUpgraded, 1), new Object[]{"XYZ", Character.valueOf('X'), Item.hoeDiamond, Character.valueOf('Y'), Item.runeUpgrade, Character.valueOf('Z'), Item.ruby});
		this.addRecipe(new ItemStack(Item.runeEarth, 1), new Object[]{"ZXZ", "X#X", "ZXZ", Character.valueOf('#'), Item.runeEmpty, Character.valueOf('X'), Block.dirt, Character.valueOf('Z'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.helmetObsidian, 1), new Object[]{" Y ", "#X#", " # ", Character.valueOf('#'), Block.obsidian, Character.valueOf('X'), Item.helmetDiamond, Character.valueOf('Y'), Item.runeUpgrade});
		this.addRecipe(new ItemStack(Item.plateObsidian, 1), new Object[]{" Y ", "#X#", " # ", Character.valueOf('#'), Block.obsidian, Character.valueOf('X'), Item.plateDiamond, Character.valueOf('Y'), Item.runeUpgrade});
		this.addRecipe(new ItemStack(Item.legsObsidian, 1), new Object[]{" Y ", "#X#", " # ", Character.valueOf('#'), Block.obsidian, Character.valueOf('X'), Item.legsDiamond, Character.valueOf('Y'), Item.runeUpgrade});
		this.addRecipe(new ItemStack(Item.bootsObsidian, 1), new Object[]{" Y ", "#X#", " # ", Character.valueOf('#'), Block.obsidian, Character.valueOf('X'), Item.bootsDiamond, Character.valueOf('Y'), Item.runeUpgrade});
		this.addRecipe(new ItemStack(Item.obsidianPlate, 1), new Object[]{"##", Character.valueOf('#'), Block.obsidian});
	
		Collections.sort(this.recipes, new RecipeSorter(this));
		System.out.println(this.recipes.size() + " recipes");
	}

	void addRecipe(ItemStack var1, Object... var2) {
		String var3 = "";
		int var4 = 0;
		int var5 = 0;
		int var6 = 0;
		if(var2[var4] instanceof String[]) {
			String[] var11 = (String[])((String[])var2[var4++]);

			for(int var8 = 0; var8 < var11.length; ++var8) {
				String var9 = var11[var8];
				++var6;
				var5 = var9.length();
				var3 = var3 + var9;
			}
		} else {
			while(var2[var4] instanceof String) {
				String var7 = (String)var2[var4++];
				++var6;
				var5 = var7.length();
				var3 = var3 + var7;
			}
		}

		HashMap var12;
		for(var12 = new HashMap(); var4 < var2.length; var4 += 2) {
			Character var13 = (Character)var2[var4];
			ItemStack var15 = null;
			if(var2[var4 + 1] instanceof Item) {
				var15 = new ItemStack((Item)var2[var4 + 1]);
			} else if(var2[var4 + 1] instanceof Block) {
				var15 = new ItemStack((Block)var2[var4 + 1], 1, -1);
			} else if(var2[var4 + 1] instanceof ItemStack) {
				var15 = (ItemStack)var2[var4 + 1];
			}

			var12.put(var13, var15);
		}

		ItemStack[] var14 = new ItemStack[var5 * var6];

		for(int var16 = 0; var16 < var5 * var6; ++var16) {
			char var10 = var3.charAt(var16);
			if(var12.containsKey(Character.valueOf(var10))) {
				var14[var16] = ((ItemStack)var12.get(Character.valueOf(var10))).copy();
			} else {
				var14[var16] = null;
			}
		}

		this.recipes.add(new ShapedRecipes(var5, var6, var14, var1));
	}

	void addShapelessRecipe(ItemStack var1, Object... var2) {
		ArrayList var3 = new ArrayList();
		Object[] var4 = var2;
		int var5 = var2.length;

		for(int var6 = 0; var6 < var5; ++var6) {
			Object var7 = var4[var6];
			if(var7 instanceof ItemStack) {
				var3.add(((ItemStack)var7).copy());
			} else if(var7 instanceof Item) {
				var3.add(new ItemStack((Item)var7));
			} else {
				if(!(var7 instanceof Block)) {
					throw new RuntimeException("Invalid shapeless recipy!");
				}

				var3.add(new ItemStack((Block)var7));
			}
		}

		this.recipes.add(new ShapelessRecipes(var1, var3));
	}

	public ItemStack findMatchingRecipe(InventoryCrafting var1) {
		for(int var2 = 0; var2 < this.recipes.size(); ++var2) {
			IRecipe var3 = (IRecipe)this.recipes.get(var2);
			if(var3.matches(var1)) {
				return var3.getCraftingResult(var1);
			}
		}

		return null;
	}

	public List getRecipeList() {
		return this.recipes;
	}
}
