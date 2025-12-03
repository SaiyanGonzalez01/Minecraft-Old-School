package net.minecraft.src;

import java.util.List;

public abstract class EntityPlayer extends EntityLiving {
	public InventoryPlayer inventory = new InventoryPlayer(this);
	public CraftingInventoryCB inventorySlots;
	public CraftingInventoryCB craftingInventory;
	public byte field_9371_f = 0;
	public int score = 0;
	public float field_775_e;
	public float field_774_f;
	public boolean isSwinging = false;
	public int swingProgressInt = 0;
	public String username;
	public int dimension;
	public String field_20067_q;
	public double field_20066_r;
	public double field_20065_s;
	public double field_20064_t;
	public double field_20063_u;
	public double field_20062_v;
	public double field_20061_w;
	private boolean field_21901_a;
	private ChunkCoordinates field_21908_b;
	private int field_21907_c;
	public float field_22063_x;
	public float field_22062_y;
	public float field_22061_z;
	private int damageRemainder = 0;
	public EntityFish fishEntity = null;

	public EntityPlayer(World var1) {
		super(var1);
		this.inventorySlots = new CraftingInventoryPlayerCB(this.inventory, !var1.multiplayerWorld);
		this.craftingInventory = this.inventorySlots;
		this.yOffset = 1.62F;
		ChunkCoordinates var2 = var1.func_22137_s();
		this.setLocationAndAngles((double)var2.field_22395_a + 0.5D, (double)(var2.field_22394_b + 1), (double)var2.field_22396_c + 0.5D, 0.0F, 0.0F);
		this.health = 20;
		this.field_9351_C = "humanoid";
		this.field_9353_B = 180.0F;
		this.fireResistance = 20;
		this.texture = "/mob/char.png";
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

	public void onUpdate() {
		if(this.isPlayerSleeping()) {
			++this.field_21907_c;
			if(this.field_21907_c > 100) {
				this.field_21907_c = 100;
			}

			if(!this.func_22057_q()) {
				this.func_22056_a(true, true);
			} else if(!this.worldObj.multiplayerWorld && this.worldObj.isDaytime()) {
				this.func_22056_a(false, true);
			}
		} else if(this.field_21907_c > 0) {
			++this.field_21907_c;
			if(this.field_21907_c >= 110) {
				this.field_21907_c = 0;
			}
		}

		super.onUpdate();
		if(!this.worldObj.multiplayerWorld && this.craftingInventory != null && !this.craftingInventory.func_20120_b(this)) {
			this.func_20059_m();
			this.craftingInventory = this.inventorySlots;
		}

		this.field_20066_r = this.field_20063_u;
		this.field_20065_s = this.field_20062_v;
		this.field_20064_t = this.field_20061_w;
		double var1 = this.posX - this.field_20063_u;
		double var3 = this.posY - this.field_20062_v;
		double var5 = this.posZ - this.field_20061_w;
		double var7 = 10.0D;
		if(var1 > var7) {
			this.field_20066_r = this.field_20063_u = this.posX;
		}

		if(var5 > var7) {
			this.field_20064_t = this.field_20061_w = this.posZ;
		}

		if(var3 > var7) {
			this.field_20065_s = this.field_20062_v = this.posY;
		}

		if(var1 < -var7) {
			this.field_20066_r = this.field_20063_u = this.posX;
		}

		if(var5 < -var7) {
			this.field_20064_t = this.field_20061_w = this.posZ;
		}

		if(var3 < -var7) {
			this.field_20065_s = this.field_20062_v = this.posY;
		}

		this.field_20063_u += var1 * 0.25D;
		this.field_20061_w += var5 * 0.25D;
		this.field_20062_v += var3 * 0.25D;
	}

	protected boolean func_22049_v() {
		return this.health <= 0 || this.isPlayerSleeping();
	}

	protected void func_20059_m() {
		this.craftingInventory = this.inventorySlots;
	}

	public void updateCloak() {
		this.field_20067_q = "http://s3.amazonaws.com/MinecraftCloaks/" + this.username + ".png";
		this.cloakUrl = this.field_20067_q;
	}

	public void updateRidden() {
		super.updateRidden();
		this.field_775_e = this.field_774_f;
		this.field_774_f = 0.0F;
	}

	public void preparePlayerToSpawn() {
		this.yOffset = 1.62F;
		this.setSize(0.6F, 1.8F);
		super.preparePlayerToSpawn();
		this.health = 20;
		this.deathTime = 0;
	}

	protected void updatePlayerActionState() {
		if(this.isSwinging) {
			++this.swingProgressInt;
			if(this.swingProgressInt == 8) {
				this.swingProgressInt = 0;
				this.isSwinging = false;
			}
		} else {
			this.swingProgressInt = 0;
		}

		this.swingProgress = (float)this.swingProgressInt / 8.0F;
	}

	public void onLivingUpdate() {
		if(this.worldObj.difficultySetting == 0 && this.health < 20 && this.ticksExisted % 20 * 12 == 0) {
			this.heal(1);
		}

		this.inventory.decrementAnimations();
		this.field_775_e = this.field_774_f;
		super.onLivingUpdate();
		float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		float var2 = (float)Math.atan(-this.motionY * (double)0.2F) * 15.0F;
		if(var1 > 0.1F) {
			var1 = 0.1F;
		}

		if(!this.onGround || this.health <= 0) {
			var1 = 0.0F;
		}

		if(this.onGround || this.health <= 0) {
			var2 = 0.0F;
		}

		this.field_774_f += (var1 - this.field_774_f) * 0.4F;
		this.field_9328_R += (var2 - this.field_9328_R) * 0.8F;
		if(this.health > 0) {
			List var3 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(1.0D, 0.0D, 1.0D));
			if(var3 != null) {
				for(int var4 = 0; var4 < var3.size(); ++var4) {
					Entity var5 = (Entity)var3.get(var4);
					if(!var5.isDead) {
						this.func_451_h(var5);
					}
				}
			}
		}

	}

