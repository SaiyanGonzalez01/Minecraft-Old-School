package net.minecraft.src;

import org.lwjgl.input.Keyboard;

import dev.colbster937.eaglercraft.FormattingCodes;
import dev.colbster937.eaglercraft.utils.I18n;
import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.profile.EaglerProfile;

public class GuiMultiplayer extends GuiScreen {
	private GuiScreen parentScreen;
	private GuiDisableButton field_22111_h;
	private GuiDisableButton usernameField;
	private String serverAddress = "";
	private boolean locked = false;

	public GuiMultiplayer(GuiScreen var1) {
		this.parentScreen = var1;
	}

	public GuiMultiplayer(GuiScreen var0, String var1) {
		this.parentScreen = var0;
		this.serverAddress = var1;
		this.locked = true;
	}

	public void updateScreen() {
		this.field_22111_h.func_22070_b();
		this.usernameField.func_22070_b();
	}

	public void initGui() {
		StringTranslate var1 = StringTranslate.getInstance();
		Keyboard.enableRepeatEvents(true);
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, var1.translateKey("multiplayer.connect")));
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, var1.translateKey("gui.cancel")));
		String var2 ;
		if (!locked) {
			var2 = this.mc.gameSettings.lastServer.replaceAll("_", ":");
		} else {
			var2 = this.serverAddress;
			this.mc.gameSettings.lastServer = this.serverAddress.replaceAll(":", "_");
		}
		this.field_22111_h = new GuiDisableButton(this.fontRenderer, this.width / 2 - 100, this.height / 4 - 10 + 50 + 18, 200, 20, var2);
		this.field_22111_h.field_22082_a = false;
		this.field_22111_h.field_22081_b = !this.locked;
		this.field_22111_h.func_22066_a(128);
		this.usernameField = new GuiDisableButton(this.fontRenderer, this.width / 2 - 100, this.height / 4 - 10 + 28, 200, 20, var2);
		this.usernameField.field_22082_a = true;
		this.usernameField.func_22068_a(EaglerProfile.getName());
		this.usernameField.func_22066_a(16);
		((GuiButton)this.controlList.get(0)).enabled = var2.length() > 0 && this.usernameField.func_22071_a().length() > 0;
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.enabled) {
			if(var1.id == 1) {
				this.mc.displayGuiScreen(this.parentScreen);
			} else if(var1.id == 0) {
				String var2 = this.field_22111_h.func_22071_a().trim();
				this.mc.gameSettings.lastServer = var2.replaceAll(":", "_");
				this.mc.gameSettings.saveOptions();
				String username = this.usernameField.func_22071_a();
				this.mc.session.username = username;
				EaglerProfile.setName(username);
				EaglerProfile.save();
				String[] var3 = var2.split(":");
				if(var2.startsWith("[")) {
					int var4 = var2.indexOf("]");
					if(var4 > 0) {
						String var5 = var2.substring(1, var4);
						String var6 = var2.substring(var4 + 1).trim();
						if(var6.startsWith(":") && var6.length() > 0) {
							var6 = var6.substring(1);
							var3 = new String[]{var5, var6};
						} else {
							var3 = new String[]{var5};
						}
					}
				}

				if(var3.length > 2) {
					var3 = new String[]{var2};
				}

				this.mc.displayGuiScreen(new GuiConnecting(this.mc, var3[0]));
			}

		}
	}

	protected void keyTyped(char var1, int var2) {
		if (this.field_22111_h.field_22082_a) this.field_22111_h.func_22072_a(var1, var2);
  	if (this.usernameField.field_22082_a) this.usernameField.func_22072_a(var1, var2);
		if(var1 == 13) {
			this.actionPerformed((GuiButton)this.controlList.get(0));
		}
		if (var1 == '\t') {
			this.field_22111_h.field_22082_a = this.usernameField.field_22082_a;
			this.usernameField.field_22082_a = !this.usernameField.field_22082_a;
		}

		((GuiButton)this.controlList.get(0)).enabled = this.field_22111_h.func_22071_a().length() > 0 && this.usernameField.func_22071_a().length() > 0;
	}

	protected void mouseClicked(int var1, int var2, int var3) {
		super.mouseClicked(var1, var2, var3);
		this.field_22111_h.func_22069_a(var1, var2, var3);
  	this.usernameField.func_22069_a(var1, var2, var3);
	}

	public void drawScreen(int var1, int var2, float var3) {
		StringTranslate var4 = StringTranslate.getInstance();
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, var4.translateKey("multiplayer.title"), this.width / 2, this.height / 4 - 60 + 20, 16777215);
		// this.drawString(this.fontRenderer, var4.translateKey("multiplayer.info1"), this.width / 2 - 140, this.height / 4 - 60 + 60 + 0, 10526880);
		// this.drawString(this.fontRenderer, var4.translateKey("multiplayer.info2"), this.width / 2 - 140, this.height / 4 - 60 + 60 + 9, 10526880);
		this.drawString(this.fontRenderer, var4.translateKey("multiplayer.ipinfo"), this.width / 2 - 100, this.height / 4 - 60 + 60 + 46, 10526880);
		this.field_22111_h.func_22067_c();
		this.usernameField.func_22067_c();
		
		this.drawString(this.fontRenderer, I18n.format("multiplayer.username"), this.width / 2 - 100, this.height / 4 - 60 + 60 + 6, 10526880);

		if (EagRuntime.requireSSL() && !this.locked) {
			this.drawCenteredString(this.fontRenderer, I18n.format("multiplayer.SSLWarn1"), this.width / 2, this.height / 4 + 83,
					FormattingCodes.COLOR_INFO);
			this.drawCenteredString(this.fontRenderer, I18n.format("multiplayer.SSLWarn2"), this.width / 2, this.height / 4 + 95,
					FormattingCodes.COLOR_INFO);
		}
		super.drawScreen(var1, var2, var3);
	}
}