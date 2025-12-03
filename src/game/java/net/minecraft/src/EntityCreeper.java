package net.minecraft.src;

public class EntityCreeper extends EntityMobs {
	int timeSinceIgnited;
	int lastActiveTime;

	public EntityCreeper(World var1) {
		super(var1);
		this.texture = "/mob/creeper.png";
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte)-1));
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
	}

	public void onUpdate() {
		this.lastActiveTime = this.timeSinceIgnited;
		if(this.worldObj.multiplayerWorld) {
			int var1 = this.func_21091_q();
			if(var1 > 0 && this.timeSinceIgnited == 0) {
				this.worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 0.5F);
			}

			this.timeSinceIgnited += var1;
			if(this.timeSinceIgnited < 0) {
				this.timeSinceIgnited = 0;
			}

			if(this.timeSinceIgnited >= 30) {
				this.timeSinceIgnited = 30;
			}
		}

		super.onUpdate();
	}

	protected String getHurtSound() {
		return "mob.creeper";
	}

	protected String getDeathSound() {
		return "mob.creeperdeath";
	}

	public void onDeath(Entity var1) {
		super.onDeath(var1);
		if(var1 instanceof EntitySkeleton) {
			this.dropItem(Item.record13.shiftedIndex + this.rand.nextInt(2), 1);
		}

	}

	protected void attackEntity(Entity var1, float var2) {
		int var3 = this.func_21091_q();
		if(var3 <= 0 && var2 < 3.0F || var3 > 0 && var2 < 7.0F) {
			if(this.timeSinceIgnited == 0) {
				this.worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 0.5F);
			}

			this.func_21090_e(1);
			++this.timeSinceIgnited;
			if(this.timeSinceIgnited >= 30) {
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 3.0F);
				this.setEntityDead();
			}

			this.hasAttacked = true;
		} else {
			this.func_21090_e(-1);
			--this.timeSinceIgnited;
			if(this.timeSinceIgnited < 0) {
				this.timeSinceIgnited = 0;
			}
		}

	}

	public float func_440_b(float var1) {
		return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * var1) / 28.0F;
	}

	protected int getDropItemId() {
		return Item.gunpowder.shiftedIndex;
	}

	private int func_21091_q() {
		return this.dataWatcher.getWatchableObjectByte(16);
	}

	private void func_21090_e(int var1) {
		this.dataWatcher.updateObject(16, Byte.valueOf((byte)var1));
	}
}
