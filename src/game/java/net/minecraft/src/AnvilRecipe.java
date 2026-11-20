package net.minecraft.src;

public class AnvilRecipe implements IRecipe {
	private final ItemStack result;
	private final ItemStack[] ingredients;

	public AnvilRecipe(ItemStack result, ItemStack... ingredients) {
		if (ingredients.length == 0 || ingredients.length > 6) {
			throw new IllegalArgumentException("invalid amount of items");
		}

		this.result = result.copy();
		this.ingredients = new ItemStack[ingredients.length];
		for (int i = 0; i < ingredients.length; ++i) {
			if (ingredients[i].stackSize <= 0) {
				throw new IllegalArgumentException("invalid stack size");
			}
			this.ingredients[i] = ingredients[i].copy();
		}
	}

	public boolean func_21135_a(InventoryCrafting matrix) {
		return this.getIngredientConsumption(matrix) != null;
	}

	public int[] getIngredientConsumption(InventoryCrafting matrix) {
		if (matrix.getSizeInventory() != 6) {
			return null;
		}

		int[] remainingIngredients = new int[this.ingredients.length];
		for (int i = 0; i < this.ingredients.length; ++i) {
			remainingIngredients[i] = this.ingredients[i].stackSize;
		}

		int[] consumption = new int[6];
		for (int slot = 0; slot < 6; ++slot) {
			ItemStack input = matrix.getStackInSlot(slot);
			if (input == null) {
				continue;
			}

			for (int count = 0; count < input.stackSize; ++count) {
				int matchedIngredient = this.findIngredient(input, remainingIngredients, false);
				if (matchedIngredient < 0) {
					matchedIngredient = this.findIngredient(input, remainingIngredients, true);
				}

				if (matchedIngredient < 0) {
					return null;
				}

				--remainingIngredients[matchedIngredient];
				++consumption[slot];
			}
		}

		for (int remaining : remainingIngredients) {
			if (remaining > 0) {
				return null;
			}
		}

		return consumption;
	}

	private int findIngredient(ItemStack input, int[] remainingIngredients, boolean damage) {
		for (int ingredient = 0; ingredient < this.ingredients.length; ++ingredient) {
			ItemStack expected = this.ingredients[ingredient];
			if (remainingIngredients[ingredient] > 0 && input.itemID == expected.itemID && (damage ? expected.getItemDamage() == -1 : expected.getItemDamage() != -1 && input.getItemDamage() == expected.getItemDamage())) {
				return ingredient;
			}
		}
		return -1;
	}

	public ItemStack func_21136_b(InventoryCrafting matrix) {
		return this.result.copy();
	}

	public int getRecipeSize() {
		return this.ingredients.length;
	}
}