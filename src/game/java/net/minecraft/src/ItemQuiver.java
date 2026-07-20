package net.minecraft.src;

public class ItemQuiver extends Item {
    public ItemQuiver(int var1) {
        super(var1);
        this.maxStackSize = 1;
        this.maxDamage = 128;
    }

    // set up for later storing arrows in the quiver

    private static int getStoredArrows(ItemStack stack) {
        return stack == null ? 0 : 128 - stack.getItemDamage();
    }

    private static ItemStack withStoredArrows(int storedArrows) {
        storedArrows = Math.max(0, Math.min(128, storedArrows));
        return new ItemStack(Item.quiver, 1, 128 - storedArrows);
    }

    public static boolean consumeArrowFromQuiver(EntityPlayer player) {
        if(player == null || player.inventory == null || player.inventory.armorInventory == null) {
            return false;
        }

        ItemStack[] armor = player.inventory.armorInventory;
        for(int i = 0; i < armor.length; ++i) {
            ItemStack quiver = armor[i];
            if(quiver != null && quiver.getItem() == Item.quiver) {
                int stored = getStoredArrows(quiver);
                if(stored > 0) {
                    player.inventory.setInventorySlotContents(player.inventory.mainInventory.length + i, withStoredArrows(stored - 1));
                    return true;
                }
            }
        }
        return false;
    }
}
