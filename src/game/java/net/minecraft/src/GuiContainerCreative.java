package net.minecraft.src;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiContainerCreative extends GuiContainer {
	private static InventoryBasic field_35311_f = new InventoryBasic("tmp", 72);
	private float field_35312_g = 0.0F;
	private boolean field_35313_h = false;
	private boolean field_35314_i;

	public GuiContainerCreative(EntityPlayer var1) {
		super(new ContainerCreative(var1));
		var1.craftingInventory = this.inventorySlots;
		this.ySize = 208;
	}

	public void updateScreen() {
		if (!this.mc.playerController.func_35640_h()) {
			this.mc.displayGuiScreen(new GuiInventory(this.mc.thePlayer));
		}

	}



	public void initGui() {
		if (!this.mc.playerController.func_35640_h()) {
			this.mc.displayGuiScreen(new GuiInventory(this.mc.thePlayer));
		}

		this.controlList.clear();
	}

	protected void drawGuiContainerForegroundLayer() {
		this.fontRenderer.drawStringWithShadow("Item Selection", 8, 6, 4210752);
	}

	public void handleMouseInput() {
		super.handleMouseInput();
		int var1 = Mouse.getEventDWheel();
		if (var1 != 0) {
			int var2 = ((ContainerCreative) this.inventorySlots).field_35375_a.size() / 8 - 8 + 1;
			if (var1 > 0) {
				var1 = 1;
			}

			if (var1 < 0) {
				var1 = -1;
			}

			this.field_35312_g = (float) ((double) this.field_35312_g - (double) var1 / (double) var2);
			if (this.field_35312_g < 0.0F) {
				this.field_35312_g = 0.0F;
			}

			if (this.field_35312_g > 1.0F) {
				this.field_35312_g = 1.0F;
			}

			((ContainerCreative) this.inventorySlots).func_35374_a(this.field_35312_g);
		}

	}

	public void drawScreen(int var1, int var2, float var3) {
		boolean var4 = Mouse.isButtonDown(0);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		int var7 = var5 + 155;
		int var8 = var6 + 17;
		int var9 = var7 + 14;
		int var10 = var8 + 160 + 2;
		if (!this.field_35314_i && var4 && var1 >= var7 && var2 >= var8 && var1 < var9 && var2 < var10) {
			this.field_35313_h = true;
		}

		if (!var4) {
			this.field_35313_h = false;
		}

		this.field_35314_i = var4;
		if (this.field_35313_h) {
			this.field_35312_g = (float) (var2 - (var8 + 8)) / ((float) (var10 - var8) - 16.0F);
			if (this.field_35312_g < 0.0F) {
				this.field_35312_g = 0.0F;
			}

			if (this.field_35312_g > 1.0F) {
				this.field_35312_g = 1.0F;
			}

			((ContainerCreative) this.inventorySlots).func_35374_a(this.field_35312_g);
		}

		super.drawScreen(var1, var2, var3);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		int var11 = this.mc.renderEngine.getTexture("/gui/allitems.png");
		this.mc.renderEngine.bindTexture(var11);
		this.drawTexturedModalRect(var5 + 154, var6 + 17 + (int) ((float) (var10 - var8 - 17) * this.field_35312_g), 0, 208,
				16, 16);
	}

	protected void drawGuiContainerBackgroundLayer(float var1) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int var2 = this.mc.renderEngine.getTexture("/gui/allitems.png");
		this.mc.renderEngine.bindTexture(var2);
		int var3 = (this.width - this.xSize) / 2;
		int var4 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var3, var4, 0, 0, this.xSize, this.ySize);
	}


	static InventoryBasic func_35310_g() {
		return field_35311_f;
	}
}
