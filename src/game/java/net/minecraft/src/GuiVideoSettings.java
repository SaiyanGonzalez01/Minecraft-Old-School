package net.minecraft.src;

public class GuiVideoSettings extends GuiScreen {
	private GuiScreen field_22110_h;
	protected String field_22107_a = "Video Settings";
	private GameSettings field_22109_i;
	private static EnumOptions[] field_22108_k = new EnumOptions[]{EnumOptions.GRAPHICS, EnumOptions.RENDER_DISTANCE, EnumOptions.ANAGLYPH, EnumOptions.VIEW_BOBBING, EnumOptions.AMBIENT_OCCLUSION, EnumOptions.VSYNC};

	public GuiVideoSettings(GuiScreen var1, GameSettings var2) {
		this.field_22110_h = var1;
		this.field_22109_i = var2;
	}

	public void initGui() {
		StringTranslate var1 = StringTranslate.getInstance();
		this.field_22107_a = var1.translateKey("options.videoTitle");
		int var2 = 0;
		EnumOptions[] var3 = field_22108_k;
		int var4 = var3.length;

		for(int var5 = 0; var5 < var4; ++var5) {
			EnumOptions var6 = var3[var5];
			GuiButton b;
			if(!var6.getEnumFloat()) {
				b = new GuiSmallButton(var6.returnEnumOrdinal(), this.width / 2 - 155 + var2 % 2 * 160, this.height / 6 + 24 * (var2 >> 1), var6, this.field_22109_i.getKeyBinding(var6));
			} else {
				b = new GuiSlider(var6.returnEnumOrdinal(), this.width / 2 - 155 + var2 % 2 * 160, this.height / 6 + 24 * (var2 >> 1), var6, this.field_22109_i.getKeyBinding(var6), this.field_22109_i.getOptionFloatValue(var6));
			}

			this.controlList.add(b);

			++var2;
		}

		this.controlList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, var1.translateKey("gui.done")));
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.enabled) {
			if(var1.id < 100 && var1 instanceof GuiSmallButton) {
				this.field_22109_i.setOptionValue(((GuiSmallButton)var1).returnEnumOptions(), 1);
				var1.displayString = this.field_22109_i.getKeyBinding(EnumOptions.func_20137_a(var1.id));
			}

			if(var1.id == 200) {
				this.mc.gameSettings.saveOptions();
				this.mc.displayGuiScreen(this.field_22110_h);
			}

		}
	}

	public void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, this.field_22107_a, this.width / 2, 20, 16777215);
		super.drawScreen(var1, var2, var3);
	}
}
