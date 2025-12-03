package net.minecraft.src;

import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class GuiSlot {
	private final Minecraft field_22264_a;
	private final int field_22263_b;
	private final int field_22262_c;
	private final int field_22261_d;
	private final int field_22260_e;
	private final int field_22259_f;
	private final int field_22258_g;
	private final int field_22257_h;
	private int field_22256_i;
	private int field_22255_j;
	private float field_22254_k = -2.0F;
	private float field_22253_l;
	private float field_22252_m;
	private int field_22251_n = -1;
	private long field_22250_o = 0L;

	public GuiSlot(Minecraft var1, int var2, int var3, int var4, int var5, int var6) {
		this.field_22264_a = var1;
		this.field_22263_b = var2;
		this.field_22262_c = var3;
		this.field_22261_d = var4;
		this.field_22260_e = var5;
		this.field_22257_h = var6;
		this.field_22258_g = 0;
		this.field_22259_f = var2;
	}

	protected abstract int func_22249_a();

	protected abstract void func_22247_a(int var1, boolean var2);

	protected abstract boolean func_22246_a(int var1);

	protected abstract int func_22245_b();

	protected abstract void func_22248_c();

	protected abstract void func_22242_a(int var1, int var2, int var3, int var4, Tessellator var5);

	public void func_22240_a(List var1, int var2, int var3) {
		this.field_22256_i = var2;
		this.field_22255_j = var3;
	}

	private void func_22244_d() {
		int var1 = this.func_22245_b() - (this.field_22260_e - this.field_22261_d - 4);
		if(var1 < 0) {
			var1 /= 2;
		}

		if(this.field_22252_m < 0.0F) {
			this.field_22252_m = 0.0F;
		}

		if(this.field_22252_m > (float)var1) {
			this.field_22252_m = (float)var1;
		}

	}

	public void func_22241_a(GuiButton var1) {
		if(var1.enabled) {
			if(var1.id == this.field_22256_i) {
				this.field_22252_m -= (float)(this.field_22257_h * 2 / 3);
				this.field_22254_k = -2.0F;
				this.func_22244_d();
			} else if(var1.id == this.field_22255_j) {
				this.field_22252_m += (float)(this.field_22257_h * 2 / 3);
				this.field_22254_k = -2.0F;
				this.func_22244_d();
			}

		}
	}

	public void func_22243_a(int var1, int var2, float var3) {
		this.func_22248_c();
		int var4 = this.func_22249_a();
		int var5 = this.field_22263_b / 2 + 124;
		int var6 = var5 + 6;
		int var9;
		int var11;
		int var18;
		if(Mouse.isButtonDown(0)) {
			if(this.field_22254_k == -1.0F) {
				if(var2 >= this.field_22261_d && var2 <= this.field_22260_e) {
					int var7 = this.field_22263_b / 2 - 110;
					int var8 = this.field_22263_b / 2 + 110;
					var9 = (var2 - this.field_22261_d + (int)this.field_22252_m - 2) / this.field_22257_h;
					if(var1 >= var7 && var1 <= var8 && var9 >= 0 && var9 < var4) {
						boolean var10 = var9 == this.field_22251_n && System.currentTimeMillis() - this.field_22250_o < 250L;
						this.func_22247_a(var9, var10);
						this.field_22251_n = var9;
						this.field_22250_o = System.currentTimeMillis();
					}

					if(var1 >= var5 && var1 <= var6) {
						this.field_22253_l = -1.0F;
						var18 = this.func_22245_b() - (this.field_22260_e - this.field_22261_d - 4);
						if(var18 < 1) {
							var18 = 1;
						}

						var11 = (this.field_22260_e - this.field_22261_d) * (this.field_22260_e - this.field_22261_d) / this.func_22245_b();
						if(var11 < 32) {
							var11 = 32;
						}

						if(var11 > this.field_22260_e - this.field_22261_d - 8) {
							var11 = this.field_22260_e - this.field_22261_d - 8;
						}

						this.field_22253_l /= (float)(this.field_22260_e - this.field_22261_d - var11) / (float)var18;
					} else {
						this.field_22253_l = 1.0F;
					}

					this.field_22254_k = (float)var2;
				} else {
					this.field_22254_k = -2.0F;
				}
			} else if(this.field_22254_k >= 0.0F) {
				this.field_22252_m -= ((float)var2 - this.field_22254_k) * this.field_22253_l;
				this.field_22254_k = (float)var2;
			}
		} else {
			this.field_22254_k = -1.0F;
		}

		this.func_22244_d();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		Tessellator var15 = Tessellator.instance;
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.field_22264_a.renderEngine.getTexture("/gui/background.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float var16 = 32.0F;
		var15.startDrawingQuads();
		var15.setColorOpaque_I(2105376);
		var15.addVertexWithUV((double)this.field_22258_g, (double)this.field_22260_e, 0.0D, (double)((float)this.field_22258_g / var16), (double)((float)(this.field_22260_e + (int)this.field_22252_m) / var16));
		var15.addVertexWithUV((double)this.field_22259_f, (double)this.field_22260_e, 0.0D, (double)((float)this.field_22259_f / var16), (double)((float)(this.field_22260_e + (int)this.field_22252_m) / var16));
		var15.addVertexWithUV((double)this.field_22259_f, (double)this.field_22261_d, 0.0D, (double)((float)this.field_22259_f / var16), (double)((float)(this.field_22261_d + (int)this.field_22252_m) / var16));
		var15.addVertexWithUV((double)this.field_22258_g, (double)this.field_22261_d, 0.0D, (double)((float)this.field_22258_g / var16), (double)((float)(this.field_22261_d + (int)this.field_22252_m) / var16));
		var15.draw();

		for(var9 = 0; var9 < var4; ++var9) {
			var18 = this.field_22263_b / 2 - 92 - 16;
			var11 = this.field_22261_d + 4 + var9 * this.field_22257_h - (int)this.field_22252_m;
			byte var12 = 32;
			if(this.func_22246_a(var9)) {
				int var13 = this.field_22263_b / 2 - 110;
				int var14 = this.field_22263_b / 2 + 110;
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				var15.startDrawingQuads();
				var15.setColorOpaque_I(8421504);
				var15.addVertexWithUV((double)var13, (double)(var11 + var12 + 2), 0.0D, 0.0D, 1.0D);
				var15.addVertexWithUV((double)var14, (double)(var11 + var12 + 2), 0.0D, 1.0D, 1.0D);
				var15.addVertexWithUV((double)var14, (double)(var11 - 2), 0.0D, 1.0D, 0.0D);
				var15.addVertexWithUV((double)var13, (double)(var11 - 2), 0.0D, 0.0D, 0.0D);
				var15.setColorOpaque_I(0);
				var15.addVertexWithUV((double)(var13 + 1), (double)(var11 + var12 + 1), 0.0D, 0.0D, 1.0D);
				var15.addVertexWithUV((double)(var14 - 1), (double)(var11 + var12 + 1), 0.0D, 1.0D, 1.0D);
				var15.addVertexWithUV((double)(var14 - 1), (double)(var11 - 1), 0.0D, 1.0D, 0.0D);
				var15.addVertexWithUV((double)(var13 + 1), (double)(var11 - 1), 0.0D, 0.0D, 0.0D);
				var15.draw();
				GL11.glEnable(GL11.GL_TEXTURE_2D);
			}

			this.func_22242_a(var9, var18, var11, var12, var15);
		}

		byte var17 = 4;
		this.func_22239_a(0, this.field_22261_d, 255, 255);
		this.func_22239_a(this.field_22260_e, this.field_22262_c, 255, 255);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		var15.startDrawingQuads();
		var15.setColorRGBA_I(0, 0);
		var15.addVertexWithUV((double)this.field_22258_g, (double)(this.field_22261_d + var17), 0.0D, 0.0D, 1.0D);
		var15.addVertexWithUV((double)this.field_22259_f, (double)(this.field_22261_d + var17), 0.0D, 1.0D, 1.0D);
		var15.setColorRGBA_I(0, 255);
		var15.addVertexWithUV((double)this.field_22259_f, (double)this.field_22261_d, 0.0D, 1.0D, 0.0D);
		var15.addVertexWithUV((double)this.field_22258_g, (double)this.field_22261_d, 0.0D, 0.0D, 0.0D);
		var15.draw();
		var15.startDrawingQuads();
		var15.setColorRGBA_I(0, 255);
		var15.addVertexWithUV((double)this.field_22258_g, (double)this.field_22260_e, 0.0D, 0.0D, 1.0D);
		var15.addVertexWithUV((double)this.field_22259_f, (double)this.field_22260_e, 0.0D, 1.0D, 1.0D);
		var15.setColorRGBA_I(0, 0);
		var15.addVertexWithUV((double)this.field_22259_f, (double)(this.field_22260_e - var17), 0.0D, 1.0D, 0.0D);
		var15.addVertexWithUV((double)this.field_22258_g, (double)(this.field_22260_e - var17), 0.0D, 0.0D, 0.0D);
		var15.draw();
		var18 = this.func_22245_b() - (this.field_22260_e - this.field_22261_d - 4);
		if(var18 > 0) {
			var11 = (this.field_22260_e - this.field_22261_d) * (this.field_22260_e - this.field_22261_d) / this.func_22245_b();
			if(var11 < 32) {
				var11 = 32;
			}

			if(var11 > this.field_22260_e - this.field_22261_d - 8) {
				var11 = this.field_22260_e - this.field_22261_d - 8;
			}

			int var19 = (int)this.field_22252_m * (this.field_22260_e - this.field_22261_d - var11) / var18 + this.field_22261_d;
			if(var19 < this.field_22261_d) {
				var19 = this.field_22261_d;
			}

			var15.startDrawingQuads();
			var15.setColorRGBA_I(0, 255);
			var15.addVertexWithUV((double)var5, (double)this.field_22260_e, 0.0D, 0.0D, 1.0D);
			var15.addVertexWithUV((double)var6, (double)this.field_22260_e, 0.0D, 1.0D, 1.0D);
			var15.addVertexWithUV((double)var6, (double)this.field_22261_d, 0.0D, 1.0D, 0.0D);
			var15.addVertexWithUV((double)var5, (double)this.field_22261_d, 0.0D, 0.0D, 0.0D);
			var15.draw();
			var15.startDrawingQuads();
			var15.setColorRGBA_I(8421504, 255);
			var15.addVertexWithUV((double)var5, (double)(var19 + var11), 0.0D, 0.0D, 1.0D);
			var15.addVertexWithUV((double)var6, (double)(var19 + var11), 0.0D, 1.0D, 1.0D);
			var15.addVertexWithUV((double)var6, (double)var19, 0.0D, 1.0D, 0.0D);
			var15.addVertexWithUV((double)var5, (double)var19, 0.0D, 0.0D, 0.0D);
			var15.draw();
			var15.startDrawingQuads();
			var15.setColorRGBA_I(12632256, 255);
			var15.addVertexWithUV((double)var5, (double)(var19 + var11 - 1), 0.0D, 0.0D, 1.0D);
			var15.addVertexWithUV((double)(var6 - 1), (double)(var19 + var11 - 1), 0.0D, 1.0D, 1.0D);
			var15.addVertexWithUV((double)(var6 - 1), (double)var19, 0.0D, 1.0D, 0.0D);
			var15.addVertexWithUV((double)var5, (double)var19, 0.0D, 0.0D, 0.0D);
			var15.draw();
		}

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}

	private void func_22239_a(int var1, int var2, int var3, int var4) {
		Tessellator var5 = Tessellator.instance;
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.field_22264_a.renderEngine.getTexture("/gui/background.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float var6 = 32.0F;
		var5.startDrawingQuads();
		var5.setColorRGBA_I(4210752, var4);
		var5.addVertexWithUV(0.0D, (double)var2, 0.0D, 0.0D, (double)((float)var2 / var6));
		var5.addVertexWithUV((double)this.field_22263_b, (double)var2, 0.0D, (double)((float)this.field_22263_b / var6), (double)((float)var2 / var6));
		var5.setColorRGBA_I(4210752, var3);
		var5.addVertexWithUV((double)this.field_22263_b, (double)var1, 0.0D, (double)((float)this.field_22263_b / var6), (double)((float)var1 / var6));
		var5.addVertexWithUV(0.0D, (double)var1, 0.0D, 0.0D, (double)((float)var1 / var6));
		var5.draw();
	}
}
