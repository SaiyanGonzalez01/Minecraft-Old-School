package net.minecraft.src;

import java.util.List;
import net.lax1dude.eaglercraft.Random;

import net.lax1dude.eaglercraft.internal.buffer.FloatBuffer;
import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class EntityRenderer {
	private Minecraft mc;
	private float farPlaneDistance = 0.0F;
	public ItemRenderer itemRenderer;
	private int field_1386_j;
	private Entity field_1385_k = null;
	private MouseFilter field_22235_l = new MouseFilter();
	private MouseFilter field_22234_m = new MouseFilter();
	private MouseFilter field_22233_n = new MouseFilter();
	private MouseFilter field_22232_o = new MouseFilter();
	private MouseFilter field_22231_p = new MouseFilter();
	private MouseFilter field_22229_q = new MouseFilter();
	private float field_22228_r = 4.0F;
	private float field_22227_s = 4.0F;
	private float field_22226_t = 0.0F;
	private float field_22225_u = 0.0F;
	private float field_22224_v = 0.0F;
	private float field_22223_w = 0.0F;
	private float field_22222_x = 0.0F;
	private float field_22221_y = 0.0F;
	private float field_22220_z = 0.0F;
	private float field_22230_A = 0.0F;
	private double field_21155_l = 1.0D;
	private double field_21154_m = 0.0D;
	private double field_21153_n = 0.0D;
	private long field_1384_l = System.currentTimeMillis();
	private Random random = new Random();
	volatile int field_1394_b = 0;
	volatile int field_1393_c = 0;
	FloatBuffer field_1392_d = GLAllocation.createDirectFloatBuffer(16);
	float fogColorRed;
	float fogColorGreen;
	float fogColorBlue;
	private float field_1382_n;
	private float field_1381_o;

	public EntityRenderer(Minecraft var1) {
		this.mc = var1;
		this.itemRenderer = new ItemRenderer(var1);
	}

	public void updateRenderer() {
		this.field_1382_n = this.field_1381_o;
		this.field_22227_s = this.field_22228_r;
		this.field_22225_u = this.field_22226_t;
		this.field_22223_w = this.field_22224_v;
		this.field_22221_y = this.field_22222_x;
		this.field_22230_A = this.field_22220_z;
		if (this.mc.field_22009_h == null) {
			this.mc.field_22009_h = this.mc.thePlayer;
		}

		float var1 = this.mc.theWorld.getLightBrightness(MathHelper.floor_double(this.mc.field_22009_h.posX),
				MathHelper.floor_double(this.mc.field_22009_h.posY), MathHelper.floor_double(this.mc.field_22009_h.posZ));
		float var2 = (float) (3 - this.mc.gameSettings.renderDistance) / 3.0F;
		float var3 = var1 * (1.0F - var2) + var2;
		this.field_1381_o += (var3 - this.field_1381_o) * 0.1F;
		++this.field_1386_j;
		this.itemRenderer.updateEquippedItem();
		if (this.mc.isRaining) {
			this.addRainParticles();
		}

	}

	public void getMouseOver(float var1) {
		if (this.mc.field_22009_h != null) {
			if (this.mc.theWorld != null) {
				double var2 = (double) this.mc.playerController.getBlockReachDistance();
				this.mc.objectMouseOver = this.mc.field_22009_h.rayTrace(var2, var1);
				double var4 = var2;
				Vec3D var6 = this.mc.field_22009_h.getPosition(var1);
				if (this.mc.objectMouseOver != null) {
					var4 = this.mc.objectMouseOver.hitVec.distanceTo(var6);
				}

				if (this.mc.playerController instanceof PlayerControllerTest) {
					var2 = 32.0D;
				} else {
					if (var4 > 3.0D) {
						var4 = 3.0D;
					}

					var2 = var4;
				}

				Vec3D var7 = this.mc.field_22009_h.getLook(var1);
				Vec3D var8 = var6.addVector(var7.xCoord * var2, var7.yCoord * var2, var7.zCoord * var2);
				this.field_1385_k = null;
				float var9 = 1.0F;
				List var10 = this.mc.theWorld.getEntitiesWithinAABBExcludingEntity(this.mc.field_22009_h,
						this.mc.field_22009_h.boundingBox.addCoord(var7.xCoord * var2, var7.yCoord * var2, var7.zCoord * var2)
								.expand((double) var9, (double) var9, (double) var9));
				double var11 = 0.0D;

				for (int var13 = 0; var13 < var10.size(); ++var13) {
					Entity var14 = (Entity) var10.get(var13);
					if (var14.canBeCollidedWith()) {
						float var15 = var14.getCollisionBorderSize();
						AxisAlignedBB var16 = var14.boundingBox.expand((double) var15, (double) var15, (double) var15);
						MovingObjectPosition var17 = var16.func_1169_a(var6, var8);
						if (var16.isVecInside(var6)) {
							if (0.0D < var11 || var11 == 0.0D) {
								this.field_1385_k = var14;
								var11 = 0.0D;
							}
						} else if (var17 != null) {
							double var18 = var6.distanceTo(var17.hitVec);
							if (var18 < var11 || var11 == 0.0D) {
								this.field_1385_k = var14;
								var11 = var18;
							}
						}
					}
				}

				if (this.field_1385_k != null && !(this.mc.playerController instanceof PlayerControllerTest)) {
					this.mc.objectMouseOver = new MovingObjectPosition(this.field_1385_k);
				}

			}
		}
	}

	private float func_914_d(float var1) {
		EntityLiving var2 = this.mc.field_22009_h;
		float var3 = 70.0F;
		if (var2.isInsideOfMaterial(Material.water)) {
			var3 = 60.0F;
		}

		if (this.mc.currentScreen == null) {
			if (Keyboard.isKeyDown(this.mc.gameSettings.keyBindZoom.keyCode)) {
				var3 /= 4.0F;
			}
		}

		if (var2.health <= 0) {
			float var4 = (float) var2.deathTime + var1;
			var3 /= (1.0F - 500.0F / (var4 + 500.0F)) * 2.0F + 1.0F;
		}

		return var3 + this.field_22221_y + (this.field_22222_x - this.field_22221_y) * var1;
	}

	private void hurtCameraEffect(float var1) {
		EntityLiving var2 = this.mc.field_22009_h;
		float var3 = (float) var2.hurtTime - var1;
		float var4;
		if (var2.health <= 0) {
			var4 = (float) var2.deathTime + var1;
			GL11.glRotatef(40.0F - 8000.0F / (var4 + 200.0F), 0.0F, 0.0F, 1.0F);
		}

		if (var3 >= 0.0F) {
			var3 /= (float) var2.maxHurtTime;
			var3 = MathHelper.sin(var3 * var3 * var3 * var3 * (float) Math.PI);
			var4 = var2.attackedAtYaw;
			GL11.glRotatef(-var4, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-var3 * 14.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(var4, 0.0F, 1.0F, 0.0F);
		}
	}

	private void setupViewBobbing(float var1) {
		if (this.mc.field_22009_h instanceof EntityPlayer) {
			EntityPlayer var2 = (EntityPlayer) this.mc.field_22009_h;
			float var3 = var2.distanceWalkedModified - var2.prevDistanceWalkedModified;
			float var4 = -(var2.distanceWalkedModified + var3 * var1);
			float var5 = var2.field_775_e + (var2.field_774_f - var2.field_775_e) * var1;
			float var6 = var2.field_9329_Q + (var2.field_9328_R - var2.field_9329_Q) * var1;
			GL11.glTranslatef(MathHelper.sin(var4 * (float) Math.PI) * var5 * 0.5F,
					-Math.abs(MathHelper.cos(var4 * (float) Math.PI) * var5), 0.0F);
			GL11.glRotatef(MathHelper.sin(var4 * (float) Math.PI) * var5 * 3.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(Math.abs(MathHelper.cos(var4 * (float) Math.PI - 0.2F) * var5) * 5.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(var6, 1.0F, 0.0F, 0.0F);
		}
	}

	private void orientCamera(float var1) {
		EntityLiving var2 = this.mc.field_22009_h;
		float var3 = var2.yOffset - 1.62F;
		double var4 = var2.prevPosX + (var2.posX - var2.prevPosX) * (double) var1;
		double var6 = var2.prevPosY + (var2.posY - var2.prevPosY) * (double) var1 - (double) var3;
		double var8 = var2.prevPosZ + (var2.posZ - var2.prevPosZ) * (double) var1;
		GL11.glRotatef(this.field_22230_A + (this.field_22220_z - this.field_22230_A) * var1, 0.0F, 0.0F, 1.0F);
		if (var2.isPlayerSleeping()) {
			var3 = (float) ((double) var3 + 1.0D);
			GL11.glTranslatef(0.0F, 0.3F, 0.0F);
			if (!this.mc.gameSettings.field_22273_E) {
				int var10 = this.mc.theWorld.getBlockId(MathHelper.floor_double(var2.posX), MathHelper.floor_double(var2.posY),
						MathHelper.floor_double(var2.posZ));
				if (var10 == Block.field_9262_S.blockID) {
					int var11 = this.mc.theWorld.getBlockMetadata(MathHelper.floor_double(var2.posX),
							MathHelper.floor_double(var2.posY), MathHelper.floor_double(var2.posZ));
					int var12 = var11 & 3;
					GL11.glRotatef((float) (var12 * 90), 0.0F, 1.0F, 0.0F);
				}

				GL11.glRotatef(var2.prevRotationYaw + (var2.rotationYaw - var2.prevRotationYaw) * var1 + 180.0F, 0.0F, -1.0F,
						0.0F);
				GL11.glRotatef(var2.prevRotationPitch + (var2.rotationPitch - var2.prevRotationPitch) * var1, -1.0F, 0.0F,
						0.0F);
			}
		} else if (this.mc.gameSettings.thirdPersonView) {
			double var27 = (double) (this.field_22227_s + (this.field_22228_r - this.field_22227_s) * var1);
			float var13;
			float var28;
			if (this.mc.gameSettings.field_22273_E) {
				var28 = this.field_22225_u + (this.field_22226_t - this.field_22225_u) * var1;
				var13 = this.field_22223_w + (this.field_22224_v - this.field_22223_w) * var1;
				GL11.glTranslatef(0.0F, 0.0F, (float) (-var27));
				GL11.glRotatef(var13, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(var28, 0.0F, 1.0F, 0.0F);
			} else {
				var28 = var2.rotationYaw;
				var13 = var2.rotationPitch;
				double var14 = (double) (-MathHelper.sin(var28 / 180.0F * (float) Math.PI)
						* MathHelper.cos(var13 / 180.0F * (float) Math.PI)) * var27;
				double var16 = (double) (MathHelper.cos(var28 / 180.0F * (float) Math.PI)
						* MathHelper.cos(var13 / 180.0F * (float) Math.PI)) * var27;
				double var18 = (double) (-MathHelper.sin(var13 / 180.0F * (float) Math.PI)) * var27;

				for (int var20 = 0; var20 < 8; ++var20) {
					float var21 = (float) ((var20 & 1) * 2 - 1);
					float var22 = (float) ((var20 >> 1 & 1) * 2 - 1);
					float var23 = (float) ((var20 >> 2 & 1) * 2 - 1);
					var21 *= 0.1F;
					var22 *= 0.1F;
					var23 *= 0.1F;
					MovingObjectPosition var24 = this.mc.theWorld.rayTraceBlocks(
							Vec3D.createVector(var4 + (double) var21, var6 + (double) var22, var8 + (double) var23),
							Vec3D.createVector(var4 - var14 + (double) var21 + (double) var23, var6 - var18 + (double) var22,
									var8 - var16 + (double) var23));
					if (var24 != null) {
						double var25 = var24.hitVec.distanceTo(Vec3D.createVector(var4, var6, var8));
						if (var25 < var27) {
							var27 = var25;
						}
					}
				}

				GL11.glRotatef(var2.rotationPitch - var13, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(var2.rotationYaw - var28, 0.0F, 1.0F, 0.0F);
				GL11.glTranslatef(0.0F, 0.0F, (float) (-var27));
				GL11.glRotatef(var28 - var2.rotationYaw, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(var13 - var2.rotationPitch, 1.0F, 0.0F, 0.0F);
			}
		} else {
			GL11.glTranslatef(0.0F, 0.0F, -0.1F);
		}

		if (!this.mc.gameSettings.field_22273_E) {
			GL11.glRotatef(var2.prevRotationPitch + (var2.rotationPitch - var2.prevRotationPitch) * var1, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(var2.prevRotationYaw + (var2.rotationYaw - var2.prevRotationYaw) * var1 + 180.0F, 0.0F, 1.0F,
					0.0F);
		}

		GL11.glTranslatef(0.0F, var3, 0.0F);
	}

	public void func_21152_a(double var1, double var3, double var5) {
		this.field_21155_l = var1;
		this.field_21154_m = var3;
		this.field_21153_n = var5;
	}

	public void func_21151_b() {
		this.field_21155_l = 1.0D;
	}

	private void setupCameraTransform(float var1, int var2) {
		this.farPlaneDistance = (float) (256 >> this.mc.gameSettings.renderDistance);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		float var3 = 0.07F;
		if (this.mc.gameSettings.anaglyph) {
			GL11.glTranslatef((float) (-(var2 * 2 - 1)) * var3, 0.0F, 0.0F);
		}

		if (this.field_21155_l != 1.0D) {
			GL11.glTranslatef((float) this.field_21154_m, (float) (-this.field_21153_n), 0.0F);
			GL11.glScaled(this.field_21155_l, this.field_21155_l, 1.0D);
			GLU.gluPerspective(this.func_914_d(var1), (float) this.mc.displayWidth / (float) this.mc.displayHeight, 0.05F,
					this.farPlaneDistance);
		} else {
			GLU.gluPerspective(this.func_914_d(var1), (float) this.mc.displayWidth / (float) this.mc.displayHeight, 0.05F,
					this.farPlaneDistance);
		}

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		if (this.mc.gameSettings.anaglyph) {
			GL11.glTranslatef((float) (var2 * 2 - 1) * 0.1F, 0.0F, 0.0F);
		}

		this.hurtCameraEffect(var1);
		if (this.mc.gameSettings.viewBobbing) {
			this.setupViewBobbing(var1);
		}

		float var4 = this.mc.thePlayer.prevTimeInPortal
				+ (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * var1;
		if (var4 > 0.0F) {
			float var5 = 5.0F / (var4 * var4 + 5.0F) - var4 * 0.04F;
			var5 *= var5;
			GL11.glRotatef(var4 * var4 * 1500.0F, 0.0F, 1.0F, 1.0F);
			GL11.glScalef(1.0F / var5, 1.0F, 1.0F);
			GL11.glRotatef(-var4 * var4 * 1500.0F, 0.0F, 1.0F, 1.0F);
		}

		this.orientCamera(var1);
	}

	private void func_4135_b(float var1, int var2) {
		GL11.glLoadIdentity();
		if (this.mc.gameSettings.anaglyph) {
			GL11.glTranslatef((float) (var2 * 2 - 1) * 0.1F, 0.0F, 0.0F);
		}

		GL11.glPushMatrix();
		this.hurtCameraEffect(var1);
		if (this.mc.gameSettings.viewBobbing) {
			this.setupViewBobbing(var1);
		}

		if (!this.mc.gameSettings.thirdPersonView && !this.mc.field_22009_h.isPlayerSleeping()
				&& !this.mc.gameSettings.field_22277_y) {
			this.itemRenderer.renderItemInFirstPerson(var1);
		}

		GL11.glPopMatrix();
		if (!this.mc.gameSettings.thirdPersonView && !this.mc.field_22009_h.isPlayerSleeping()) {
			this.itemRenderer.renderOverlays(var1);
			this.hurtCameraEffect(var1);
		}

		if (this.mc.gameSettings.viewBobbing) {
			this.setupViewBobbing(var1);
		}

	}

	public void func_4136_b(float var1) {
		if (!Display.isActive()) {
			if (System.currentTimeMillis() - this.field_1384_l > 500L) {
				this.mc.func_6252_g();
			}
		} else {
			this.field_1384_l = System.currentTimeMillis();
		}

		if (this.mc.field_6289_L) {
			this.mc.mouseHelper.mouseXYChange();
			float var2 = this.mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
			float var3 = var2 * var2 * var2 * 8.0F;
			float var4 = (float) this.mc.mouseHelper.deltaX * var3;
			float var5 = (float) this.mc.mouseHelper.deltaY * var3;
			byte var6 = 1;
			if (this.mc.gameSettings.invertMouse) {
				var6 = -1;
			}

			if (this.mc.gameSettings.field_22274_D) {
				var4 = this.field_22235_l.func_22386_a(var4, 0.05F * var3);
				var5 = this.field_22234_m.func_22386_a(var5, 0.05F * var3);
			}

			this.mc.thePlayer.func_346_d(var4, var5 * (float) var6);
		}

		if (!this.mc.field_6307_v) {
			ScaledResolution var7 = new ScaledResolution(this.mc.displayWidth, this.mc.displayHeight);
			int var8 = var7.getScaledWidth();
			int var9 = var7.getScaledHeight();
			int var10 = Mouse.getX() * var8 / this.mc.displayWidth;
			int var11 = var9 - Mouse.getY() * var9 / this.mc.displayHeight - 1;
			if (this.mc.theWorld != null) {
				this.renderWorld(var1);
				if (!this.mc.gameSettings.field_22277_y || this.mc.currentScreen != null) {
					this.mc.ingameGUI.renderGameOverlay(var1, this.mc.currentScreen != null, var10, var11);
				}
			} else {
				GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
				GL11.glMatrixMode(GL11.GL_PROJECTION);
				GL11.glLoadIdentity();
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glLoadIdentity();
				this.func_905_b();
			}

			if (this.mc.currentScreen != null) {
				GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
				this.mc.currentScreen.drawScreen(var10, var11, var1);
			}

		}
	}

	public void renderWorld(float var1) {
		if (this.mc.field_22009_h == null) {
			this.mc.field_22009_h = this.mc.thePlayer;
		}

		this.getMouseOver(var1);
		EntityLiving var2 = this.mc.field_22009_h;
		RenderGlobal var3 = this.mc.renderGlobal;
		EffectRenderer var4 = this.mc.effectRenderer;
		double var5 = var2.lastTickPosX + (var2.posX - var2.lastTickPosX) * (double) var1;
		double var7 = var2.lastTickPosY + (var2.posY - var2.lastTickPosY) * (double) var1;
		double var9 = var2.lastTickPosZ + (var2.posZ - var2.lastTickPosZ) * (double) var1;
		IChunkProvider var11 = this.mc.theWorld.func_21118_q();
		int var14;
		if (var11 instanceof ChunkProviderLoadOrGenerate) {
			ChunkProviderLoadOrGenerate var12 = (ChunkProviderLoadOrGenerate) var11;
			int var13 = MathHelper.floor_float((float) ((int) var5)) >> 4;
			var14 = MathHelper.floor_float((float) ((int) var9)) >> 4;
			var12.func_21110_c(var13, var14);
		}

		for (int var15 = 0; var15 < 2; ++var15) {
			if (this.mc.gameSettings.anaglyph) {
				if (var15 == 0) {
					GL11.glColorMask(false, true, true, false);
				} else {
					GL11.glColorMask(true, false, false, false);
				}
			}

			GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
			this.updateFogColor(var1);
			GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
			GL11.glEnable(GL11.GL_CULL_FACE);
			this.setupCameraTransform(var1, var15);
			ClippingHelperImplementation.getInstance();
			if (this.mc.gameSettings.renderDistance < 2) {
				this.func_4140_a(-1);
				var3.func_4142_a(var1);
			}

			GL11.glEnable(GL11.GL_FOG);
			this.func_4140_a(1);
			if (this.mc.gameSettings.field_22278_j) {
				GL11.glShadeModel(GL11.GL_SMOOTH);
			}

			Frustrum var16 = new Frustrum();
			var16.setPosition(var5, var7, var9);
			this.mc.renderGlobal.func_960_a(var16, var1);
			this.mc.renderGlobal.updateRenderers(var2, false);
			this.func_4140_a(0);
			GL11.glEnable(GL11.GL_FOG);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/terrain.png"));
			RenderHelper.disableStandardItemLighting();
			var3.func_943_a(var2, 0, (double) var1);
			GL11.glShadeModel(GL11.GL_FLAT);
			RenderHelper.enableStandardItemLighting();
			var3.func_951_a(var2.getPosition(var1), var16, var1);
			var4.func_1187_b(var2, var1);
			RenderHelper.disableStandardItemLighting();
			this.func_4140_a(0);
			var4.renderParticles(var2, var1);
			EntityPlayer var17;
			if (this.mc.objectMouseOver != null && var2.isInsideOfMaterial(Material.water) && var2 instanceof EntityPlayer) {
				var17 = (EntityPlayer) var2;
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				var3.func_959_a(var17, this.mc.objectMouseOver, 0, var17.inventory.getCurrentItem(), var1);
				var3.drawSelectionBox(var17, this.mc.objectMouseOver, 0, var17.inventory.getCurrentItem(), var1);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
			}

			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			this.func_4140_a(0);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/terrain.png"));
			if (this.mc.gameSettings.fancyGraphics) {
				// GL11.glColorMask(false, false, false, false);
				var14 = var3.func_943_a(var2, 1, (double) var1);
				// GL11.glColorMask(true, true, true, true);
				if (this.mc.gameSettings.anaglyph) {
					if (var15 == 0) {
						GL11.glColorMask(false, true, true, false);
					} else {
						GL11.glColorMask(true, false, false, false);
					}
				}

				if (var14 > 0) {
					var3.func_944_a(1, (double) var1);
				}
			} else {
				var3.func_943_a(var2, 1, (double) var1);
			}

			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL11.GL_BLEND);
			if (this.field_21155_l == 1.0D && var2 instanceof EntityPlayer && this.mc.objectMouseOver != null
					&& !var2.isInsideOfMaterial(Material.water)) {
				var17 = (EntityPlayer) var2;
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				var3.func_959_a(var17, this.mc.objectMouseOver, 0, var17.inventory.getCurrentItem(), var1);
				var3.drawSelectionBox(var17, this.mc.objectMouseOver, 0, var17.inventory.getCurrentItem(), var1);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
			}

			GL11.glDisable(GL11.GL_FOG);
			if (this.field_1385_k != null) {
			}

			this.func_4140_a(0);
			GL11.glEnable(GL11.GL_FOG);
			var3.func_4141_b(var1);
			GL11.glDisable(GL11.GL_FOG);
			this.func_4140_a(1);
			if (this.field_21155_l == 1.0D) {
				GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
				this.func_4135_b(var1, var15);
			}

			if (!this.mc.gameSettings.anaglyph) {
				return;
			}
		}

		GL11.glColorMask(true, true, true, false);
	}

	private void addRainParticles() {
		if (this.mc.gameSettings.fancyGraphics) {
			EntityLiving var1 = this.mc.field_22009_h;
			World var2 = this.mc.theWorld;
			int var3 = MathHelper.floor_double(var1.posX);
			int var4 = MathHelper.floor_double(var1.posY);
			int var5 = MathHelper.floor_double(var1.posZ);
			byte var6 = 16;

			for (int var7 = 0; var7 < 150; ++var7) {
				int var8 = var3 + this.random.nextInt(var6) - this.random.nextInt(var6);
				int var9 = var5 + this.random.nextInt(var6) - this.random.nextInt(var6);
				int var10 = var2.func_696_e(var8, var9);
				int var11 = var2.getBlockId(var8, var10 - 1, var9);
				if (var10 <= var4 + var6 && var10 >= var4 - var6) {
					float var12 = this.random.nextFloat();
					float var13 = this.random.nextFloat();
					if (var11 > 0) {
						this.mc.effectRenderer.addEffect(new EntityRainFX(var2, (double) ((float) var8 + var12),
								(double) ((float) var10 + 0.1F) - Block.blocksList[var11].minY, (double) ((float) var9 + var13)));
					}
				}
			}

		}
	}

	public void func_905_b() {
		ScaledResolution var1 = new ScaledResolution(this.mc.displayWidth, this.mc.displayHeight);
		int var2 = var1.getScaledWidth();
		int var3 = var1.getScaledHeight();
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0D, (double) var2, (double) var3, 0.0D, 1000.0D, 3000.0D);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
	}

	private void updateFogColor(float var1) {
		World var2 = this.mc.theWorld;
		EntityLiving var3 = this.mc.field_22009_h;
		float var4 = 1.0F / (float) (4 - this.mc.gameSettings.renderDistance);
		var4 = 1.0F - (float) Math.pow((double) var4, 0.25D);
		Vec3D var5 = var2.func_4079_a(this.mc.field_22009_h, var1);
		float var6 = (float) var5.xCoord;
		float var7 = (float) var5.yCoord;
		float var8 = (float) var5.zCoord;
		Vec3D var9 = var2.func_4082_d(var1);
		this.fogColorRed = (float) var9.xCoord;
		this.fogColorGreen = (float) var9.yCoord;
		this.fogColorBlue = (float) var9.zCoord;
		this.fogColorRed += (var6 - this.fogColorRed) * var4;
		this.fogColorGreen += (var7 - this.fogColorGreen) * var4;
		this.fogColorBlue += (var8 - this.fogColorBlue) * var4;
		if (var3.isInsideOfMaterial(Material.water)) {
			this.fogColorRed = 0.02F;
			this.fogColorGreen = 0.02F;
			this.fogColorBlue = 0.2F;
		} else if (var3.isInsideOfMaterial(Material.lava)) {
			this.fogColorRed = 0.6F;
			this.fogColorGreen = 0.1F;
			this.fogColorBlue = 0.0F;
		}

		float var10 = this.field_1382_n + (this.field_1381_o - this.field_1382_n) * var1;
		this.fogColorRed *= var10;
		this.fogColorGreen *= var10;
		this.fogColorBlue *= var10;
		if (this.mc.gameSettings.anaglyph) {
			float var11 = (this.fogColorRed * 30.0F + this.fogColorGreen * 59.0F + this.fogColorBlue * 11.0F) / 100.0F;
			float var12 = (this.fogColorRed * 30.0F + this.fogColorGreen * 70.0F) / 100.0F;
			float var13 = (this.fogColorRed * 30.0F + this.fogColorBlue * 70.0F) / 100.0F;
			this.fogColorRed = var11;
			this.fogColorGreen = var12;
			this.fogColorBlue = var13;
		}

		GL11.glClearColor(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 0.0F);
	}

	private void func_4140_a(int var1) {
		EntityLiving var2 = this.mc.field_22009_h;
		GL11.glFog(GL11.GL_FOG_COLOR, this.func_908_a(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 1.0F));
		GL11.glNormal3f(0.0F, -1.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float var3;
		float var4;
		float var5;
		float var6;
		float var7;
		float var8;
		if (var2.isInsideOfMaterial(Material.water)) {
			GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
			GL11.glFogf(GL11.GL_FOG_DENSITY, 0.1F);
			var3 = 0.4F;
			var4 = 0.4F;
			var5 = 0.9F;
			if (this.mc.gameSettings.anaglyph) {
				var6 = (var3 * 30.0F + var4 * 59.0F + var5 * 11.0F) / 100.0F;
				var7 = (var3 * 30.0F + var4 * 70.0F) / 100.0F;
				var8 = (var3 * 30.0F + var5 * 70.0F) / 100.0F;
			}
		} else if (var2.isInsideOfMaterial(Material.lava)) {
			GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
			GL11.glFogf(GL11.GL_FOG_DENSITY, 2.0F);
			var3 = 0.4F;
			var4 = 0.3F;
			var5 = 0.3F;
			if (this.mc.gameSettings.anaglyph) {
				var6 = (var3 * 30.0F + var4 * 59.0F + var5 * 11.0F) / 100.0F;
				var7 = (var3 * 30.0F + var4 * 70.0F) / 100.0F;
				var8 = (var3 * 30.0F + var5 * 70.0F) / 100.0F;
			}
		} else {
			GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
			GL11.glFogf(GL11.GL_FOG_START, this.farPlaneDistance * 0.25F);
			GL11.glFogf(GL11.GL_FOG_END, this.farPlaneDistance);
			if (var1 < 0) {
				GL11.glFogf(GL11.GL_FOG_START, 0.0F);
				GL11.glFogf(GL11.GL_FOG_END, this.farPlaneDistance * 0.8F);
			}

			if (this.mc.theWorld.worldProvider.field_4220_c) {
				GL11.glFogf(GL11.GL_FOG_START, 0.0F);
			}
		}

		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glColorMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT);
	}

	private FloatBuffer func_908_a(float var1, float var2, float var3, float var4) {
		this.field_1392_d.clear();
		this.field_1392_d.put(var1).put(var2).put(var3).put(var4);
		this.field_1392_d.flip();
		return this.field_1392_d;
	}
}