	private void func_451_h(Entity var1) {
		var1.onCollideWithPlayer(this);
	}

	public int getScore() {
		return this.score;
	}

	public void onDeath(Entity var1) {
		super.onDeath(var1);
		this.setSize(0.2F, 0.2F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.motionY = (double)0.1F;
		if(this.username.equals("Notch")) {
			this.dropPlayerItemWithRandomChoice(new ItemStack(Item.appleRed, 1), true);
		}

		this.inventory.dropAllItems();
		if(var1 != null) {
			this.motionX = (double)(-MathHelper.cos((this.attackedAtYaw + this.rotationYaw) * (float)Math.PI / 180.0F) * 0.1F);
			this.motionZ = (double)(-MathHelper.sin((this.attackedAtYaw + this.rotationYaw) * (float)Math.PI / 180.0F) * 0.1F);
		} else {
			this.motionX = this.motionZ = 0.0D;
		}

		this.yOffset = 0.1F;
	}

	public void addToPlayerScore(Entity var1, int var2) {
		this.score += var2;
	}

	public void dropCurrentItem() {
		this.dropPlayerItemWithRandomChoice(this.inventory.decrStackSize(this.inventory.currentItem, 1), false);
	}

	public void dropPlayerItem(ItemStack var1) {
		this.dropPlayerItemWithRandomChoice(var1, false);
	}

	public void dropPlayerItemWithRandomChoice(ItemStack var1, boolean var2) {
		if(var1 != null) {
			EntityItem var3 = new EntityItem(this.worldObj, this.posX, this.posY - (double)0.3F + (double)this.getEyeHeight(), this.posZ, var1);
			var3.delayBeforeCanPickup = 40;
			float var4 = 0.1F;
			float var5;
			if(var2) {
				var5 = this.rand.nextFloat() * 0.5F;
				float var6 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
				var3.motionX = (double)(-MathHelper.sin(var6) * var5);
				var3.motionZ = (double)(MathHelper.cos(var6) * var5);
				var3.motionY = (double)0.2F;
			} else {
				var4 = 0.3F;
				var3.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * var4);
				var3.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * var4);
				var3.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI) * var4 + 0.1F);
				var4 = 0.02F;
				var5 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
				var4 *= this.rand.nextFloat();
				var3.motionX += Math.cos((double)var5) * (double)var4;
				var3.motionY += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
				var3.motionZ += Math.sin((double)var5) * (double)var4;
			}

			this.joinEntityItemWithWorld(var3);
		}
	}

	protected void joinEntityItemWithWorld(EntityItem var1) {
		this.worldObj.entityJoinedWorld(var1);
	}

	public float getCurrentPlayerStrVsBlock(Block var1) {
		float var2 = this.inventory.getStrVsBlock(var1);
		if(this.isInsideOfMaterial(Material.water)) {
			var2 /= 5.0F;
		}

		if(!this.onGround) {
			var2 /= 5.0F;
		}

		return var2;
	}

	public boolean canHarvestBlock(Block var1) {
		return this.inventory.canHarvestBlock(var1);
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		NBTTagList var2 = var1.getTagList("Inventory");
		this.inventory.readFromNBT(var2);
		this.dimension = var1.getInteger("Dimension");
		this.field_21901_a = var1.getBoolean("Sleeping");
		this.field_21907_c = var1.getShort("SleepTimer");
		if(this.field_21901_a) {
			this.field_21908_b = new ChunkCoordinates(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
			this.func_22056_a(true, true);
		}

	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		var1.setTag("Inventory", this.inventory.writeToNBT(new NBTTagList()));
		var1.setInteger("Dimension", this.dimension);
		var1.setBoolean("Sleeping", this.field_21901_a);
		var1.setShort("SleepTimer", (short)this.field_21907_c);
	}

	public void displayGUIChest(IInventory var1) {
	}

	public void displayWorkbenchGUI(int var1, int var2, int var3) {
	}

	public void onItemPickup(Entity var1, int var2) {
	}

	public float getEyeHeight() {
		return 0.12F;
	}

	protected void func_22058_C() {
		this.yOffset = 1.62F;
	}

	public boolean attackEntityFrom(Entity var1, int var2) {
		this.field_9344_ag = 0;
		if(this.health <= 0) {
			return false;
		} else {
			if(this.isPlayerSleeping()) {
				this.func_22056_a(true, true);
			}

			if(var1 instanceof EntityMobs || var1 instanceof EntityArrow) {
				if(this.worldObj.difficultySetting == 0) {
					var2 = 0;
				}

				if(this.worldObj.difficultySetting == 1) {
					var2 = var2 / 3 + 1;
				}

				if(this.worldObj.difficultySetting == 3) {
					var2 = var2 * 3 / 2;
				}
			}

			return var2 == 0 ? false : super.attackEntityFrom(var1, var2);
		}
	}

	protected void damageEntity(int var1) {
		int var2 = 25 - this.inventory.getTotalArmorValue();
		int var3 = var1 * var2 + this.damageRemainder;
		this.inventory.damageArmor(var1);
		var1 = var3 / 25;
		this.damageRemainder = var3 % 25;
		super.damageEntity(var1);
	}

	public void displayGUIFurnace(TileEntityFurnace var1) {
	}

	public void displayGUIDispenser(TileEntityDispenser var1) {
	}

	public void displayGUIEditSign(TileEntitySign var1) {
	}

	public void useCurrentItemOnEntity(Entity var1) {
		if(!var1.interact(this)) {
			ItemStack var2 = this.getCurrentEquippedItem();
			if(var2 != null && var1 instanceof EntityLiving) {
				var2.useItemOnEntity((EntityLiving)var1);
				if(var2.stackSize <= 0) {
					var2.func_1097_a(this);
					this.destroyCurrentEquippedItem();
				}
			}

		}
	}

	public ItemStack getCurrentEquippedItem() {
		return this.inventory.getCurrentItem();
	}

	public void destroyCurrentEquippedItem() {
		this.inventory.setInventorySlotContents(this.inventory.currentItem, (ItemStack)null);
	}

	public double getYOffset() {
		return (double)(this.yOffset - 0.5F);
	}

	public void swingItem() {
		this.swingProgressInt = -1;
		this.isSwinging = true;
	}

	public void attackTargetEntityWithCurrentItem(Entity var1) {
		int var2 = this.inventory.getDamageVsEntity(var1);
		if(var2 > 0) {
			var1.attackEntityFrom(this, var2);
			ItemStack var3 = this.getCurrentEquippedItem();
			if(var3 != null && var1 instanceof EntityLiving) {
				var3.hitEntity((EntityLiving)var1);
				if(var3.stackSize <= 0) {
					var3.func_1097_a(this);
					this.destroyCurrentEquippedItem();
				}
			}
		}

	}

	public void respawnPlayer() {
	}

	public abstract void func_6420_o();

	public void onItemStackChanged(ItemStack var1) {
	}

	public void setEntityDead() {
		super.setEntityDead();
		this.inventorySlots.onCraftGuiClosed(this);
		if(this.craftingInventory != null) {
			this.craftingInventory.onCraftGuiClosed(this);
		}

	}

	public boolean func_345_I() {
		return !this.field_21901_a && super.func_345_I();
	}

	public boolean func_22053_b(int var1, int var2, int var3) {
		if(!this.isPlayerSleeping() && this.isEntityAlive()) {
			if(this.worldObj.worldProvider.field_4220_c) {
				return false;
			} else if(this.worldObj.isDaytime()) {
				return false;
			} else if(Math.abs(this.posX - (double)var1) <= 3.0D && Math.abs(this.posY - (double)var2) <= 2.0D && Math.abs(this.posZ - (double)var3) <= 3.0D) {
				this.setSize(0.2F, 0.2F);
				this.yOffset = 0.2F;
				if(this.worldObj.blockExists(var1, var2, var3)) {
					int var4 = this.worldObj.getBlockMetadata(var1, var2, var3);
					int var5 = BlockBed.func_22030_c(var4);
					float var6 = 0.5F;
					float var7 = 0.5F;
					switch(var5) {
					case 0:
						var7 = 0.9F;
						break;
					case 1:
						var6 = 0.1F;
						break;
					case 2:
						var7 = 0.1F;
						break;
					case 3:
						var6 = 0.9F;
					}

					this.func_22052_e(var5);
					this.setPosition((double)((float)var1 + var6), (double)((float)var2 + 15.0F / 16.0F), (double)((float)var3 + var7));
				} else {
					this.setPosition((double)((float)var1 + 0.5F), (double)((float)var2 + 15.0F / 16.0F), (double)((float)var3 + 0.5F));
				}

				this.field_21901_a = true;
				this.field_21907_c = 0;
				this.field_21908_b = new ChunkCoordinates(var1, var2, var3);
				this.motionX = this.motionZ = this.motionY = 0.0D;
				if(!this.worldObj.multiplayerWorld) {
					this.worldObj.func_22140_w();
				}

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void func_22052_e(int var1) {
		this.field_22063_x = 0.0F;
		this.field_22061_z = 0.0F;
		switch(var1) {
		case 0:
			this.field_22061_z = -1.8F;
			break;
		case 1:
			this.field_22063_x = 1.8F;
			break;
		case 2:
			this.field_22061_z = 1.8F;
			break;
		case 3:
			this.field_22063_x = -1.8F;
		}

	}

	public void func_22056_a(boolean var1, boolean var2) {
		this.setSize(0.6F, 1.8F);
		this.func_22058_C();
		ChunkCoordinates var3 = this.field_21908_b;
		if(var3 != null && this.worldObj.getBlockId(var3.field_22395_a, var3.field_22394_b, var3.field_22396_c) == Block.field_9262_S.blockID) {
			BlockBed.func_22031_a(this.worldObj, var3.field_22395_a, var3.field_22394_b, var3.field_22396_c, false);
			ChunkCoordinates var4 = BlockBed.func_22028_g(this.worldObj, var3.field_22395_a, var3.field_22394_b, var3.field_22396_c, 0);
			this.setPosition((double)((float)var4.field_22395_a + 0.5F), (double)((float)var4.field_22394_b + this.yOffset + 0.1F), (double)((float)var4.field_22396_c + 0.5F));
		}

		this.field_21901_a = false;
		if(!this.worldObj.multiplayerWorld && var2) {
			this.worldObj.func_22140_w();
		}

		if(var1) {
			this.field_21907_c = 0;
		} else {
			this.field_21907_c = 100;
		}

	}

	private boolean func_22057_q() {
		return this.worldObj.getBlockId(this.field_21908_b.field_22395_a, this.field_21908_b.field_22394_b, this.field_21908_b.field_22396_c) == Block.field_9262_S.blockID;
	}

	public float func_22059_J() {
		if(this.field_21908_b != null) {
			int var1 = this.worldObj.getBlockMetadata(this.field_21908_b.field_22395_a, this.field_21908_b.field_22394_b, this.field_21908_b.field_22396_c);
			int var2 = BlockBed.func_22030_c(var1);
			switch(var2) {
			case 0:
				return 90.0F;
			case 1:
				return 0.0F;
			case 2:
				return 270.0F;
			case 3:
				return 180.0F;
			}
		}

		return 0.0F;
	}

	public boolean isPlayerSleeping() {
		return this.field_21901_a;
	}

	public boolean func_22054_L() {
		return this.field_21901_a && this.field_21907_c >= 100;
	}

	public int func_22060_M() {
		return this.field_21907_c;
	}

	public void func_22055_b(String var1) {
	}
}
