package net.minecraft.src;

import net.lax1dude.eaglercraft.Random;

public class BlockTallGrass extends BlockFlower {
	protected BlockTallGrass(int var1, int var2) {
		super(var1, var2);
		float var3 = 0.4F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
	}

	public int getBlockTextureFromSideAndMetadata(int var1, int var2) {
		return var2 == 1 ? this.blockIndexInTexture
				: (var2 == 2 ? this.blockIndexInTexture + 0 + 0
						: (var2 == 0 ? this.blockIndexInTexture + 0 : this.blockIndexInTexture));
	}

	public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
		var1.getWorldChunkManager().func_4069_a(var2, var4, 1, 1);
		double var5 = var1.getWorldChunkManager().temperature[0];
		double var7 = var1.getWorldChunkManager().humidity[0];
		return ColorizerGrass.getGrassColor(var5, var7);
	}

	public int idDropped(int var1, Random var2) {
		return var2.nextInt(8) == 0 ? Item.fibers.shiftedIndex : -1;
	}
}
