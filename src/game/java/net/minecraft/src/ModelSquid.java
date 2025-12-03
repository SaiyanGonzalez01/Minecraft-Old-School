package net.minecraft.src;

public class ModelSquid extends ModelBase {
	ModelRenderer field_21123_a;
	ModelRenderer[] field_21122_b = new ModelRenderer[8];

	public ModelSquid() {
		byte var1 = -16;
		this.field_21123_a = new ModelRenderer(0, 0);
		this.field_21123_a.addBox(-6.0F, -8.0F, -6.0F, 12, 16, 12);
		this.field_21123_a.offsetY += (float)(24 + var1);

		for(int var2 = 0; var2 < this.field_21122_b.length; ++var2) {
			this.field_21122_b[var2] = new ModelRenderer(48, 0);
			double var3 = (double)var2 * Math.PI * 2.0D / (double)this.field_21122_b.length;
			float var5 = (float)Math.cos(var3) * 5.0F;
			float var6 = (float)Math.sin(var3) * 5.0F;
			this.field_21122_b[var2].addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2);
			this.field_21122_b[var2].offsetX = var5;
			this.field_21122_b[var2].offsetZ = var6;
			this.field_21122_b[var2].offsetY = (float)(31 + var1);
			var3 = (double)var2 * Math.PI * -2.0D / (double)this.field_21122_b.length + Math.PI * 0.5D;
			this.field_21122_b[var2].rotateAngleY = (float)var3;
		}

	}

	public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
		for(int var7 = 0; var7 < this.field_21122_b.length; ++var7) {
			this.field_21122_b[var7].rotateAngleX = var3;
		}

	}

	public void render(float var1, float var2, float var3, float var4, float var5, float var6) {
		this.setRotationAngles(var1, var2, var3, var4, var5, var6);
		this.field_21123_a.render(var6);

		for(int var7 = 0; var7 < this.field_21122_b.length; ++var7) {
			this.field_21122_b[var7].render(var6);
		}

	}
}
