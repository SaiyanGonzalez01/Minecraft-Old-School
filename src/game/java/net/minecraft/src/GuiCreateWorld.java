package net.minecraft.src;

import net.lax1dude.eaglercraft.Random;
import org.lwjgl.input.Keyboard;

public class GuiCreateWorld extends GuiScreen {
	private GuiScreen field_22131_a;
	private GuiDisableButton field_22134_h;
	private GuiDisableButton field_22133_i;
	private String field_22132_k;
	private boolean field_22130_l;

	public GuiCreateWorld(GuiScreen var1) {
		this.field_22131_a = var1;
	}

	public void updateScreen() {
		this.field_22134_h.func_22070_b();
		this.field_22133_i.func_22070_b();
	}

	public void initGui() {
		StringTranslate var1 = StringTranslate.getInstance();
		Keyboard.enableRepeatEvents(true);
		this.controlList.clear();
		this.controlList.add(
				new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, var1.translateKey("selectWorld.create")));
		this.controlList
				.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, var1.translateKey("gui.cancel")));
		this.field_22134_h = new GuiDisableButton(this.fontRenderer, this.width / 2 - 100, 60, 200, 20,
				var1.translateKey("selectWorld.newWorld"));
		this.field_22134_h.field_22082_a = true;
		this.field_22134_h.func_22066_a(32);
		this.field_22133_i = new GuiDisableButton(this.fontRenderer, this.width / 2 - 100, 116, 200, 20, "");
		this.func_22129_j();
	}

	private void func_22129_j() {
		this.field_22132_k = this.field_22134_h.func_22071_a().trim();
		char[] var1 = FontAllowedCharacters.field_22286_b;
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			char var4 = var1[var3];
			this.field_22132_k = this.field_22132_k.replace(var4, '_');
		}

		if (MathHelper.func_22282_a(this.field_22132_k)) {
			this.field_22132_k = "World";
		}

		for (ISaveFormat var5 = this.mc.func_22004_c(); var5
				.func_22173_b(this.field_22132_k) != null; this.field_22132_k = this.field_22132_k + "-") {
		}

	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton var1) {
		if (var1.enabled) {
			if (var1.id == 1) {
				this.mc.displayGuiScreen(this.field_22131_a);
			} else if (var1.id == 0) {
				this.mc.displayGuiScreen((GuiScreen) null);
				if (this.field_22130_l) {
					return;
				}

				this.field_22130_l = true;
				long var2 = (new Random()).nextLong();
				String var4 = this.field_22133_i.func_22071_a();
				if (!MathHelper.func_22282_a(var4)) {
					try {
						long var5 = Long.parseLong(var4);
						if (var5 != 0L) {
							var2 = var5;
						}
					} catch (NumberFormatException var7) {
						var2 = (long) var4.hashCode();
					}
				}

				this.mc.playerController = new PlayerControllerSP(this.mc);
				this.mc.startWorld(this.field_22132_k, this.field_22134_h.func_22071_a(), var2);
				this.mc.displayGuiScreen((GuiScreen) null);
			}

		}
	}

	protected void keyTyped(char var1, int var2) {
		this.field_22134_h.func_22072_a(var1, var2);
		this.field_22133_i.func_22072_a(var1, var2);
		if (var1 == 13) {
			this.actionPerformed((GuiButton) this.controlList.get(0));
		}

		((GuiButton) this.controlList.get(0)).enabled = this.field_22134_h.func_22071_a().length() > 0;
		this.func_22129_j();
	}

	protected void mouseClicked(int var1, int var2, int var3) {
		super.mouseClicked(var1, var2, var3);
		this.field_22134_h.func_22069_a(var1, var2, var3);
		this.field_22133_i.func_22069_a(var1, var2, var3);
	}

	public void drawScreen(int var1, int var2, float var3) {
		StringTranslate var4 = StringTranslate.getInstance();
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, var4.translateKey("selectWorld.create"), this.width / 2,
				this.height / 4 - 60 + 20, 16777215);
		this.drawString(this.fontRenderer, var4.translateKey("selectWorld.enterName"), this.width / 2 - 100, 47, 10526880);
		this.drawString(this.fontRenderer, var4.translateKey("selectWorld.resultFolder") + " " + this.field_22132_k,
				this.width / 2 - 100, 85, 10526880);
		this.drawString(this.fontRenderer, var4.translateKey("selectWorld.enterSeed"), this.width / 2 - 100, 104, 10526880);
		this.drawString(this.fontRenderer, var4.translateKey("selectWorld.seedInfo"), this.width / 2 - 100, 140, 10526880);
		this.field_22134_h.func_22067_c();
		this.field_22133_i.func_22067_c();
		super.drawScreen(var1, var2, var3);
	}
}
