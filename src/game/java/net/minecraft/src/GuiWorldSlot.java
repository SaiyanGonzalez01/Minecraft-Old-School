package net.minecraft.src;

import java.util.Date;

class GuiWorldSlot extends GuiSlot {
	final GuiSelectWorld field_22266_a;

	public GuiWorldSlot(GuiSelectWorld var1) {
		super(var1.mc, var1.width, var1.height, 32, var1.height - 64, 36);
		this.field_22266_a = var1;
	}

	protected int func_22249_a() {
		return GuiSelectWorld.func_22090_a(this.field_22266_a).size();
	}

	protected void func_22247_a(int var1, boolean var2) {
		GuiSelectWorld.func_22089_a(this.field_22266_a, var1);
		boolean var3 = GuiSelectWorld.func_22086_b(this.field_22266_a) >= 0 && GuiSelectWorld.func_22086_b(this.field_22266_a) < this.func_22249_a();
		GuiSelectWorld.func_22083_c(this.field_22266_a).enabled = var3;
		GuiSelectWorld.func_22085_d(this.field_22266_a).enabled = var3;
		GuiSelectWorld.func_22092_e(this.field_22266_a).enabled = var3;
		GuiSelectWorld.getExportButton(this.field_22266_a).enabled = var3;
		if(var2 && var3) {
			this.field_22266_a.selectWorld(var1);
		}

	}

	protected boolean func_22246_a(int var1) {
		return var1 == GuiSelectWorld.func_22086_b(this.field_22266_a);
	}

	protected int func_22245_b() {
		return GuiSelectWorld.func_22090_a(this.field_22266_a).size() * 36;
	}

	protected void func_22248_c() {
		this.field_22266_a.drawDefaultBackground();
	}

	protected void func_22242_a(int var1, int var2, int var3, int var4, Tessellator var5) {
		SaveFormatComparator var6 = (SaveFormatComparator)GuiSelectWorld.func_22090_a(this.field_22266_a).get(var1);
		String var7 = var6.func_22162_b();
		if(var7 == null || MathHelper.func_22282_a(var7)) {
			var7 = GuiSelectWorld.func_22087_f(this.field_22266_a) + " " + (var1 + 1);
		}

		String var8 = var6.func_22164_a();
		var8 = var8 + " (" + GuiSelectWorld.func_22093_g(this.field_22266_a).format(new Date(var6.func_22163_e()));
		long var9 = var6.func_22159_c();
		var8 = var8 + ", " + (float)(var9 / 1024L * 100L / 1024L) / 100.0F + " MB)";
		String var11 = "";
		if(var6.func_22161_d()) {
			var11 = GuiSelectWorld.func_22088_h(this.field_22266_a) + " " + var11;
		}

		this.field_22266_a.drawString(this.field_22266_a.fontRenderer, var7, var2 + 2, var3 + 1, 16777215);
		this.field_22266_a.drawString(this.field_22266_a.fontRenderer, var8, var2 + 2, var3 + 12, 8421504);
		this.field_22266_a.drawString(this.field_22266_a.fontRenderer, var11, var2 + 2, var3 + 12 + 10, 8421504);
	}
}
