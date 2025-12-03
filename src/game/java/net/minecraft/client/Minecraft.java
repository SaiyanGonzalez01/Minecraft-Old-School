package net.minecraft.client;

import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.internal.PlatformRuntime;
import net.lax1dude.eaglercraft.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.internal.vfs2.VFile2;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.ChunkProviderLoadOrGenerate;
import net.minecraft.src.EffectRenderer;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.EntityRenderer;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GLAllocation;
import net.minecraft.src.GameSettings;
import net.minecraft.src.GuiChat;
import net.minecraft.src.GuiConflictWarning;
import net.minecraft.src.GuiConnecting;
import net.minecraft.src.GuiGameOver;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.GuiIngameMenu;
import net.minecraft.src.GuiInventory;
import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiSleepMP;
import net.minecraft.src.GuiUnused;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.ISaveFormat;
import net.minecraft.src.ISaveHandler;
import net.minecraft.src.ItemRenderer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.LoadingScreenRenderer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MinecraftError;
import net.minecraft.src.MinecraftException;
import net.minecraft.src.ModelBiped;
import net.minecraft.src.MouseHelper;
import net.minecraft.src.MovementInputFromOptions;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.PlayerController;
import net.minecraft.src.PlayerControllerTest;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.RenderGlobal;
import net.minecraft.src.RenderManager;
import net.minecraft.src.SaveConverterMcRegion;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.ScreenShotHelper;
import net.minecraft.src.Session;
import net.minecraft.src.SoundManager;
import net.minecraft.src.Teleporter;
import net.minecraft.src.Tessellator;
import net.minecraft.src.TextureCompassFX;
import net.minecraft.src.TextureFlamesFX;
import net.minecraft.src.TextureLavaFX;
import net.minecraft.src.TextureLavaFlowFX;
import net.minecraft.src.TexturePortalFX;
import net.minecraft.src.TextureWatchFX;
import net.minecraft.src.TextureWaterFX;
import net.minecraft.src.TexureWaterFlowFX;
import net.minecraft.src.Timer;
import net.minecraft.src.UnexpectedThrowable;
import net.minecraft.src.Vec3D;
import net.minecraft.src.World;
import net.minecraft.src.WorldProvider;
import net.minecraft.src.WorldProviderHell;
import net.minecraft.src.WorldRenderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import dev.colbster937.eaglercraft.FormattingCodes;
import dev.colbster937.eaglercraft.SingleplayerCommands;
import dev.colbster937.eaglercraft.rp.TexturePack;
import dev.colbster937.eaglercraft.utils.I18n;
import dev.colbster937.eaglercraft.utils.SaveUtils;
import dev.colbster937.eaglercraft.utils.StringPrintStream;

public class Minecraft implements Runnable {
	private static Minecraft field_21900_a;
	public PlayerController playerController;
	private boolean fullscreen = false;
	public int displayWidth;
	public int displayHeight;
	private Timer timer = new Timer(20.0F);
	public World theWorld;
	public RenderGlobal renderGlobal;
	public EntityPlayerSP thePlayer;
	public EntityLiving field_22009_h;
	public EffectRenderer effectRenderer;
	public Session session = null;
	public String minecraftUri;
	public volatile boolean isWorldLoaded = false;
	public RenderEngine renderEngine;
	public FontRenderer fontRenderer;
	public GuiScreen currentScreen = null;
	public LoadingScreenRenderer loadingScreen = new LoadingScreenRenderer(this);
	public EntityRenderer entityRenderer = new EntityRenderer(this);
	private int ticksRan = 0;
	private int field_6282_S = 0;
	private int field_9236_T;
	private int field_9235_U;
	public GuiIngame ingameGUI;
	public boolean field_6307_v = false;
	public ModelBiped field_9242_w = new ModelBiped(0.0F);
	public MovingObjectPosition objectMouseOver = null;
	public GameSettings gameSettings;
	public SoundManager sndManager = new SoundManager();
	public MouseHelper mouseHelper;
	private VFile2 mcDataDir;
	private ISaveFormat field_22008_V;
	public static long[] frameTimes = new long[512];
	public static long[] tickTimes = new long[512];
	public static int numRecordedFrameTimes = 0;
	private String server;
	private TextureWaterFX textureWaterFX = new TextureWaterFX();
	private TextureLavaFX textureLavaFX = new TextureLavaFX();
	private static VFile2 minecraftDir = null;
	public volatile boolean running = true;
	public String debug = "";
	public int fps = 0;
	boolean isTakingScreenshot = false;
	long prevFrameTime = -1L;
	public boolean field_6289_L = false;
	private int field_6302_aa = 0;
	public boolean isRaining = false;
	long systemTime = System.currentTimeMillis();
	private int field_6300_ab = 0;

	public GuiMainMenu menu = new GuiMainMenu();

	public Minecraft() {
		this.displayWidth = Display.getWidth();
		this.displayHeight = Display.getHeight();

		this.session = new Session("Player", "");

		field_21900_a = this;
	}

	public static Minecraft getMinecraft() {
		return field_21900_a;
	}

	public void updateDisplay() {
		if (Display.isVSyncSupported()) {
			if (this.theWorld == null || this.currentScreen != null) Display.setVSync(true);
			else Display.setVSync(this.gameSettings.vsync);
		}
		Display.update();
		if (Display.wasResized()) this.resize(Display.getWidth(), Display.getHeight());
	}

