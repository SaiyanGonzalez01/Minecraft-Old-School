package net.minecraft.src;

import net.minecraft.src.Item;

class SlotArmor extends Slot {
	final int field_1124_c;
	final CraftingInventoryPlayerCB field_1123_d;

	SlotArmor(CraftingInventoryPlayerCB var1, IInventory var2, int var3, int var4, int var5, int var6) {
		super(var2, var3, var4, var5);
		this.field_1123_d = var1;
		this.field_1124_c = var6;
	}

	public int getSlotStackLimit() {
		return 1;
	}
	// lowkey would forget about what is what if i didnt label
	public int func_775_c() {
		switch(this.field_1124_c) {
		case 0: // helmet
			return 15;
		case 1: // chestplate
			return 31;
		case 2: // leggings
			return 47;
		case 3: // boots
			return 63;
		case 4: // left hand
			return 62;
		case 5: // right hand
			return 46;
		case 6: // ring1
			return 222;
		case 7: // upgrade1
			return 223;
		case 8: // ring2
			return 222;
		case 9: // upgrade2
			return 223;
		case 10: // quiver
			return 207;
		case 11: // necklace
			return 206;
		default:
			return -1;
		}
	}

	public boolean isItemValid(ItemStack var1) {
		if(var1 == null) {
			return false;
		}

		if(this.field_1124_c < 4) {
			return var1.getItem() instanceof ItemArmor ? ((ItemArmor)var1.getItem()).armorType == this.field_1124_c : (var1.getItem().shiftedIndex == Block.pumpkin.blockID ? this.field_1124_c == 0 : false);
		}

		if(this.field_1124_c == 10) {
			return var1.getItem() == Item.quiver;
		}

		if(this.field_1124_c == 11) {
			return var1.getItem() == Item.amulet || var1.getItem() == Item.amuletRuby;
		}

		if(this.field_1124_c == 6 || this.field_1124_c == 8) {
			return var1.getItem() == Item.ring || var1.getItem() == Item.ringDiamond;
		}

		return false;
	}
}
