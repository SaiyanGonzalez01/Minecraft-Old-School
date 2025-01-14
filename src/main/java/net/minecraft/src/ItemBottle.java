package net.minecraft.src;

public class ItemBottle extends Item {
	private int isFull;

	public ItemBottle(int var1, int var2) {
		super(var1);
		this.maxStackSize = 1;
		this.isFull = var2;
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		float var4 = 1.0F;
		float var5 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * var4;
		float var6 = var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * var4;
		double var7 = var3.prevPosX + (var3.posX - var3.prevPosX) * (double)var4;
		double var9 = var3.prevPosY + (var3.posY - var3.prevPosY) * (double)var4 + 1.62D - (double)var3.yOffset;
		double var11 = var3.prevPosZ + (var3.posZ - var3.prevPosZ) * (double)var4;
		Vec3D var13 = Vec3D.createVector(var7, var9, var11);
		float var14 = MathHelper.cos(-var6 * ((float)Math.PI / 180.0F) - (float)Math.PI);
		float var15 = MathHelper.sin(-var6 * ((float)Math.PI / 180.0F) - (float)Math.PI);
		float var16 = -MathHelper.cos(-var5 * ((float)Math.PI / 180.0F));
		float var17 = MathHelper.sin(-var5 * ((float)Math.PI / 180.0F));
		float var18 = var15 * var16;
		float var20 = var14 * var16;
		double var21 = 5.0D;
		Vec3D var23 = var13.addVector((double)var18 * var21, (double)var17 * var21, (double)var20 * var21);
		MovingObjectPosition var24 = var2.rayTraceBlocks_do(var13, var23, this.isFull == 0);
		if(var24 == null) {
			return var1;
		} else {
			if(var24.typeOfHit == EnumMovingObjectType.TILE) {
				int var25 = var24.blockX;
				int var26 = var24.blockY;
				int var27 = var24.blockZ;
				if(!var2.func_6466_a(var3, var25, var26, var27)) {
					return var1;
				}

				if(this.isFull == 0) {
					if(var2.getBlockMaterial(var25, var26, var27) == Material.water && var2.getBlockMetadata(var25, var26, var27) == 0) {
						var2.setBlockWithNotify(var25, var26, var27, 0);
						return new ItemStack(Item.bucketWater);
					}
