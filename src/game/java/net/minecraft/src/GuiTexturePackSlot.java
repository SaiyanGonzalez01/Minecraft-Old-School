package net.minecraft.src;

import java.util.List;
import org.lwjgl.opengl.GL11;

import dev.colbster937.eaglercraft.gui.GuiScreenTexturePackOptions;
import dev.colbster937.eaglercraft.rp.TexturePack;
import dev.colbster937.eaglercraft.utils.ScuffedUtils;
import net.minecraft.client.Minecraft;

class GuiTexturePackSlot extends GuiSlot {
	final GuiTexturePacks field_22265_a;

	public GuiTexturePackSlot(GuiTexturePacks var1) {
		super(GuiTexturePacks.func_22124_a(var1), var1.width, var1.height, 32, var1.height - 55 + 4, 36);
		this.field_22265_a = var1;
	}

	protected int func_22249_a() {
		return TexturePack.getTexturePacks().size();
	}

	protected void func_22247_a(int var1, boolean var2) {
		TexturePack pack = TexturePack.getTexturePacks().get(var1);
		if (!TexturePack.isDefaultPack(pack) && !ScuffedUtils.isShiftKeyDown()) Minecraft.getMinecraft().displayGuiScreen(new GuiScreenTexturePackOptions(this.field_22265_a, pack));
		else TexturePack.setSelectedPack(pack);
	}

	protected boolean func_22246_a(int var1) {
		return TexturePack.isSelectedPack(var1);
	}

	protected int func_22245_b() {
		return this.func_22249_a() * 36;
	}

	protected void func_22248_c() {
		this.field_22265_a.drawDefaultBackground();
	}

	protected void func_22242_a(int var1, int var2, int var3, int var4, Tessellator var5) {
		TexturePack var6 = (TexturePack)TexturePack.getTexturePacks().get(var1);
		var6.bindIconTexture();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		var5.startDrawingQuads();
		var5.setColorOpaque_I(16777215);
		var5.addVertexWithUV((double)var2, (double)(var3 + var4), 0.0D, 0.0D, 1.0D);
		var5.addVertexWithUV((double)(var2 + 32), (double)(var3 + var4), 0.0D, 1.0D, 1.0D);
		var5.addVertexWithUV((double)(var2 + 32), (double)var3, 0.0D, 1.0D, 0.0D);
		var5.addVertexWithUV((double)var2, (double)var3, 0.0D, 0.0D, 0.0D);
		var5.draw();
		this.field_22265_a.drawString(GuiTexturePacks.func_22127_j(this.field_22265_a), var6.getName(), var2 + 32 + 2, var3 + 1, 16777215);
		String[] desc = var6.getDescription();
		this.field_22265_a.drawString(GuiTexturePacks.func_22120_k(this.field_22265_a), desc[0], var2 + 32 + 2, var3 + 12, 8421504);
		this.field_22265_a.drawString(GuiTexturePacks.func_22125_l(this.field_22265_a), desc[1], var2 + 32 + 2, var3 + 12 + 10, 8421504);
	}
}
