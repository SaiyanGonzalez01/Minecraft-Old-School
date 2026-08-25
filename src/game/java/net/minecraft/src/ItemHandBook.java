package net.minecraft.src;

public class ItemHandBook extends Item {
    public ItemHandBook(int var1) {
        super(var1);
        this.maxStackSize = 1;
    }

    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
        var3.displayGUIHandBook(var1);
        return var1;
    }
    
}
