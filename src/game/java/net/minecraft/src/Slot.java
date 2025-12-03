package net.minecraft.src;

public class Slot {
	private final int slotIndex;
	private final IInventory inventory;
	public int field_20007_a;
	public int xDisplayPosition;
	public int yDisplayPosition;

	public Slot(IInventory var1, int var2, int var3, int var4) {
		this.inventory = var1;
		this.slotIndex = var2;
		this.xDisplayPosition = var3;
		this.yDisplayPosition = var4;
	}

	public void onPickupFromSlot() {
		this.onSlotChanged();
	}

	public boolean isItemValid(ItemStack var1) {
		return true;
	}

	public ItemStack getStack() {
		return this.inventory.getStackInSlot(this.slotIndex);
	}

	public boolean func_20005_c() {
		return this.getStack() != null;
	}

	public void putStack(ItemStack var1) {
		this.inventory.setInventorySlotContents(this.slotIndex, var1);
		this.onSlotChanged();
	}

	public void onSlotChanged() {
		this.inventory.onInventoryChanged();
	}

	public int getSlotStackLimit() {
		return this.inventory.getInventoryStackLimit();
	}

	public int func_775_c() {
		return -1;
	}

	public ItemStack decrStackSize(int var1) {
		return this.inventory.decrStackSize(this.slotIndex, var1);
	}
}
