package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

//unfinished will do next update trust

public class GuiHandBook extends GuiScreen {

    private float xSize_lo;
	private float ySize_lo;
    private int xSize;
    private int ySize;

    public GuiHandBook() {
    }

    public void initGui() {
    }

    protected void drawGuiContainerForegroundLayer() {
        this.fontRenderer.drawStringWithShadow("HandBook", 63, -10, 16777215);
    }

    protected void drawGuiContainerBackgroundLayer(float var1) {
        int var2 = this.mc.renderEngine.getTexture("/gui/handbook.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var2);
        int var3 = (this.width - this.xSize) / 2;
        int var4 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var3, var4, 0, 0, this.xSize, this.ySize);
    }

    public void drawScreen(int var1, int var2, float var3) {
        this.drawDefaultBackground();
        super.drawScreen(var1, var2, var3);
        this.xSize_lo = (float)var1;
		this.ySize_lo = (float)var2;
    }

}
