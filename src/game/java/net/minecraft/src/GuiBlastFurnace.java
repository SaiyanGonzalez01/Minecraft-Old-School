package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class GuiBlastFurnace extends GuiContainer {
	private TileEntityBlastFurnace blastFurnaceInventory;

	public GuiBlastFurnace(InventoryPlayer var1, TileEntityBlastFurnace var2) {
		super(new CraftingInventoryBlastFurnaceCB(var1, var2));
		this.blastFurnaceInventory = var2;
	}

	protected void drawGuiContainerForegroundLayer() {
		this.fontRenderer.drawStringWithShadow("Blast Furnace", 55, 6, 16777215);
		this.fontRenderer.drawStringWithShadow("Inventory", 63, this.ySize - 96 + 2, 16777215);
	}

	protected void drawGuiContainerBackgroundLayer(float var1) {
		int var2 = this.mc.renderEngine.getTexture("/gui/furnace.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var2);
		int var3 = (this.width - this.xSize) / 2;
		int var4 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var3, var4, 0, 0, this.xSize, this.ySize);
		int var5;
		if(this.blastFurnaceInventory.isBurning()) {
			var5 = this.blastFurnaceInventory.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(var3 + 56, var4 + 36 + 12 - var5, 176, 12 - var5, 14, var5 + 2);
		}

		var5 = this.blastFurnaceInventory.getCookProgressScaled(24);
		this.drawTexturedModalRect(var3 + 79, var4 + 34, 176, 14, var5 + 1, 16);
	}
}
