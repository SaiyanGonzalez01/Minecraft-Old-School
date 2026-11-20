package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class AnvilManager {
	private static final AnvilManager instance = new AnvilManager();
	private final List<AnvilRecipe> recipes = new ArrayList<AnvilRecipe>();

	public static AnvilManager getInstance() {
		return instance;
	}

	private AnvilManager() {
		this.addRecipe(new ItemStack(Item.swordSteel), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.pickaxeSteel), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.stick), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.shovelSteel), new ItemStack(Item.moltenIron), new ItemStack(Item.stick), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.axeSteel), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.hoeSteel), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.stick), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.hammerSteel), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.helmetSteel), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron));
		this.addRecipe(new ItemStack(Item.plateSteel), new ItemStack(Item.moltenIron, 2), new ItemStack(Item.moltenIron, 2), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron));
		this.addRecipe(new ItemStack(Item.legsSteel), new ItemStack(Item.moltenIron, 2), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron));
		this.addRecipe(new ItemStack(Item.bootsSteel), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron), new ItemStack(Item.moltenIron));

		this.addRecipe(new ItemStack(Item.swordGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.pickaxeGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.stick), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.shovelGold), new ItemStack(Item.moltenGold), new ItemStack(Item.stick), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.axeGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.hoeGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.stick), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.hammerGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.helmetGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold));
		this.addRecipe(new ItemStack(Item.plateGold), new ItemStack(Item.moltenGold, 2), new ItemStack(Item.moltenGold, 2), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold));
		this.addRecipe(new ItemStack(Item.legsGold), new ItemStack(Item.moltenGold, 2), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold));
		this.addRecipe(new ItemStack(Item.bootsGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold), new ItemStack(Item.moltenGold));

		this.addRecipe(new ItemStack(Item.swordDiamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.pickaxeDiamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.stick), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.shovelDiamond), new ItemStack(Item.diamond), new ItemStack(Item.stick), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.axeDiamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.hoeDiamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.stick), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.hammerDiamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.stick));
		this.addRecipe(new ItemStack(Item.helmetDiamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond));
		this.addRecipe(new ItemStack(Item.plateDiamond), new ItemStack(Item.diamond, 2), new ItemStack(Item.diamond, 2), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond));
		this.addRecipe(new ItemStack(Item.legsDiamond), new ItemStack(Item.diamond, 2), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond));
		this.addRecipe(new ItemStack(Item.bootsDiamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond));

	}

	public void addRecipe(ItemStack result, ItemStack... ingredients) {
		this.recipes.add(new AnvilRecipe(result, ingredients));
	}

	public ItemStack getRecipe(InventoryCrafting matrix, ItemStack hammer) {
		AnvilRecipe recipe = this.getAnvilRecipe(matrix, hammer);
		return recipe == null ? null : recipe.func_21136_b(matrix);
	}

	public AnvilRecipe getAnvilRecipe(InventoryCrafting matrix, ItemStack hammer) {
		if (hammer == null || !(hammer.getItem() instanceof ItemHammer)) {
			return null;
		}

		for (AnvilRecipe recipe : this.recipes) {
			if (recipe.func_21135_a(matrix)) {
				return recipe;
			}
		}

		return null;
	}
}