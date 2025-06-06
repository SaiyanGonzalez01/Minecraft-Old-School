package net.minecraft.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//the random i prefer, and used for the splashtexts -chicken-nugget
import java.util.Random;

import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz585.textures.TextureLocation;

public class GuiMainMenu extends GuiScreen {
	
	private static final TextureLocation logoTexture = new TextureLocation("/title/mclogo.png");
	
	private static final Random rand = new Random();
	private float updateCounter = 0.0F;
	public String splashText = "erm this is a placeholder";

	String[] splashTexts = {
        	"Old-School!",
        	"Built on sticks and stones!",
        	"TeaVM is so epic!",
       		"As a child i yearned for the mines...",
        	"At least 8% bug free!",
		"World of Rubydung!",
		"Rekt.",
		"Nuggets are nice!",
		"Saiyan's Golden Child!",
		"Try Ctrl + Shift + Q + Q!",
		"Minecraft? More like Eaglercraft!",
		"Browser Supported!",
		"We will never have 404's!",
		"100% less laxatives for 1 dude!",
		"Better than S-SP!",
		"'Credit peyton' They cry! And so we did!",
		"1 star, deal with it Saiyan!",
		"Now with rubies!",
		"Try Terraria Stamped!",
		"Honey, the erasers are moving!",
		"Come back when your a little...mmm Richer!",
		"Dig Dug got nothing on me!",
		"And Knuckles!",
		"X Space Invaders!",
		"Cthulhu is MAD and missing an- oh wait wrong splash.",
		"The Legend of Steve!",
		"Farfig is still crying!",
		"1.12 got nothin on Beta, right?",
		"Check out my new shoes!",
		"Delay, Deny, Depose!",
		"Check For GoGuardian!",
		"Look Around For Teachers!",
		"Hola!",
		"Aw Snap!",
		"Opa!",
		"byte[] splash = new byte[] {72, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100, 33};" //this is a play on how the old splash text worked, and is just hello world in bytes
    	};
	
	private GuiButton multiplayerButton;
	
//	byte[] splash = new byte[] {87, 111, 114, 108, 100, 32, 111, 102, 32, 82, 117, 98, 121, 100, 117, 110, 103, 33};

	public GuiMainMenu() {
		splashText = splashTexts[rand.nextInt(splashTexts.length)];
	}

	public void updateScreen() {
		this.updateCounter++;
	}

	protected void keyTyped(char var1, int var2) {
	}

	public void initGui() {
		Calendar var1 = Calendar.getInstance();
		var1.setTime(new Date());
		if(var1.get(2) + 1 == 11 && var1.get(5) == 9) {
			this.splashText = "Happy birthday, ez!";
		} else if(var1.get(2) + 1 == 6 && var1.get(5) == 1) {
			this.splashText = "Happy birthday, Notch!";
		} else if(var1.get(2) + 1 == 12 && var1.get(5) == 24) {
			this.splashText = "Merry X-mas!";
		} else if(var1.get(2) + 1 == 1 && var1.get(5) == 1) {
			this.splashText = "Happy new year!";
		} else if(var1.get(2) + 1 == 1 && var1.get(5) == 5) {
			this.splashText = "Happy Birthday, Saiyan!";
		} else if(var1.get(2) + 1 == 10 && var1.get(5) == 31) {
			this.splashText = "Happy Halloween!";
		}

		StringTranslate var2 = StringTranslate.getInstance();
		int var4 = this.height / 4 + 48;
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, var4, var2.translateKey("menu.singleplayer")));
		this.controlList.add(this.multiplayerButton = new GuiButton(2, this.width / 2 - 100, var4 + 24, var2.translateKey("menu.multiplayer")));
		this.controlList.add(new GuiButton(3, this.width / 2 - 100, var4 + 48, var2.translateKey("menu.mods")));
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, var4 + 72, var2.translateKey("menu.options")));

		if(this.mc.session == null) {
			this.multiplayerButton.enabled = false;
		}

	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.id == 0) {
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
		}

		if(var1.id == 1) {
			this.mc.displayGuiScreen(new GuiSelectWorld(this));
		}

		if(var1.id == 2) {
			this.mc.displayGuiScreen(new GuiMultiplayer(this));
		}
		
		if(var1.id == 3) {
			this.mc.displayGuiScreen(new GuiTexturePacks(this));
		}

		if(var1.id == 4) {
			this.mc.shutdown();
		}

	}

	public void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();
		Tessellator var4 = Tessellator.instance;
		short var5 = 274;
		int var6 = this.width / 2 - var5 / 2;
		byte var7 = 30;
		logoTexture.bindTexture();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawTexturedModalRect(var6 + 0, var7 + 0, 0, 0, 155, 44);
		this.drawTexturedModalRect(var6 + 155, var7 + 0, 0, 45, 155, 44);
		var4.setColorOpaque_I(16777215);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)(this.width / 2 + 90), 70.0F, 0.0F);
		GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
		float var8 = 1.8F - MathHelper.abs(MathHelper.sin((float)(System.currentTimeMillis() % 1000L) / 1000.0F * (float)Math.PI * 2.0F) * 0.1F);
		var8 = var8 * 100.0F / (float)(this.fontRenderer.getStringWidth(this.splashText) + 32);
		GL11.glScalef(var8, var8, var8);
		this.drawCenteredString(this.fontRenderer, this.splashText, 0, -8, 16776960);
		GL11.glPopMatrix();
		this.drawString(this.fontRenderer, "Minecraft Old-School RD", 2, 2, 5263440);
		String var9 = "Created by Saiyan Gonzalez";
		this.drawString(this.fontRenderer, var9, this.width - this.fontRenderer.getStringWidth(var9) - 2, this.height - 10, 16777215);
		super.drawScreen(var1, var2, var3);
	}
}

 


