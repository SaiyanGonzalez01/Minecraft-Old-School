package net.minecraft.src;

import net.lax1dude.eaglercraft.Random;

public class BlockDispenser extends BlockContainer {
	protected BlockDispenser(int var1) {
		super(var1, Material.rock);
		this.blockIndexInTexture = 45;
	}

	public int tickRate() {
		return 4;
	}

	public int idDropped(int var1, Random var2) {
		return Block.dispenser.blockID;
	}

	public void onBlockAdded(World var1, int var2, int var3, int var4) {
		super.onBlockAdded(var1, var2, var3, var4);
		this.setDispenserDefaultDirection(var1, var2, var3, var4);
	}

	private void setDispenserDefaultDirection(World var1, int var2, int var3, int var4) {
		int var5 = var1.getBlockId(var2, var3, var4 - 1);
		int var6 = var1.getBlockId(var2, var3, var4 + 1);
		int var7 = var1.getBlockId(var2 - 1, var3, var4);
		int var8 = var1.getBlockId(var2 + 1, var3, var4);
		byte var9 = 3;
		if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6]) {
			var9 = 3;
		}

		if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5]) {
			var9 = 2;
		}

		if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8]) {
			var9 = 5;
		}

		if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7]) {
			var9 = 4;
		}

		var1.setBlockMetadataWithNotify(var2, var3, var4, var9);
	}

	public int getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5) {
		if (var5 == 1) {
			return this.blockIndexInTexture + 17;
		} else if (var5 == 0) {
			return this.blockIndexInTexture + 17;
		} else {
			int var6 = var1.getBlockMetadata(var2, var3, var4);
			return var5 != var6 ? this.blockIndexInTexture : this.blockIndexInTexture + 1;
		}
	}

	public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? this.blockIndexInTexture + 17
				: (var1 == 0 ? this.blockIndexInTexture + 17
						: (var1 == 3 ? this.blockIndexInTexture + 1 : this.blockIndexInTexture));
	}

	public boolean blockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5) {
		if (var1.multiplayerWorld) {
			return true;
		} else {
			TileEntityDispenser var6 = (TileEntityDispenser) var1.getBlockTileEntity(var2, var3, var4);
			var5.displayGUIDispenser(var6);
			return true;
		}
	}

	private void dispenseItem(World var1, int var2, int var3, int var4, Random var5) {
		int var6 = var1.getBlockMetadata(var2, var3, var4);
		float var9 = 0.0F;
		float var10 = 0.0F;
		if (var6 == 3) {
			var10 = 1.0F;
		} else if (var6 == 2) {
			var10 = -1.0F;
		} else if (var6 == 5) {
			var9 = 1.0F;
		} else {
			var9 = -1.0F;
		}

		TileEntityDispenser var11 = (TileEntityDispenser) var1.getBlockTileEntity(var2, var3, var4);
		ItemStack var12 = var11.getRandomStackFromInventory();
		double var13 = (double) var2 + (double) var9 * 0.5D + 0.5D;
		double var15 = (double) var3 + 0.5D;
		double var17 = (double) var4 + (double) var10 * 0.5D + 0.5D;
		if (var12 == null) {
			var1.playSoundEffect((double) var2, (double) var3, (double) var4, "random.click", 1.0F, 1.2F);
		} else {
			double var20;
			if (var12.itemID == Item.arrow.shiftedIndex) {
				EntityArrow var19 = new EntityArrow(var1, var13, var15, var17);
				var19.setArrowHeading((double) var9, (double) 0.1F, (double) var10, 1.1F, 6.0F);
				var1.entityJoinedWorld(var19);
				var1.playSoundEffect((double) var2, (double) var3, (double) var4, "random.bow", 1.0F, 1.2F);
			} else if (var12.itemID == Item.egg.shiftedIndex) {
				EntityEgg var34 = new EntityEgg(var1, var13, var15, var17);
				var34.func_20048_a((double) var9, (double) 0.1F, (double) var10, 1.1F, 6.0F);
				var1.entityJoinedWorld(var34);
				var1.playSoundEffect((double) var2, (double) var3, (double) var4, "random.bow", 1.0F, 1.2F);
			} else if (var12.itemID == Item.snowball.shiftedIndex) {
				EntitySnowball var35 = new EntitySnowball(var1, var13, var15, var17);
				var35.func_467_a((double) var9, (double) 0.1F, (double) var10, 1.1F, 6.0F);
				var1.entityJoinedWorld(var35);
				var1.playSoundEffect((double) var2, (double) var3, (double) var4, "random.bow", 1.0F, 1.2F);
			} else {
				EntityItem var36 = new EntityItem(var1, var13, var15 - 0.3D, var17, var12);
				var20 = var5.nextDouble() * 0.1D + 0.2D;
				var36.motionX = (double) var9 * var20;
				var36.motionY = (double) 0.2F;
				var36.motionZ = (double) var10 * var20;
				var36.motionX += var5.nextGaussian() * (double) 0.0075F * 6.0D;
				var36.motionY += var5.nextGaussian() * (double) 0.0075F * 6.0D;
				var36.motionZ += var5.nextGaussian() * (double) 0.0075F * 6.0D;
				var1.entityJoinedWorld(var36);
				var1.playSoundEffect((double) var2, (double) var3, (double) var4, "random.click", 1.0F, 1.0F);
			}

			for (int var37 = 0; var37 < 10; ++var37) {
				var20 = var5.nextDouble() * 0.2D + 0.01D;
				double var22 = var13 + (double) var9 * 0.01D + (var5.nextDouble() - 0.5D) * (double) var10 * 0.5D;
				double var24 = var15 + (var5.nextDouble() - 0.5D) * 0.5D;
				double var26 = var17 + (double) var10 * 0.01D + (var5.nextDouble() - 0.5D) * (double) var9 * 0.5D;
				double var28 = (double) var9 * var20 + var5.nextGaussian() * 0.01D;
				double var30 = -0.03D + var5.nextGaussian() * 0.01D;
				double var32 = (double) var10 * var20 + var5.nextGaussian() * 0.01D;
				var1.spawnParticle("smoke", var22, var24, var26, var28, var30, var32);
			}
		}

	}

	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
		if (var5 > 0 && Block.blocksList[var5].canProvidePower()) {
			boolean var6 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4)
					|| var1.isBlockIndirectlyGettingPowered(var2, var3 + 1, var4);
			if (var6) {
				var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate());
			}
		}

	}

	public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		if (var1.isBlockIndirectlyGettingPowered(var2, var3, var4)
				|| var1.isBlockIndirectlyGettingPowered(var2, var3 + 1, var4)) {
			this.dispenseItem(var1, var2, var3, var4, var5);
		}

	}

	protected TileEntity getBlockEntity() {
		return new TileEntityDispenser();
	}

	public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5) {
		int var6 = MathHelper.floor_double((double) (var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		if (var6 == 0) {
			var1.setBlockMetadataWithNotify(var2, var3, var4, 2);
		}

		if (var6 == 1) {
			var1.setBlockMetadataWithNotify(var2, var3, var4, 5);
		}

		if (var6 == 2) {
			var1.setBlockMetadataWithNotify(var2, var3, var4, 3);
		}

		if (var6 == 3) {
			var1.setBlockMetadataWithNotify(var2, var3, var4, 4);
		}

	}
}
