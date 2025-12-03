package net.minecraft.src;

public class EntityPig extends EntityAnimals {
	public EntityPig(World var1) {
		super(var1);
		this.texture = "/mob/pig.png";
		this.setSize(0.9F, 0.9F);
	}

	protected void entityInit() {
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		var1.setBoolean("Saddle", this.func_21068_q());
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		this.func_21069_a(var1.getBoolean("Saddle"));
	}

	protected String getLivingSound() {
		return "mob.pig";
	}

	protected String getHurtSound() {
		return "mob.pig";
	}

	protected String getDeathSound() {
		return "mob.pigdeath";
	}

	public boolean interact(EntityPlayer var1) {
		if(!this.func_21068_q() || this.worldObj.multiplayerWorld || this.riddenByEntity != null && this.riddenByEntity != var1) {
			return false;
		} else {
			var1.mountEntity(this);
			return true;
		}
	}

	protected int getDropItemId() {
		return Item.porkRaw.shiftedIndex;
	}

	public boolean func_21068_q() {
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public void func_21069_a(boolean var1) {
		if(var1) {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
		} else {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
		}

	}
}
