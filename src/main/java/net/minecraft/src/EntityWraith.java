package net.minecraft.src;

public class EntityWraith extends EntityMob {
	public EntityWraith(World var1) {
		super(var1);
		this.moveSpeed = 1.0F;
		this.attackStrength = 4;
	}

	public void onLivingUpdate() {
		if(this.worldObj.isDaytime()) {
			float var1 = this.getEntityBrightness(1.0F);
			if(var1 > 0.5F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F) {
				this.fire = 300;
			}
		}

		super.onLivingUpdate();
	}

	protected String getLivingSound() {
		return "mob.wraith";
	}

	protected String getHurtSound() {
		return "mob.wraithhurt";
	}

	protected String getDeathSound() {
		return "mob.wraithdeath";
	}

	protected int getDropItemId() {
		return Item.feather.shiftedIndex;
	}
	
}
