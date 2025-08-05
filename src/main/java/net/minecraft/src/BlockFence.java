package net.minecraft.src;

public class BlockFence extends Block {
	public BlockFence(int var1, int var2) {
		super(var1, var2, Material.wood);
	}

	public BlockFence(int var1, int var2, Material var3) {
		super(var1, var2, var3);
	}

	public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
		return super.canPlaceBlockAt(var1, var2, var3, var4);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
		boolean var5 = this.canConnectFenceTo(var1, var2, var3, var4 - 1);
		boolean var6 = this.canConnectFenceTo(var1, var2, var3, var4 + 1);
		boolean var7 = this.canConnectFenceTo(var1, var2 - 1, var3, var4);
		boolean var8 = this.canConnectFenceTo(var1, var2 + 1, var3, var4);
		float var9 = 6.0F / 16.0F;
		float var10 = 10.0F / 16.0F;
		float var11 = 6.0F / 16.0F;
		float var12 = 10.0F / 16.0F;
		if(var5) {
			var11 = 0.0F;
		}

		if(var6) {
			var12 = 1.0F;
		}

		if(var7) {
			var9 = 0.0F;
		}

		if(var8) {
			var10 = 1.0F;
		}

		return AxisAlignedBB.getBoundingBoxFromPool((double)((float)var2 + var9), (double)var3, (double)((float)var4 + var11), (double)((float)var2 + var10), (double)((float)var3 + 1.5F), (double)((float)var4 + var12));
	}

	public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
		boolean var5 = this.canConnectFenceTo(var1, var2, var3, var4 - 1);
		boolean var6 = this.canConnectFenceTo(var1, var2, var3, var4 + 1);
		boolean var7 = this.canConnectFenceTo(var1, var2 - 1, var3, var4);
		boolean var8 = this.canConnectFenceTo(var1, var2 + 1, var3, var4);
		float var9 = 6.0F / 16.0F;
		float var10 = 10.0F / 16.0F;
		float var11 = 6.0F / 16.0F;
		float var12 = 10.0F / 16.0F;
		if(var5) {
			var11 = 0.0F;
		}

		if(var6) {
			var12 = 1.0F;
		}

		if(var7) {
			var9 = 0.0F;
		}

		if(var8) {
			var10 = 1.0F;
		}

		this.setBlockBounds(var9, 0.0F, var11, var10, 1.0F, var12);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4) {
		return false;
	}

	public int getRenderType() {
		return 11;
	}

	public boolean canConnectFenceTo(IBlockAccess var1, int var2, int var3, int var4) {
		int var5 = var1.getBlockId(var2, var3, var4);
		if(var5 != this.blockID && var5 != Block.fenceGate.blockID) {
			Block var6 = Block.blocksList[var5];
			return var6 != null && var6.blockMaterial.isOpaque() && var6.renderAsNormalBlock() ? var6.blockMaterial != Material.pumpkin : false;
		} else {
			return true;
		}
	}
}