	public void displayUnexpectedThrowable(UnexpectedThrowable var1) {
		StringPrintStream log = new StringPrintStream();
		var1.exception.printStackTrace();
		var1.exception.printStackTrace(log);
		PlatformRuntime.writeCrashReport(log.toString());
	}

	public void setServer(String var1) {
		this.server = var1;
	}

	public void startGame() throws LWJGLException {
		Display.setTitle("Minecraft Beta 1.3_01");

		RenderManager.instance.itemRenderer = new ItemRenderer(this);
		this.mcDataDir = getMinecraftDir();
		this.field_22008_V = new SaveConverterMcRegion(new VFile2(this.mcDataDir, "saves"));
		this.gameSettings = new GameSettings(this, this.mcDataDir);
		this.renderEngine = new RenderEngine(this.gameSettings);
		TexturePack.init(this);
		SaveUtils.init(this);
		this.fontRenderer = new FontRenderer(this.gameSettings, "/font/default.png", this.renderEngine);
		this.loadScreen();
		this.mouseHelper = new MouseHelper();

		this.checkGLError("Pre startup");
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glClearDepth(1.0D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		this.checkGLError("Startup");
		this.sndManager.loadSoundSettings(this.gameSettings);
		this.renderEngine.registerTextureFX(this.textureLavaFX);
		this.renderEngine.registerTextureFX(this.textureWaterFX);
		this.renderEngine.registerTextureFX(new TexturePortalFX());
		this.renderEngine.registerTextureFX(new TextureCompassFX(this));
		this.renderEngine.registerTextureFX(new TextureWatchFX(this));
		this.renderEngine.registerTextureFX(new TexureWaterFlowFX());
		this.renderEngine.registerTextureFX(new TextureLavaFlowFX());
		this.renderEngine.registerTextureFX(new TextureFlamesFX(0));
		this.renderEngine.registerTextureFX(new TextureFlamesFX(1));
		this.renderGlobal = new RenderGlobal(this, this.renderEngine);
		GL11.glViewport(0, 0, this.displayWidth, this.displayHeight);
		this.effectRenderer = new EffectRenderer(this.theWorld, this.renderEngine);

		this.checkGLError("Post startup");
		this.ingameGUI = new GuiIngame(this);
		if(this.server != null) {
			this.displayGuiScreen(new GuiConnecting(this, this.server));
		} else {
			this.displayGuiScreen(this.menu);
		}

	}

	private void loadScreen() throws LWJGLException {
		ScaledResolution var1 = new ScaledResolution(this.displayWidth, this.displayHeight);
		int var2 = var1.getScaledWidth();
		int var3 = var1.getScaledHeight();
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0D, (double)var2, (double)var3, 0.0D, 1000.0D, 3000.0D);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
		GL11.glViewport(0, 0, this.displayWidth, this.displayHeight);
		GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		Tessellator var4 = Tessellator.instance;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_FOG);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.renderEngine.getTexture("/title/mojang.png"));
		var4.startDrawingQuads();
		var4.setColorOpaque_I(16777215);
		var4.addVertexWithUV(0.0D, (double)this.displayHeight, 0.0D, 0.0D, 0.0D);
		var4.addVertexWithUV((double)this.displayWidth, (double)this.displayHeight, 0.0D, 0.0D, 0.0D);
		var4.addVertexWithUV((double)this.displayWidth, 0.0D, 0.0D, 0.0D, 0.0D);
		var4.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
		var4.draw();
		short var5 = 256;
		short var6 = 256;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		var4.setColorOpaque_I(16777215);
		this.func_6274_a((this.displayWidth / 2 - var5) / 2, (this.displayHeight / 2 - var6) / 2, 0, 0, var5, var6);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
	}

	public void func_6274_a(int var1, int var2, int var3, int var4, int var5, int var6) {
		float var7 = 0.00390625F;
		float var8 = 0.00390625F;
		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV((double)(var1 + 0), (double)(var2 + var6), 0.0D, (double)((float)(var3 + 0) * var7), (double)((float)(var4 + var6) * var8));
		var9.addVertexWithUV((double)(var1 + var5), (double)(var2 + var6), 0.0D, (double)((float)(var3 + var5) * var7), (double)((float)(var4 + var6) * var8));
		var9.addVertexWithUV((double)(var1 + var5), (double)(var2 + 0), 0.0D, (double)((float)(var3 + var5) * var7), (double)((float)(var4 + 0) * var8));
		var9.addVertexWithUV((double)(var1 + 0), (double)(var2 + 0), 0.0D, (double)((float)(var3 + 0) * var7), (double)((float)(var4 + 0) * var8));
		var9.draw();
	}

	public static VFile2 getMinecraftDir() {
		if(minecraftDir == null) {
			minecraftDir = getAppDir("minecraft");
		}

		return minecraftDir;
	}

	public static VFile2 getAppDir(String var0) {
		return new VFile2(var0 + '/');
	}

	public ISaveFormat func_22004_c() {
		return this.field_22008_V;
	}

	public void displayGuiScreen(GuiScreen var1) {
		if(!(this.currentScreen instanceof GuiUnused)) {
			if(this.currentScreen != null) {
				this.currentScreen.onGuiClosed();
			}

			if(var1 == null && this.theWorld == null) {
				var1 = this.menu;
			} else if(var1 == null && this.thePlayer.health <= 0) {
				var1 = new GuiGameOver();
			}

			this.currentScreen = (GuiScreen)var1;
			if(var1 != null) {
				this.func_6273_f();
				ScaledResolution var2 = new ScaledResolution(this.displayWidth, this.displayHeight);
				int var3 = var2.getScaledWidth();
				int var4 = var2.getScaledHeight();
				((GuiScreen)var1).setWorldAndResolution(this, var3, var4);
				this.field_6307_v = false;
			} else {
				this.func_6259_e();
			}

		}
	}

	private void checkGLError(String var1) {
		int var2 = GL11.glGetError();
		if(var2 != 0) {
			String var3 = GLU.gluErrorString(var2);
			System.out.println("########## GL ERROR ##########");
			System.out.println("@ " + var1);
			System.out.println(var2 + ": " + var3);
			EagRuntime.exit();
		}

	}

	public void shutdownMinecraftApplet() {
		try {
			System.out.println("Stopping!");

			try {
				this.changeWorld1((World)null);
			} catch (Throwable var8) {
			}

			try {
				GLAllocation.deleteTexturesAndDisplayLists();
			} catch (Throwable var7) {
			}

			this.sndManager.closeMinecraft();
		} finally {
			EagRuntime.exit();
		}

		System.gc();
	}

	public void run() {
		this.running = true;

		try {
			this.startGame();
		} catch (Exception var15) {
			var15.printStackTrace();
			this.displayUnexpectedThrowable(new UnexpectedThrowable("Failed to start game", var15));
			return;
		}

		try {
			long var1 = System.currentTimeMillis();
			int var3 = 0;

			while(this.running) {
				AxisAlignedBB.clearBoundingBoxPool();
				Vec3D.initialize();
				if(Display.isCloseRequested()) {
					this.shutdown();
				}

				if(this.isWorldLoaded && this.theWorld != null) {
					float var4 = this.timer.renderPartialTicks;
					this.timer.updateTimer();
					this.timer.renderPartialTicks = var4;
				} else {
					this.timer.updateTimer();
				}

				long var19 = System.nanoTime();

				for(int var6 = 0; var6 < this.timer.elapsedTicks; ++var6) {
					++this.ticksRan;

					try {
						this.runTick();
					} catch (MinecraftException var14) {
						this.theWorld = null;
						this.changeWorld1((World)null);
						this.displayGuiScreen(new GuiConflictWarning());
					}
				}

				long var20 = System.nanoTime() - var19;
				this.checkGLError("Pre render");
				this.sndManager.func_338_a(this.thePlayer, this.timer.renderPartialTicks);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				if(this.theWorld != null && !this.theWorld.multiplayerWorld) {
					this.theWorld.func_6465_g();
				}

				if(this.theWorld != null && this.theWorld.multiplayerWorld) {
					this.theWorld.func_6465_g();
				}

				if(!this.field_6307_v) {
					if(this.playerController != null) {
						this.playerController.setPartialTime(this.timer.renderPartialTicks);
					}

					this.entityRenderer.func_4136_b(this.timer.renderPartialTicks);
				}

				if(this.gameSettings.showDebugInfo) {
					this.displayDebugInfo(var20);
				} else {
					this.prevFrameTime = System.nanoTime();
				}

				this.screenshotListener();
				this.updateDisplay();

				this.checkGLError("Post render");
				++var3;

				for(this.isWorldLoaded = !this.isMultiplayerWorld() && this.currentScreen != null && this.currentScreen.doesGuiPauseGame(); System.currentTimeMillis() >= var1 + 1000L; var3 = 0) {
					this.debug = var3 + " fps, " + WorldRenderer.chunksUpdated + " chunk updates";
					this.fps = var3;
					WorldRenderer.chunksUpdated = 0;
					var1 += 1000L;
				}
			}
		} catch (MinecraftError var16) {
		} catch (Throwable var17) {
			this.theWorld = null;
			var17.printStackTrace();
			this.displayUnexpectedThrowable(new UnexpectedThrowable("Unexpected error", var17));
		} finally {
			this.shutdownMinecraftApplet();
		}

	}

	private void screenshotListener() {
		if(Keyboard.isKeyDown(Keyboard.KEY_F2)) {
			if(!this.isTakingScreenshot) {
				this.isTakingScreenshot = true;
				if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
					this.ingameGUI.addChatMessage(this.func_21001_a(minecraftDir, this.displayWidth, this.displayHeight, '\u8e62', 17700));
				} else {
					this.ingameGUI.addChatMessage(ScreenShotHelper.saveScreenshot(minecraftDir, this.displayWidth, this.displayHeight));
				}
			}
		} else {
			this.isTakingScreenshot = false;
		}

	}

	private String func_21001_a(VFile2 var1, int var2, int var3, int var4, int var5) {
		try {
			ByteBuffer var6 = GLAllocation.createDirectByteBuffer(var2 * var3 * 3);
			ScreenShotHelper var7 = new ScreenShotHelper(var1, var4, var5, var3);
			double var8 = (double)var4 / (double)var2;
			double var10 = (double)var5 / (double)var3;
			double var12 = var8 > var10 ? var8 : var10;

			for(int var14 = (var5 - 1) / var3 * var3; var14 >= 0; var14 -= var3) {
				for(int var15 = 0; var15 < var4; var15 += var2) {
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.renderEngine.getTexture("/terrain.png"));
					double var18 = (double)(var4 - var2) / 2.0D * 2.0D - (double)(var15 * 2);
					double var20 = (double)(var5 - var3) / 2.0D * 2.0D - (double)(var14 * 2);
					var18 /= (double)var2;
					var20 /= (double)var3;
					this.entityRenderer.func_21152_a(var12, var18, var20);
					this.entityRenderer.renderWorld(1.0F);
					this.entityRenderer.func_21151_b();
					var6.clear();
					var7.func_21189_a(var6, var15, var14, var2, var3);
				}

				var7.func_21191_a();
			}

			return var7.func_21190_b();
		} catch (Exception var24) {
			var24.printStackTrace();
			return "Failed to save image: " + var24;
		}
	}

	private void displayDebugInfo(long var1) {
		long var3 = 16666666L;
		if(this.prevFrameTime == -1L) {
			this.prevFrameTime = System.nanoTime();
		}

		long var5 = System.nanoTime();
		tickTimes[numRecordedFrameTimes & frameTimes.length - 1] = var1;
		frameTimes[numRecordedFrameTimes++ & frameTimes.length - 1] = var5 - this.prevFrameTime;
		this.prevFrameTime = var5;
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0D, (double)this.displayWidth, (double)this.displayHeight, 0.0D, 1000.0D, 3000.0D);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
		GL11.glLineWidth(1.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		Tessellator var7 = Tessellator.instance;
		var7.startDrawing(7);
		int var8 = (int)(var3 / 200000L);
		var7.setColorOpaque_I(536870912);
		var7.addVertex(0.0D, (double)(this.displayHeight - var8), 0.0D);
		var7.addVertex(0.0D, (double)this.displayHeight, 0.0D);
		var7.addVertex((double)frameTimes.length, (double)this.displayHeight, 0.0D);
		var7.addVertex((double)frameTimes.length, (double)(this.displayHeight - var8), 0.0D);
		var7.setColorOpaque_I(538968064);
		var7.addVertex(0.0D, (double)(this.displayHeight - var8 * 2), 0.0D);
		var7.addVertex(0.0D, (double)(this.displayHeight - var8), 0.0D);
		var7.addVertex((double)frameTimes.length, (double)(this.displayHeight - var8), 0.0D);
		var7.addVertex((double)frameTimes.length, (double)(this.displayHeight - var8 * 2), 0.0D);
		var7.draw();
		long var9 = 0L;

		int var11;
		for(var11 = 0; var11 < frameTimes.length; ++var11) {
			var9 += frameTimes[var11];
		}

		var11 = (int)(var9 / 200000L / (long)frameTimes.length);
		var7.startDrawing(7);
		var7.setColorOpaque_I(541065216);
		var7.addVertex(0.0D, (double)(this.displayHeight - var11), 0.0D);
		var7.addVertex(0.0D, (double)this.displayHeight, 0.0D);
		var7.addVertex((double)frameTimes.length, (double)this.displayHeight, 0.0D);
		var7.addVertex((double)frameTimes.length, (double)(this.displayHeight - var11), 0.0D);
		var7.draw();
		var7.startDrawing(1);

		for(int var12 = 0; var12 < frameTimes.length; ++var12) {
			int var13 = (var12 - numRecordedFrameTimes & frameTimes.length - 1) * 255 / frameTimes.length;
			int var14 = var13 * var13 / 255;
			var14 = var14 * var14 / 255;
			int var15 = var14 * var14 / 255;
			var15 = var15 * var15 / 255;
			if(frameTimes[var12] > var3) {
				var7.setColorOpaque_I(-16777216 + var14 * 65536);
			} else {
				var7.setColorOpaque_I(-16777216 + var14 * 256);
			}

			long var16 = frameTimes[var12] / 200000L;
			long var18 = tickTimes[var12] / 200000L;
			var7.addVertex((double)((float)var12 + 0.5F), (double)((float)((long)this.displayHeight - var16) + 0.5F), 0.0D);
			var7.addVertex((double)((float)var12 + 0.5F), (double)((float)this.displayHeight + 0.5F), 0.0D);
			var7.setColorOpaque_I(-16777216 + var14 * 65536 + var14 * 256 + var14 * 1);
			var7.addVertex((double)((float)var12 + 0.5F), (double)((float)((long)this.displayHeight - var16) + 0.5F), 0.0D);
			var7.addVertex((double)((float)var12 + 0.5F), (double)((float)((long)this.displayHeight - (var16 - var18)) + 0.5F), 0.0D);
		}

		var7.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public void shutdown() {
		this.running = false;
	}

	public void func_6259_e() {
		if(Display.isActive()) {
			if(!this.field_6289_L) {
				this.field_6289_L = true;
				if (this.currentScreen == null) this.mouseHelper.func_774_a();
				this.displayGuiScreen((GuiScreen)null);
				this.field_6302_aa = this.ticksRan + 10000;
			}
		}
	}

	public void func_6273_f() {
		if(this.field_6289_L) {
			if(this.thePlayer != null) {
				this.thePlayer.resetPlayerKeyState();
			}

			this.field_6289_L = false;
			this.mouseHelper.func_773_b();
		}
	}

	public void func_6252_g() {
		if(this.currentScreen == null) {
			this.displayGuiScreen(new GuiIngameMenu());
		}
	}

	private void func_6254_a(int var1, boolean var2) {
		if(!this.playerController.field_1064_b) {
			if(var1 != 0 || this.field_6282_S <= 0) {
				if(var2 && this.objectMouseOver != null && this.objectMouseOver.typeOfHit == EnumMovingObjectType.TILE && var1 == 0) {
					int var3 = this.objectMouseOver.blockX;
					int var4 = this.objectMouseOver.blockY;
					int var5 = this.objectMouseOver.blockZ;
					this.playerController.sendBlockRemoving(var3, var4, var5, this.objectMouseOver.sideHit);
					this.effectRenderer.addBlockHitEffects(var3, var4, var5, this.objectMouseOver.sideHit);
				} else {
					this.playerController.func_6468_a();
				}

			}
		}
	}

	private void clickMouse(int var1) {
		if(var1 != 0 || this.field_6282_S <= 0) {
			if(var1 == 0) {
				this.thePlayer.swingItem();
			}

			boolean var2 = true;
			if(this.objectMouseOver == null) {
				if(var1 == 0 && !(this.playerController instanceof PlayerControllerTest)) {
					this.field_6282_S = 10;
				}
			} else if(this.objectMouseOver.typeOfHit == EnumMovingObjectType.ENTITY) {
				if(var1 == 0) {
					this.playerController.func_6472_b(this.thePlayer, this.objectMouseOver.entityHit);
				}

				if(var1 == 1) {
					this.playerController.func_6475_a(this.thePlayer, this.objectMouseOver.entityHit);
				}
			} else if(this.objectMouseOver.typeOfHit == EnumMovingObjectType.TILE) {
				int var3 = this.objectMouseOver.blockX;
				int var4 = this.objectMouseOver.blockY;
				int var5 = this.objectMouseOver.blockZ;
				int var6 = this.objectMouseOver.sideHit;
				Block var7 = Block.blocksList[this.theWorld.getBlockId(var3, var4, var5)];
				if(var1 == 0) {
					this.theWorld.onBlockHit(var3, var4, var5, this.objectMouseOver.sideHit);
					if(var7 != Block.bedrock || this.thePlayer.field_9371_f >= 100) {
						this.playerController.clickBlock(var3, var4, var5, this.objectMouseOver.sideHit);
					}
				} else {
					ItemStack var8 = this.thePlayer.inventory.getCurrentItem();
					int var9 = var8 != null ? var8.stackSize : 0;
					if(this.playerController.sendPlaceBlock(this.thePlayer, this.theWorld, var8, var3, var4, var5, var6)) {
						var2 = false;
						this.thePlayer.swingItem();
					}

					if(var8 == null) {
						return;
					}

					if(var8.stackSize == 0) {
						this.thePlayer.inventory.mainInventory[this.thePlayer.inventory.currentItem] = null;
					} else if(var8.stackSize != var9) {
						this.entityRenderer.itemRenderer.func_9449_b();
					}
				}
			}

			if(var2 && var1 == 1) {
				ItemStack var10 = this.thePlayer.inventory.getCurrentItem();
				if(var10 != null && this.playerController.sendUseItem(this.thePlayer, this.theWorld, var10)) {
					this.entityRenderer.itemRenderer.func_9450_c();
				}
			}

		}
	}

	public void toggleFullscreen() {
		Display.toggleFullscreen();
	}

	private void resize(int var1, int var2) {
		if(var1 <= 0) {
			var1 = 1;
		}

		if(var2 <= 0) {
			var2 = 1;
		}

		this.displayWidth = var1;
		this.displayHeight = var2;
		if(this.currentScreen != null) {
			ScaledResolution var3 = new ScaledResolution(var1, var2);
			int var4 = var3.getScaledWidth();
			int var5 = var3.getScaledHeight();
			this.currentScreen.setWorldAndResolution(this, var4, var5);
		}

	}

	private void clickMiddleMouseButton() {
		if(this.objectMouseOver != null) {
			int var1 = this.theWorld.getBlockId(this.objectMouseOver.blockX, this.objectMouseOver.blockY, this.objectMouseOver.blockZ);
			if(var1 == Block.grass.blockID) {
				var1 = Block.dirt.blockID;
			}

			if(var1 == Block.stairDouble.blockID) {
				var1 = Block.stairSingle.blockID;
			}

			if(var1 == Block.bedrock.blockID) {
				var1 = Block.stone.blockID;
			}

			this.thePlayer.inventory.setCurrentItem(var1, this.playerController instanceof PlayerControllerTest);
		}

	}

	private int noticeTimer = 0;
	private boolean displayedNotice = false;

	public void runTick() {
		this.ingameGUI.updateTick();
		this.entityRenderer.getMouseOver(1.0F);
		int var3;
		if(this.thePlayer != null) {
			IChunkProvider var1 = this.theWorld.func_21118_q();
			if(var1 instanceof ChunkProviderLoadOrGenerate) {
				ChunkProviderLoadOrGenerate var2 = (ChunkProviderLoadOrGenerate)var1;
				var3 = MathHelper.floor_float((float)((int)this.thePlayer.posX)) >> 4;
				int var4 = MathHelper.floor_float((float)((int)this.thePlayer.posZ)) >> 4;
				var2.func_21110_c(var3, var4);
			}
		}

		if (!this.isWorldLoaded && this.theWorld != null) {
			playerController.updateController();
			if (!this.displayedNotice) {
				if (++noticeTimer >= 150) {
					if (thePlayer != null) {
						this.displayedNotice = true;
						if (!isMultiplayerWorld()) {
							ingameGUI.addChatMessage(FormattingCodes.RED + I18n.format("lagNotice1"));
							ingameGUI.addChatMessage(FormattingCodes.RED + I18n.format("lagNotice2"));
						}
					}
				}
			}
		}

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.renderEngine.getTexture("/terrain.png"));
		if(!this.isWorldLoaded) {
			this.renderEngine.func_1067_a();
		}

		if(this.currentScreen == null && this.thePlayer != null) {
			if(this.thePlayer.health <= 0) {
				this.displayGuiScreen((GuiScreen)null);
			} else if(this.thePlayer.isPlayerSleeping() && this.theWorld != null && this.theWorld.multiplayerWorld) {
				this.displayGuiScreen(new GuiSleepMP());
			}
		} else if(this.currentScreen != null && this.currentScreen instanceof GuiSleepMP && !this.thePlayer.isPlayerSleeping()) {
			this.displayGuiScreen((GuiScreen)null);
		}

		if(this.currentScreen != null) {
			this.field_6302_aa = this.ticksRan + 10000;
		}

		if(this.currentScreen != null) {
			this.currentScreen.handleInput();
			if(this.currentScreen != null) {
				this.currentScreen.updateScreen();
			}
		}

		if(this.currentScreen == null || this.currentScreen.field_948_f) {
			label295:
			while(true) {
				while(true) {
					while(true) {
						long var5;
						do {
							if(!Mouse.next()) {
								if(this.field_6282_S > 0) {
									--this.field_6282_S;
								}

								while(true) {
									while(true) {
										do {
											if(!Keyboard.next()) {
												if(this.currentScreen == null) {
													if(Mouse.isButtonDown(0) && (float)(this.ticksRan - this.field_6302_aa) >= this.timer.ticksPerSecond / 4.0F && this.field_6289_L) {
														this.clickMouse(0);
														this.field_6302_aa = this.ticksRan;
													}

													if(Mouse.isButtonDown(1) && (float)(this.ticksRan - this.field_6302_aa) >= this.timer.ticksPerSecond / 4.0F && this.field_6289_L) {
														this.clickMouse(1);
														this.field_6302_aa = this.ticksRan;
													}
												}

												this.func_6254_a(0, this.currentScreen == null && Mouse.isButtonDown(0) && this.field_6289_L);
												break label295;
											}

											this.thePlayer.handleKeyPress(Keyboard.getEventKey(), Keyboard.getEventKeyState());
										} while(!Keyboard.getEventKeyState());

										if(Keyboard.getEventKey() == Keyboard.KEY_F11) {
											this.toggleFullscreen();
										} else {
											if(this.currentScreen != null) {
												this.currentScreen.handleKeyboardInput();
											} else {
												if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE || Keyboard.getEventKey() == Keyboard.KEY_GRAVE) {
													this.func_6252_g();
												}

												if(Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.isKeyDown(Keyboard.KEY_F3)) {
													this.forceReload();
												}

												if(Keyboard.getEventKey() == Keyboard.KEY_F1) {
													this.gameSettings.field_22277_y = !this.gameSettings.field_22277_y;
												}

												if(Keyboard.getEventKey() == Keyboard.KEY_F3) {
													this.gameSettings.showDebugInfo = !this.gameSettings.showDebugInfo;
												}

												if(Keyboard.getEventKey() == Keyboard.KEY_F5) {
													this.gameSettings.thirdPersonView = !this.gameSettings.thirdPersonView;
												}

												if(Keyboard.getEventKey() == Keyboard.KEY_F8) {
													this.gameSettings.field_22274_D = !this.gameSettings.field_22274_D;
												}

												if(Keyboard.getEventKey() == this.gameSettings.keyBindInventory.keyCode) {
													this.displayGuiScreen(new GuiInventory(this.thePlayer));
												}

												if(Keyboard.getEventKey() == this.gameSettings.keyBindDrop.keyCode) {
													this.thePlayer.dropCurrentItem();
												}

												if(Keyboard.getEventKey() == this.gameSettings.keyBindChat.keyCode) {
													this.displayGuiScreen(new GuiChat());
												}

												if(Keyboard.getEventKey() == this.gameSettings.keyBindCommand.keyCode) {
													this.displayGuiScreen(new GuiChat());
													if (this.currentScreen instanceof GuiChat)
														((GuiChat) this.currentScreen).setMessage("/");
												}
											}

											for(int var6 = 0; var6 < 9; ++var6) {
												if(Keyboard.getEventKey() == Keyboard.KEY_1 + var6) {
													this.thePlayer.inventory.currentItem = var6;
												}
											}
										}
									}
								}
							}

							var5 = System.currentTimeMillis() - this.systemTime;
						} while(var5 > 200L);

						var3 = Mouse.getEventDWheel();
						if(var3 != 0) {
							this.thePlayer.inventory.changeCurrentItem(var3);
							if(this.gameSettings.field_22275_C) {
								if(var3 > 0) {
									var3 = 1;
								}

								if(var3 < 0) {
									var3 = -1;
								}

								this.gameSettings.field_22272_F += (float)var3 * 0.25F;
							}
						}

						if(this.currentScreen == null) {
							if(!this.field_6289_L && Mouse.getEventButtonState()) {
								this.func_6259_e();
							} else {
								if(Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
									this.clickMouse(0);
									this.field_6302_aa = this.ticksRan;
								}

								if(Mouse.getEventButton() == 1 && Mouse.getEventButtonState()) {
									this.clickMouse(1);
									this.field_6302_aa = this.ticksRan;
								}

								if(Mouse.getEventButton() == 2 && Mouse.getEventButtonState()) {
									this.clickMiddleMouseButton();
								}
							}
						} else if(this.currentScreen != null) {
							this.currentScreen.handleMouseInput();
						}
					}
				}
			}
		}

		if(this.theWorld != null) {
			if(this.thePlayer != null) {
				++this.field_6300_ab;
				if(this.field_6300_ab == 30) {
					this.field_6300_ab = 0;
					this.theWorld.joinEntityInSurroundings(this.thePlayer);
				}
			}

			this.theWorld.difficultySetting = this.gameSettings.difficulty;
			if(this.theWorld.multiplayerWorld) {
				this.theWorld.difficultySetting = 3;
			}

			if(!this.isWorldLoaded) {
				this.entityRenderer.updateRenderer();
			}

			if(!this.isWorldLoaded) {
				this.renderGlobal.func_945_d();
			}

			if(!this.isWorldLoaded) {
				this.theWorld.func_633_c();
			}

			if(!this.isWorldLoaded || this.isMultiplayerWorld()) {
				this.theWorld.func_21114_a(this.gameSettings.difficulty > 0, true);
				this.theWorld.tick();
			}

			if(!this.isWorldLoaded && this.theWorld != null) {
				this.theWorld.randomDisplayUpdates(MathHelper.floor_double(this.thePlayer.posX), MathHelper.floor_double(this.thePlayer.posY), MathHelper.floor_double(this.thePlayer.posZ));
			}

			if(!this.isWorldLoaded) {
				this.effectRenderer.updateEffects();
			}
		}

		this.systemTime = System.currentTimeMillis();
	}

	private void forceReload() {
		System.out.println("FORCING RELOAD!");
		this.sndManager = new SoundManager();
		this.sndManager.loadSoundSettings(this.gameSettings);
	}

	public boolean isMultiplayerWorld() {
		return this.theWorld != null && this.theWorld.multiplayerWorld;
	}

	public void startWorld(String var1, String var2, long var3) {
		this.changeWorld1((World)null);
		System.gc();
		if(this.field_22008_V.func_22175_a(var1)) {
			this.func_22002_b(var1, var2);
		} else {
			ISaveHandler var5 = this.field_22008_V.func_22174_a(var1, false);
			World var6 = new World(var5, var2, var3);
			if(var6.isNewWorld) {
				this.changeWorld2(var6, "Generating level");
			} else {
				this.changeWorld2(var6, "Loading level");
			}
		}

	}

	public void usePortal() {
		if(this.thePlayer.dimension == -1) {
			this.thePlayer.dimension = 0;
		} else {
			this.thePlayer.dimension = -1;
		}

		this.theWorld.setEntityDead(this.thePlayer);
		this.thePlayer.isDead = false;
		double var1 = this.thePlayer.posX;
		double var3 = this.thePlayer.posZ;
		double var5 = 8.0D;
		World var7;
		if(this.thePlayer.dimension == -1) {
			var1 /= var5;
			var3 /= var5;
			this.thePlayer.setLocationAndAngles(var1, this.thePlayer.posY, var3, this.thePlayer.rotationYaw, this.thePlayer.rotationPitch);
			this.theWorld.updateEntityWithOptionalForce(this.thePlayer, false);
			var7 = new World(this.theWorld, new WorldProviderHell());
			this.changeWorld(var7, "Entering the Nether", this.thePlayer);
		} else {
			var1 *= var5;
			var3 *= var5;
			this.thePlayer.setLocationAndAngles(var1, this.thePlayer.posY, var3, this.thePlayer.rotationYaw, this.thePlayer.rotationPitch);
			this.theWorld.updateEntityWithOptionalForce(this.thePlayer, false);
			var7 = new World(this.theWorld, new WorldProvider());
			this.changeWorld(var7, "Leaving the Nether", this.thePlayer);
		}

		this.thePlayer.worldObj = this.theWorld;
		this.thePlayer.setLocationAndAngles(var1, this.thePlayer.posY, var3, this.thePlayer.rotationYaw, this.thePlayer.rotationPitch);
		this.theWorld.updateEntityWithOptionalForce(this.thePlayer, false);
		(new Teleporter()).func_4107_a(this.theWorld, this.thePlayer);
	}

	public void changeWorld1(World var1) {
		this.changeWorld2(var1, "");
	}

	public void changeWorld2(World var1, String var2) {
		this.changeWorld(var1, var2, (EntityPlayer)null);
	}

	public void changeWorld(World var1, String var2, EntityPlayer var3) {
		this.field_22009_h = null;
		this.loadingScreen.printText(var2);
		this.loadingScreen.displayLoadingString("");
		this.sndManager.func_331_a((String)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		if(this.theWorld != null) {
			this.theWorld.func_651_a(this.loadingScreen);
		}

		this.theWorld = var1;
		if(var1 != null) {
			this.playerController.func_717_a(var1);
			if(!this.isMultiplayerWorld()) {
				if(var3 == null) {
					this.thePlayer = (EntityPlayerSP)var1.func_4085_a(EntityPlayerSP.class);
				}
			} else if(this.thePlayer != null) {
				this.thePlayer.preparePlayerToSpawn();
				if(var1 != null) {
					var1.entityJoinedWorld(this.thePlayer);
				}
			}

			if(!var1.multiplayerWorld) {
				this.func_6255_d(var2);
			}

			if(this.thePlayer == null) {
				this.thePlayer = (EntityPlayerSP)this.playerController.func_4087_b(var1);
				this.thePlayer.preparePlayerToSpawn();
				this.playerController.flipPlayer(this.thePlayer);
			}

			this.thePlayer.movementInput = new MovementInputFromOptions(this.gameSettings);
			if(this.renderGlobal != null) {
				this.renderGlobal.func_946_a(var1);
			}

			if(this.effectRenderer != null) {
				this.effectRenderer.clearEffects(var1);
			}

			this.playerController.func_6473_b(this.thePlayer);
			if(var3 != null) {
				var1.func_6464_c();
			}

			IChunkProvider var4 = var1.func_21118_q();
			if(var4 instanceof ChunkProviderLoadOrGenerate) {
				ChunkProviderLoadOrGenerate var5 = (ChunkProviderLoadOrGenerate)var4;
				int var6 = MathHelper.floor_float((float)((int)this.thePlayer.posX)) >> 4;
				int var7 = MathHelper.floor_float((float)((int)this.thePlayer.posZ)) >> 4;
				var5.func_21110_c(var6, var7);
			}

			var1.spawnPlayerWithLoadedChunks(this.thePlayer);
			if(var1.isNewWorld) {
				var1.func_651_a(this.loadingScreen);
			}

			this.field_22009_h = this.thePlayer;
		} else {
			this.thePlayer = null;
		}

		System.gc();
		this.systemTime = 0L;
	}

	private void func_22002_b(String var1, String var2) {
		this.loadingScreen.printText("Converting World to " + this.field_22008_V.func_22178_a());
		this.loadingScreen.displayLoadingString("This may take a while :)");
		this.field_22008_V.func_22171_a(var1, this.loadingScreen);
		this.startWorld(var1, var2, 0L);
	}

	private void func_6255_d(String var1) {
		this.loadingScreen.printText(var1);
		this.loadingScreen.displayLoadingString("Building terrain");
		short var2 = 128;
		int var3 = 0;
		int var4 = var2 * 2 / 16 + 1;
		var4 *= var4;
		IChunkProvider var5 = this.theWorld.func_21118_q();
		ChunkCoordinates var6 = this.theWorld.func_22137_s();
		if(this.thePlayer != null) {
			var6.field_22395_a = (int)this.thePlayer.posX;
			var6.field_22396_c = (int)this.thePlayer.posZ;
		}

		if(var5 instanceof ChunkProviderLoadOrGenerate) {
			ChunkProviderLoadOrGenerate var7 = (ChunkProviderLoadOrGenerate)var5;
			var7.func_21110_c(var6.field_22395_a >> 4, var6.field_22396_c >> 4);
		}

		for(int var10 = -var2; var10 <= var2; var10 += 16) {
			for(int var8 = -var2; var8 <= var2; var8 += 16) {
				this.loadingScreen.setLoadingProgress(var3++ * 100 / var4);
				this.theWorld.getBlockId(var6.field_22395_a + var10, 64, var6.field_22396_c + var8);

				while(this.theWorld.func_6465_g()) {
				}
			}
		}

		this.loadingScreen.displayLoadingString("Simulating world for a bit");
		this.theWorld.func_656_j();
	}

	public String func_6241_m() {
		return this.renderGlobal.func_953_b();
	}

	public String func_6262_n() {
		return this.renderGlobal.func_957_c();
	}

	public String func_21002_o() {
		return this.theWorld.func_21119_g();
	}

	public String func_6245_o() {
		return "P: " + this.effectRenderer.getStatistics() + ". T: " + this.theWorld.func_687_d();
	}

	public void respawn() {
		if(!this.theWorld.worldProvider.canRespawnHere()) {
			this.usePortal();
		}

		ChunkCoordinates var1 = this.theWorld.func_22137_s();
		IChunkProvider var2 = this.theWorld.func_21118_q();
		if(var2 instanceof ChunkProviderLoadOrGenerate) {
			ChunkProviderLoadOrGenerate var3 = (ChunkProviderLoadOrGenerate)var2;
			var3.func_21110_c(var1.field_22395_a >> 4, var1.field_22396_c >> 4);
		}

		this.theWorld.setSpawnLocation();
		this.theWorld.updateEntityList();
		int var4 = 0;
		if(this.thePlayer != null) {
			var4 = this.thePlayer.entityId;
			this.theWorld.setEntityDead(this.thePlayer);
		}

		this.field_22009_h = null;
		this.thePlayer = (EntityPlayerSP)this.playerController.func_4087_b(this.theWorld);
		this.field_22009_h = this.thePlayer;
		this.thePlayer.preparePlayerToSpawn();
		this.playerController.flipPlayer(this.thePlayer);
		this.theWorld.spawnPlayerWithLoadedChunks(this.thePlayer);
		this.thePlayer.movementInput = new MovementInputFromOptions(this.gameSettings);
		this.thePlayer.entityId = var4;
		this.thePlayer.func_6420_o();
		this.playerController.func_6473_b(this.thePlayer);
		this.func_6255_d("Respawning");
		if(this.currentScreen instanceof GuiGameOver) {
			this.displayGuiScreen((GuiScreen)null);
		}

	}

	public NetClientHandler func_20001_q() {
		return this.thePlayer instanceof EntityClientPlayerMP ? ((EntityClientPlayerMP)this.thePlayer).sendQueue : null;
	}

	public static boolean func_22006_t() {
		return field_21900_a == null || !field_21900_a.gameSettings.field_22277_y;
	}

	public static boolean func_22001_u() {
		return field_21900_a != null && field_21900_a.gameSettings.fancyGraphics;
	}

	public static boolean func_22005_v() {
		return field_21900_a != null && field_21900_a.gameSettings.field_22278_j;
	}

	public static boolean func_22007_w() {
		return field_21900_a != null && field_21900_a.gameSettings.showDebugInfo;
	}

	public boolean func_22003_b(String var1) {
		if(!this.isMultiplayerWorld() && var1.startsWith("/")) {
			SingleplayerCommands.processCommand(var1);
			return true;
		}

		return false;
	}
}
