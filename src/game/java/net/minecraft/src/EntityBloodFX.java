package net.minecraft.src;

public class EntityBloodFX extends EntityFX {
	public EntityBloodFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
		super(var1, var2, var4, var6, var8, var10, var12);
		this.particleRed = 1.0F;
		this.particleGreen = 1.0F;
		this.particleBlue = 1.0F;
		this.particleTextureIndex = 80 + this.rand.nextInt(8);
		this.particleGravity = 1.0F;
		this.particleScale *= 0.75F;
		this.particleMaxAge = 40 + this.rand.nextInt(20);
	}

	public int getFXLayer() {
		return 0;
	}
}
