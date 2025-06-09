package net.minecraft.src;

public class EntityNetherSkeleton extends EntityMob {
	private static final ItemStack defaultHeldItem = new ItemStack(Item.swordStone, 1);

	public EntityNetherSkeleton(World var1) {
		super(var1);
    		this.moveSpeed = 1.0F;
		this.attackStrength = 5;
	}

	protected String getLivingSound() {
		return "mob.skeleton";
	}

	protected String getHurtSound() {
		return "mob.skeletonhurt";
	}

	protected String getDeathSound() {
		return "mob.skeletonhurt";
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
	}

	protected int getDropItemId() {
		return Item.arrow.shiftedIndex;
	}

	protected void dropFewItems() {
		int var1 = this.rand.nextInt(3);

		int var2;
		for(var2 = 0; var2 < var1; ++var2) {
			this.dropItem(Item.arrow.shiftedIndex, 1);
		}

		var1 = this.rand.nextInt(3);

		for(var2 = 0; var2 < var1; ++var2) {
			this.dropItem(Item.bone.shiftedIndex, 1);
		}

	}

	public ItemStack getHeldItem() {
		return defaultHeldItem;
	}
}
