package net.minecraft.src;

public class BlockBlueberry extends BlockFlower {
	protected BlockBlueberry(int var1, int var2) {
		super(var1, var2);
		float var3 = 0.4F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
	}

	public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
		return super.canPlaceBlockAt(var1, var2, var3, var4) && this.canThisPlantGrowOnThisBlockID(var1.getBlockId(var2, var3 - 1, var4));
	}

	protected boolean canThisPlantGrowOnThisBlockID(int var1) {
		return var1 == Block.grass.blockID || var1 == Block.dirt.blockID || var1 == Block.tilledField.blockID;
	}

	public int idDropped(int var1, Random var2) {
		return var2.nextInt(4) == 0 ? Item.blueberry.shiftedIndex : 2;
	}
}
