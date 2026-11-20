package net.minecraft.src;

public class CraftingInventoryAnvilCB extends CraftingInventoryCB {
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 2);
	public InventoryCrafting crafthammer = new InventoryCrafting(this, 1, 1);
	public IInventory craftResult = new InventoryCraftResult();
	private World field_20133_c;
	private int field_20132_h;
	private int field_20131_i;
	private int field_20130_j;

	public CraftingInventoryAnvilCB(InventoryPlayer var1, World var2, int var3, int var4, int var5) {
		this.field_20133_c = var2;
		this.field_20132_h = var3;
		this.field_20131_i = var4;
		this.field_20130_j = var5;
		this.func_20117_a(new AnvilCraftingSlot(this.craftMatrix, this.craftResult, 0, 148, 35, this.crafthammer, var2, var3, var4, var5));

		int var6;
		int var7;
		for(var6 = 0; var6 < 2; ++var6) {
			for(var7 = 0; var7 < 3; ++var7) {
				this.func_20117_a(new Slot(this.craftMatrix, var7 + var6 * 3, 8 + var7 * 18, 25 + var6 * 18));
			}
		}

		for(var6 = 0; var6 < 1; ++var6) {
			for(var7 = 0; var7 < 1; ++var7) {
				this.func_20117_a(new AnvilHammerSlot(this.crafthammer, var7 + var6 * 3, 93 + var7 * 12, 35 + var6 * 18));
			}
		}

		for(var6 = 0; var6 < 3; ++var6) {
			for(var7 = 0; var7 < 9; ++var7) {
				this.func_20117_a(new Slot(var1, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
			}
		}

		for(var6 = 0; var6 < 9; ++var6) {
			this.func_20117_a(new Slot(var1, var6, 8 + var6 * 18, 142));
		}

		this.onCraftMatrixChanged(this.craftMatrix);
	}

	public void onCraftMatrixChanged(IInventory var1) {
		this.craftResult.setInventorySlotContents(0, AnvilManager.getInstance().getRecipe(this.craftMatrix, this.crafthammer.getStackInSlot(0)));
	}

	public void onCraftGuiClosed(EntityPlayer var1) {
		super.onCraftGuiClosed(var1);

		for(int var2 = 0; var2 < 9; ++var2) {
			ItemStack var3 = this.craftMatrix.getStackInSlot(var2);
			if(var3 != null) {
				var1.dropPlayerItem(var3);
			}
		}

		ItemStack hammer = this.crafthammer.getStackInSlot(0);
		if (hammer != null) {
			var1.dropPlayerItem(hammer);
			this.crafthammer.setInventorySlotContents(0, null);
		}

	}

	private static class AnvilHammerSlot extends Slot {
		public AnvilHammerSlot(IInventory inventory, int slot, int x, int y) {
			super(inventory, slot, x, y);
		}

		public boolean isItemValid(ItemStack stack) {
			return stack != null && stack.getItem() instanceof ItemHammer;
		}
	}

	private static class AnvilCraftingSlot extends Slot {
		private final InventoryCrafting matrix;
		private final InventoryCrafting hammerItem;
		private final World world;
		private final int anvilX;
		private final int anvilY;
		private final int anvilZ;

		public AnvilCraftingSlot(InventoryCrafting matrix, IInventory result, int slot, int x, int y, InventoryCrafting hammerInventory, World world, int anvilX, int anvilY, int anvilZ) {
			super(result, slot, x, y);
			this.matrix = matrix;
			this.hammerItem = hammerInventory;
			this.world = world;
			this.anvilX = anvilX;
			this.anvilY = anvilY;
			this.anvilZ = anvilZ;
		}

		public boolean isItemValid(ItemStack stack) {
			return false;
		}

		public void onPickupFromSlot() {
			super.onPickupFromSlot();
			ItemStack hammer = this.hammerItem.getStackInSlot(0);
			AnvilRecipe recipe = AnvilManager.getInstance().getAnvilRecipe(this.matrix, hammer);
			if (recipe == null) {
				return;
			}

			int[] consumption = recipe.getIngredientConsumption(this.matrix);
			if (consumption == null) {
				return;
			}

			for (int slot = 0; slot < consumption.length; ++slot) {
				if (consumption[slot] > 0) {
					this.matrix.decrStackSize(slot, consumption[slot]);
				}
			}

			hammer.damageItem(1);
			this.hammerItem.setInventorySlotContents(0, hammer.stackSize > 0 ? hammer : null);
			this.world.playSoundEffect((double) this.anvilX + 0.5D, (double) this.anvilY + 0.5D, (double) this.anvilZ + 0.5D, "random.anvil_use", 1.0F, 1.0F);
		}
	}

	public boolean func_20120_b(EntityPlayer var1) {
		return this.field_20133_c.getBlockId(this.field_20132_h, this.field_20131_i, this.field_20130_j) != Block.anvil.blockID ? false : var1.getDistanceSq((double)this.field_20132_h + 0.5D, (double)this.field_20131_i + 0.5D, (double)this.field_20130_j + 0.5D) <= 64.0D;
	}
}
