package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public class EntityWraith extends EntityMobs {

	public EntityWraith(World world) {
		super(world);
		//texture = "/mob/wraith.png";
		moveSpeed = 0.5F;
		attackStrength = 3;
	}

	public void onLivingUpdate() {
		if (worldObj.isDaytime()) {
			float f = getEntityBrightness(1.0F);
			if (f > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY),
					MathHelper.floor_double(posZ)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F) {
				fire = 300;
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
}
