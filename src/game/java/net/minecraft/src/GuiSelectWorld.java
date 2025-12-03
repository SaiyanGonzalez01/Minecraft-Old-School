package net.minecraft.src;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import dev.colbster937.eaglercraft.gui.GuiScreenCreateOrImport;
import dev.colbster937.eaglercraft.utils.I18n;
import dev.colbster937.eaglercraft.utils.SaveUtils;

public class GuiSelectWorld extends GuiScreen {
	private final DateFormat field_22102_i = new SimpleDateFormat();
	protected GuiScreen parentScreen;
	protected String screenTitle = "Select world";
	private boolean selected = false;
	private int field_22101_l;
	private List field_22100_m;
	private GuiWorldSlot field_22099_n;
	private String field_22098_o;
	private String field_22097_p;
	private boolean field_22096_q;
	private GuiButton field_22095_r;
	private GuiButton field_22104_s;
	private GuiButton field_22103_t;
	private GuiButton buttonExport;

	public GuiSelectWorld(GuiScreen var1) {
		this.parentScreen = var1;
	}

	public void initGui() {
		StringTranslate var1 = StringTranslate.getInstance();
		this.screenTitle = var1.translateKey("selectWorld.title");
		this.field_22098_o = var1.translateKey("selectWorld.world");
		this.field_22097_p = var1.translateKey("selectWorld.conversion");
		this.func_22084_k();
		this.field_22099_n = new GuiWorldSlot(this);
		this.field_22099_n.func_22240_a(this.controlList, 4, 5);
		this.initGui2();
	}

	private void func_22084_k() {
		ISaveFormat var1 = this.mc.func_22004_c();
		this.field_22100_m = var1.func_22176_b();
		Collections.sort(this.field_22100_m);
		this.field_22101_l = -1;
	}

	protected String func_22091_c(int var1) {
		return ((SaveFormatComparator)this.field_22100_m.get(var1)).func_22164_a();
	}

	protected String func_22094_d(int var1) {
		String var2 = ((SaveFormatComparator)this.field_22100_m.get(var1)).func_22162_b();
		if(var2 == null || MathHelper.func_22282_a(var2)) {
			StringTranslate var3 = StringTranslate.getInstance();
			var2 = var3.translateKey("selectWorld.world") + " " + (var1 + 1);
		}

		return var2;
	}

	public void initGui2() {
		StringTranslate var1 = StringTranslate.getInstance();
		this.controlList.add(this.field_22104_s = new GuiButton(1, this.width / 2 - 154, this.height - 52, 150, 20, var1.translateKey("selectWorld.select")));
		this.controlList.add(this.field_22095_r = new GuiButton(6, this.width / 2 - 154, this.height - 28, 70, 20, var1.translateKey("selectWorld.rename")));
		this.controlList.add(this.field_22103_t = new GuiButton(2, this.width / 2 - 74, this.height - 28, 70, 20, var1.translateKey("selectWorld.delete")));
		this.controlList.add(this.buttonExport = new GuiButton(4, this.width / 2 + 4, this.height - 28, 70, 20, I18n.format("selectWorld.export")));
		this.controlList.add(new GuiButton(3, this.width / 2 + 4, this.height - 52, 150, 20, var1.translateKey("selectWorld.create")));
		this.controlList.add(new GuiButton(0, this.width / 2 + 84, this.height - 28, 70, 20, var1.translateKey("gui.cancel")));
		this.field_22104_s.enabled = false;
		this.field_22095_r.enabled = false;
		this.field_22103_t.enabled = false;
		this.buttonExport.enabled = false;
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.enabled) {
			if(var1.id == 2) {
				String var2 = this.func_22094_d(this.field_22101_l);
				if(var2 != null) {
					this.field_22096_q = true;
					StringTranslate var3 = StringTranslate.getInstance();
					String var4 = var3.translateKey("selectWorld.deleteQuestion");
					String var5 = "\'" + var2 + "\' " + var3.translateKey("selectWorld.deleteWarning");
					String var6 = var3.translateKey("selectWorld.deleteButton");
					String var7 = var3.translateKey("gui.cancel");
					GuiYesNo var8 = new GuiYesNo(this, var4, var5, var6, var7, this.field_22101_l);
					this.mc.displayGuiScreen(var8);
				}
			} else if(var1.id == 1) {
				this.selectWorld(this.field_22101_l);
			} else if(var1.id == 3) {
				this.mc.displayGuiScreen(new GuiScreenCreateOrImport(this));
			} else if(var1.id == 6) {
				this.mc.displayGuiScreen(new GuiRenameWorld(this, this.func_22091_c(this.field_22101_l)));
			} else if(var1.id == 0) {
				this.mc.displayGuiScreen(this.parentScreen);
			} else if(var1.id == 4) {
				SaveUtils.i.export(this.mc.loadingScreen, this.func_22091_c(this.field_22101_l));
			} else {
				this.field_22099_n.func_22241_a(var1);
			}

		}
	}

	public void selectWorld(int var1) {
		this.mc.displayGuiScreen((GuiScreen)null);
		if(!this.selected) {
			this.selected = true;
			this.mc.playerController = new PlayerControllerSP(this.mc);
			String var2 = this.func_22091_c(var1);
			if(var2 == null) {
				var2 = "World" + var1;
			}

			this.mc.startWorld(var2, this.func_22094_d(var1), 0L);
			this.mc.displayGuiScreen((GuiScreen)null);
		}
	}

	public void deleteWorld(boolean var1, int var2) {
		if(this.field_22096_q) {
			this.field_22096_q = false;
			if(var1) {
				ISaveFormat var3 = this.mc.func_22004_c();
				var3.func_22177_c();
				var3.func_22172_c(this.func_22091_c(var2));
				this.func_22084_k();
			}

			this.mc.displayGuiScreen(this);
		}

	}

	public void drawScreen(int var1, int var2, float var3) {
		this.field_22099_n.func_22243_a(var1, var2, var3);
		this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 20, 16777215);
		super.drawScreen(var1, var2, var3);
	}

	static List func_22090_a(GuiSelectWorld var0) {
		return var0.field_22100_m;
	}

	static int func_22089_a(GuiSelectWorld var0, int var1) {
		return var0.field_22101_l = var1;
	}

	static int func_22086_b(GuiSelectWorld var0) {
		return var0.field_22101_l;
	}

	static GuiButton func_22083_c(GuiSelectWorld var0) {
		return var0.field_22104_s;
	}

	static GuiButton func_22085_d(GuiSelectWorld var0) {
		return var0.field_22095_r;
	}

	static GuiButton func_22092_e(GuiSelectWorld var0) {
		return var0.field_22103_t;
	}

	static GuiButton getExportButton(GuiSelectWorld var0) {
		return var0.buttonExport;
	}

	static String func_22087_f(GuiSelectWorld var0) {
		return var0.field_22098_o;
	}

	static DateFormat func_22093_g(GuiSelectWorld var0) {
		return var0.field_22102_i;
	}

	static String func_22088_h(GuiSelectWorld var0) {
		return var0.field_22097_p;
	}
}
