package net.minecraft.src;

public abstract class EntityAnimals extends EntityCreature {
	public EntityAnimals(World var1) {
		super(var1);
	}

	protected float getBlockPathWeight(int var1, int var2, int var3) {
		return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID ? 10.0F : this.worldObj.getLightBrightness(var1, var2, var3) - 0.5F;
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
	}

	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID && this.worldObj.getBlockLightValue(var1, var2, var3) > 8 && super.getCanSpawnHere();
	}

	public int func_421_b() {
		return 120;
	}

	public boolean attackEntityFrom(Entity var1, int var2) {
		if(super.attackEntityFrom(var1, var2)) {
			if (this instanceof EntityPig) {
				for (int i = 0; i < 4; ++i) {
					double var4 = this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width;
					double var6 = this.posY + this.rand.nextDouble() * (double)this.height;
					double var8 = this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width;
					this.worldObj.spawnParticle("blood", var4, var6, var8, 0.0D, 0.0D, 0.0D);
				}
			} else if (this instanceof EntitySheep) {
				for (int i = 0; i < 5; ++i) {
					double var4 = this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width;
					double var6 = this.posY + this.rand.nextDouble() * (double)this.height;
					double var8 = this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width;
					this.worldObj.spawnParticle("blood", var4, var6, var8, 0.0D, 0.0D, 0.0D);
				}
			} else if (this instanceof EntityCow) {
				for (int i = 0; i < 5; ++i) {
					double var4 = this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width;
					double var6 = this.posY + this.rand.nextDouble() * (double)this.height;
					double var8 = this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width;
					this.worldObj.spawnParticle("blood", var4, var6, var8, 0.0D, 0.0D, 0.0D);
				}
			} else if (this instanceof EntityChicken) {
				for (int i = 0; i < 2; ++i) {
					double var4 = this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width;
					double var6 = this.posY + this.rand.nextDouble() * (double)this.height;
					double var8 = this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width;
					this.worldObj.spawnParticle("blood", var4, var6, var8, 0.0D, 0.0D, 0.0D);
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
