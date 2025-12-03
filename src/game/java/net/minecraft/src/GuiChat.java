package net.minecraft.src;

import org.lwjgl.input.Keyboard;

import dev.colbster937.eaglercraft.utils.I18n;
import dev.colbster937.eaglercraft.utils.ScuffedUtils;

public class GuiChat extends GuiScreen {
	protected String message = "";
	private int updateCounter = 0;
	private static final String field_20082_i = FontAllowedCharacters.allowedCharacters;

	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		if (!(this instanceof GuiSleepMP)) this.controlList.add(new GuiButton(0, this.width - 100, 3, 97, 20, I18n.format("chat.exit")));
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	public void updateScreen() {
		++this.updateCounter;
	}

	protected void keyTyped(char var1, int var2) {
		if(var2 == 1) {
			this.mc.displayGuiScreen((GuiScreen)null);
		} else if(var2 == 28) {
			String var3 = this.message.trim();
			if(var3.length() > 0) {
				String var4 = this.message.trim();
				if(!this.mc.func_22003_b(var4)) {
					this.mc.thePlayer.sendChatMessage(var4);
				}
			} else if (var2 == Keyboard.KEY_V && ScuffedUtils.isCtrlKeyDown()) {
				String clip = getClipboardString();
				if (clip == null) clip = "";
				this.message += clip;
				if (this.message.length() > 100) this.message.subSequence(0, 100);
				return;
			}

			this.mc.displayGuiScreen((GuiScreen)null);
		} else {
			if(var2 == 14 && this.message.length() > 0) {
				this.message = this.message.substring(0, this.message.length() - 1);
			}

			if(field_20082_i.indexOf(var1) >= 0 && this.message.length() < 100) {
				this.message = this.message + var1;
			}

		}
	}

	public void drawScreen(int var1, int var2, float var3) {
		this.drawRect(2, this.height - 14, this.width - 2, this.height - 2, Integer.MIN_VALUE);
		this.drawString(this.fontRenderer, "> " + this.message + (this.updateCounter / 6 % 2 == 0 ? "_" : ""), 4, this.height - 12, 14737632);
		super.drawScreen(var1, var2, var3);
	}

	protected void mouseClicked(int var1, int var2, int var3) {
		if(var3 == 0) {
			if(this.mc.ingameGUI.field_933_a != null) {
				if(this.message.length() > 0 && !this.message.endsWith(" ")) {
					this.message = this.message + " ";
				}

				this.message = this.message + this.mc.ingameGUI.field_933_a;
				byte var4 = 100;
				if(this.message.length() > var4) {
					this.message = this.message.substring(0, var4);
				}
			} else {
				super.mouseClicked(var1, var2, var3);
			}
		}

	}

	protected void actionPerformed(GuiButton var1) {
		if (var1.id == 0) {
			this.mc.displayGuiScreen(null);
			this.mc.func_6259_e();
		}
	}

	public void setMessage(String var0) {
		this.message = var0;
	}

	public boolean doesGuiPauseGame() {
		return false;
	}


}
