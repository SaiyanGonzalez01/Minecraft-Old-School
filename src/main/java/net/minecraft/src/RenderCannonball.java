package net.minecraft.src;

import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz585.opengl.GL12;
import net.PeytonPlayz585.textures.TextureLocation;

public class RenderCannonball extends Render {
	
	private static final TextureLocation itemTexture = new TextureLocation("/gui/items.png");
	private int itemIconIndex;

	public RenderCannonball(int var1) {
		this.itemIconIndex = var1;
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)var2, (float)var4, (float)var6);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		itemTexture.bindTexture();
		Tessellator var10 = Tessellator.instance;
		float var11 = (float)(this.itemIconIndex % 16 * 16 + 0) / 256.0F;
		float var12 = (float)(this.itemIconIndex % 16 * 16 + 16) / 256.0F;
		float var13 = (float)(this.itemIconIndex / 16 * 16 + 0) / 256.0F;
		float var14 = (float)(this.itemIconIndex / 16 * 16 + 16) / 256.0F;
		float var15 = 1.0F;
		float var16 = 0.5F;
		float var17 = 0.25F;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		var10.startDrawingQuads();
		var10.setNormal(0.0F, 1.0F, 0.0F);
		var10.addVertexWithUV((double)(0.0F - var16), (double)(0.0F - var17), 0.0D, (double)var11, (double)var14);
		var10.addVertexWithUV((double)(var15 - var16), (double)(0.0F - var17), 0.0D, (double)var12, (double)var14);
		var10.addVertexWithUV((double)(var15 - var16), (double)(1.0F - var17), 0.0D, (double)var12, (double)var13);
		var10.addVertexWithUV((double)(0.0F - var16), (double)(1.0F - var17), 0.0D, (double)var11, (double)var13);
		var10.draw();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}
	
	@Override
	protected boolean loadDownloadableImageTexture(String s, String s1) {
		return true;
	}
}
