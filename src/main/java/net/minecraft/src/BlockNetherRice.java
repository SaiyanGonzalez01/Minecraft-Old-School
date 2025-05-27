package net.minecraft.src;

public class BlockNetherRice extends BlockNetherFlower {
	protected BlockNetherRice(int var1, int var2) {
		super(var1, var2);
		float var3 = 0.4F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
	}

	public int idDropped(int var1, Random var2) {
		return var2.nextInt(4) == 0 ? Item.netherrice.shiftedIndex : -1;
	}
}
