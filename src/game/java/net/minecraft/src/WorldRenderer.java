package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class WorldRenderer {
	public World worldObj;
	private int glRenderList = -1;
	private static Tessellator tessellator = Tessellator.instance;
	public static int chunksUpdated = 0;
	public int posX;
	public int posY;
	public int posZ;
	public int sizeWidth;
	public int sizeHeight;
	public int sizeDepth;
	public int field_1755_i;
	public int field_1754_j;
	public int field_1753_k;
	public int field_1752_l;
	public int field_1751_m;
	public int field_1750_n;
	public boolean isInFrustum = false;
	public boolean[] skipRenderPass = new boolean[2];
	public int field_1746_q;
	public int field_1743_r;
	public int field_1741_s;
	public float field_1740_t;
	public boolean needsUpdate;
	public AxisAlignedBB field_1736_v;
	public int field_1735_w;
	public boolean isVisible = true;
	public boolean isWaitingOnOcclusionQuery;
	public int field_1732_z;
	public boolean field_1747_A;
	private boolean isInitialized = false;
	public List tileEntityRenderers = new ArrayList();
	private List tileEntities;

	public WorldRenderer(World var1, List var2, int var3, int var4, int var5, int var6, int var7) {
		this.worldObj = var1;
		this.tileEntities = var2;
		this.sizeWidth = this.sizeHeight = this.sizeDepth = var6;
		this.field_1740_t = MathHelper.sqrt_float((float)(this.sizeWidth * this.sizeWidth + this.sizeHeight * this.sizeHeight + this.sizeDepth * this.sizeDepth)) / 2.0F;
		this.glRenderList = var7;
		this.posX = -999;
		this.func_1197_a(var3, var4, var5);
		this.needsUpdate = false;
	}

	public void func_1197_a(int var1, int var2, int var3) {
		if(var1 != this.posX || var2 != this.posY || var3 != this.posZ) {
			this.setDontDraw();
			this.posX = var1;
			this.posY = var2;
			this.posZ = var3;
			this.field_1746_q = var1 + this.sizeWidth / 2;
			this.field_1743_r = var2 + this.sizeHeight / 2;
			this.field_1741_s = var3 + this.sizeDepth / 2;
			this.field_1752_l = var1 & 1023;
			this.field_1751_m = var2;
			this.field_1750_n = var3 & 1023;
			this.field_1755_i = var1 - this.field_1752_l;
			this.field_1754_j = var2 - this.field_1751_m;
			this.field_1753_k = var3 - this.field_1750_n;
			float var4 = 6.0F;
			this.field_1736_v = AxisAlignedBB.getBoundingBox((double)((float)var1 - var4), (double)((float)var2 - var4), (double)((float)var3 - var4), (double)((float)(var1 + this.sizeWidth) + var4), (double)((float)(var2 + this.sizeHeight) + var4), (double)((float)(var3 + this.sizeDepth) + var4));
			GL11.glNewList(this.glRenderList + 2, GL11.GL_COMPILE);
			RenderItem.renderAABB(AxisAlignedBB.getBoundingBoxFromPool((double)((float)this.field_1752_l - var4), (double)((float)this.field_1751_m - var4), (double)((float)this.field_1750_n - var4), (double)((float)(this.field_1752_l + this.sizeWidth) + var4), (double)((float)(this.field_1751_m + this.sizeHeight) + var4), (double)((float)(this.field_1750_n + this.sizeDepth) + var4)));
			GL11.glEndList();
			this.markDirty();
		}
	}

	private void setupGLTranslation() {
		GL11.glTranslatef((float)this.field_1752_l, (float)this.field_1751_m, (float)this.field_1750_n);
	}

	public void updateRenderer() {
		if(this.needsUpdate) {
			++chunksUpdated;
			int var1 = this.posX;
			int var2 = this.posY;
			int var3 = this.posZ;
			int var4 = this.posX + this.sizeWidth;
			int var5 = this.posY + this.sizeHeight;
			int var6 = this.posZ + this.sizeDepth;

			for(int var7 = 0; var7 < 2; ++var7) {
				this.skipRenderPass[var7] = true;
			}

			Chunk.isLit = false;
			HashSet var21 = new HashSet();
			var21.addAll(this.tileEntityRenderers);
			this.tileEntityRenderers.clear();
			byte var8 = 1;
			ChunkCache var9 = new ChunkCache(this.worldObj, var1 - var8, var2 - var8, var3 - var8, var4 + var8, var5 + var8, var6 + var8);
			RenderBlocks var10 = new RenderBlocks(var9);

			for(int var11 = 0; var11 < 2; ++var11) {
				boolean var12 = false;
				boolean var13 = false;
				boolean var14 = false;

				for(int var15 = var2; var15 < var5; ++var15) {
					for(int var16 = var3; var16 < var6; ++var16) {
						for(int var17 = var1; var17 < var4; ++var17) {
							int var18 = var9.getBlockId(var17, var15, var16);
							if(var18 > 0) {
								if(!var14) {
									var14 = true;
									GL11.glNewList(this.glRenderList + var11, GL11.GL_COMPILE);
									GL11.glPushMatrix();
									this.setupGLTranslation();
									float var19 = 1.000001F;
									GL11.glTranslatef((float)(-this.sizeDepth) / 2.0F, (float)(-this.sizeHeight) / 2.0F, (float)(-this.sizeDepth) / 2.0F);
									GL11.glScalef(var19, var19, var19);
									GL11.glTranslatef((float)this.sizeDepth / 2.0F, (float)this.sizeHeight / 2.0F, (float)this.sizeDepth / 2.0F);
									tessellator.startDrawingQuads();
									tessellator.setTranslationD((double)(field_1752_l-this.posX), (double)(field_1751_m-this.posY), (double)(field_1750_n-this.posZ));
								}

								if(var11 == 0 && Block.isBlockContainer[var18]) {
									TileEntity var23 = var9.getBlockTileEntity(var17, var15, var16);
									if(TileEntityRenderer.instance.hasSpecialRenderer(var23)) {
										this.tileEntityRenderers.add(var23);
									}
								}

								Block var24 = Block.blocksList[var18];
								int var20 = var24.getRenderBlockPass();
								if(var20 != var11) {
									var12 = true;
								} else if(var20 == var11) {
									var13 |= var10.renderBlockByRenderType(var24, var17, var15, var16);
								}
							}
						}
					}
				}

				if(var14) {
					tessellator.draw();
					GL11.glPopMatrix();
					GL11.glEndList();
					tessellator.setTranslationD(0.0D, 0.0D, 0.0D);
				} else {
					var13 = false;
				}

				if(var13) {
					this.skipRenderPass[var11] = false;
				}

				if(!var12) {
					break;
				}
			}

			HashSet var22 = new HashSet();
			var22.addAll(this.tileEntityRenderers);
			var22.removeAll(var21);
			this.tileEntities.addAll(var22);
			var21.removeAll(this.tileEntityRenderers);
			this.tileEntities.removeAll(var21);
			this.field_1747_A = Chunk.isLit;
			this.isInitialized = true;
		}
	}

	public float distanceToEntity(Entity var1) {
		float var2 = (float)(var1.posX - (double)this.field_1746_q);
		float var3 = (float)(var1.posY - (double)this.field_1743_r);
		float var4 = (float)(var1.posZ - (double)this.field_1741_s);
		return var2 * var2 + var3 * var3 + var4 * var4;
	}

	public void setDontDraw() {
		for(int var1 = 0; var1 < 2; ++var1) {
			this.skipRenderPass[var1] = true;
		}

		this.isInFrustum = false;
		this.isInitialized = false;
	}

	public void func_1204_c() {
		this.setDontDraw();
		this.worldObj = null;
	}

	public int getGLCallListForPass(int var1) {
		return !this.isInFrustum ? -1 : (!this.skipRenderPass[var1] ? this.glRenderList + var1 : -1);
	}

	public void updateInFrustrum(ICamera var1) {
		this.isInFrustum = var1.isBoundingBoxInFrustum(this.field_1736_v);
	}

	public void callOcclusionQueryList() {
		GL11.glCallList(this.glRenderList + 2);
	}

	public boolean canRender() {
		return !this.isInitialized ? false : this.skipRenderPass[0] && this.skipRenderPass[1];
	}

	public void markDirty() {
		this.needsUpdate = true;
	}
}
