package net.minecraft.src;

import net.lax1dude.eaglercraft.Random;

public class BlockBed extends Block {
	public static final int[][] field_22033_a = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

	public BlockBed(int var1) {
		super(var1, 134, Material.cloth);
		this.func_22027_j();
	}

	public boolean blockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5) {
		int var6 = var1.getBlockMetadata(var2, var3, var4);
		if (!func_22032_d(var6)) {
			int var7 = func_22030_c(var6);
			var2 += field_22033_a[var7][0];
			var4 += field_22033_a[var7][1];
			if (var1.getBlockId(var2, var3, var4) != this.blockID) {
				return true;
			}

			var6 = var1.getBlockMetadata(var2, var3, var4);
		}

		if (func_22029_f(var6)) {
			var5.func_22055_b("tile.bed.occupied");
			return true;
		} else if (var5.func_22053_b(var2, var3, var4)) {
			func_22031_a(var1, var2, var3, var4, true);
			return true;
		} else {
			var5.func_22055_b("tile.bed.noSleep");
			return true;
		}
	}

	public int getBlockTextureFromSideAndMetadata(int var1, int var2) {
		if (var1 == 0) {
			return Block.planks.blockIndexInTexture;
		} else {
			int var3 = func_22030_c(var2);
			int var4 = ModelBed.field_22281_c[var3][var1];
			return func_22032_d(var2)
					? (var4 == 2 ? this.blockIndexInTexture + 2 + 16
							: (var4 != 5 && var4 != 4 ? this.blockIndexInTexture + 1 : this.blockIndexInTexture + 1 + 16))
					: (var4 == 3 ? this.blockIndexInTexture - 1 + 16
							: (var4 != 5 && var4 != 4 ? this.blockIndexInTexture : this.blockIndexInTexture + 16));
		}
	}

	public int getRenderType() {
		return 14;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
		this.func_22027_j();
	}

	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
		int var6 = var1.getBlockMetadata(var2, var3, var4);
		int var7 = func_22030_c(var6);
		if (func_22032_d(var6)) {
			if (var1.getBlockId(var2 - field_22033_a[var7][0], var3, var4 - field_22033_a[var7][1]) != this.blockID) {
				var1.setBlockWithNotify(var2, var3, var4, 0);
			}
		} else if (var1.getBlockId(var2 + field_22033_a[var7][0], var3, var4 + field_22033_a[var7][1]) != this.blockID) {
			var1.setBlockWithNotify(var2, var3, var4, 0);
			if (!var1.multiplayerWorld) {
				this.dropBlockAsItem(var1, var2, var3, var4, var6);
			}
		}

	}

	public int idDropped(int var1, Random var2) {
		return func_22032_d(var1) ? 0 : Item.field_22019_aY.shiftedIndex;
	}

	private void func_22027_j() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 9.0F / 16.0F, 1.0F);
	}

	public static int func_22030_c(int var0) {
		return var0 & 3;
	}

	public static boolean func_22032_d(int var0) {
		return (var0 & 8) != 0;
	}

	public static boolean func_22029_f(int var0) {
		return (var0 & 4) != 0;
	}

	public static void func_22031_a(World var0, int var1, int var2, int var3, boolean var4) {
		int var5 = var0.getBlockMetadata(var1, var2, var3);
		if (var4) {
			var5 |= 4;
		} else {
			var5 &= -5;
		}

		var0.setBlockMetadataWithNotify(var1, var2, var3, var5);
	}

	public static ChunkCoordinates func_22028_g(World var0, int var1, int var2, int var3, int var4) {
		int var5 = var0.getBlockMetadata(var1, var2, var3);
		int var6 = func_22030_c(var5);

		for (int var7 = 0; var7 <= 1; ++var7) {
			int var8 = var1 - field_22033_a[var6][0] * var7 - 1;
			int var9 = var3 - field_22033_a[var6][1] * var7 - 1;
			int var10 = var8 + 2;
			int var11 = var9 + 2;

			for (int var12 = var8; var12 <= var10; ++var12) {
				for (int var13 = var9; var13 <= var11; ++var13) {
					if (var0.isBlockOpaqueCube(var12, var2 - 1, var13) && var0.isAirBlock(var12, var2, var13)
							&& var0.isAirBlock(var12, var2 + 1, var13)) {
						if (var4 <= 0) {
							return new ChunkCoordinates(var12, var2, var13);
						}

						--var4;
					}
				}
			}
		}

		return new ChunkCoordinates(var1, var2 + 1, var3);
	}
}
