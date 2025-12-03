package dev.colbster937.eaglercraft.gui;

import dev.colbster937.eaglercraft.utils.I18n;
import dev.colbster937.eaglercraft.utils.SaveUtils;
import dev.colbster937.eaglercraft.utils.ScuffedUtils;
import net.lax1dude.eaglercraft.EagRuntime;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiCreateWorld;
import net.minecraft.src.GuiScreen;
import net.peyton.java.awt.Color;

public class GuiScreenCreateOrImport extends GuiScreen {
  private final GuiScreen parent;
  private final String title;

  public GuiScreenCreateOrImport(GuiScreen parent) {
    this.parent = parent;
    this.title = I18n.format("selectWorld.importOrExport");
  }

  @Override
  public void initGui() {
    controlList.add(new GuiButton(0, (this.width - 200) / 2, this.height / 3 + 5, I18n.format("selectWorld.create")));
    controlList.add(new GuiButton(1, (this.width - 200) / 2, this.height / 3 + 29, I18n.format("selectWorld.import")));
    controlList.add(new GuiButton(2, (this.width - 200) / 2, this.height / 3 + 53, I18n.format("gui.cancel")));
  }

  @Override
  public void drawScreen(int mx, int my, float f) {
    drawDefaultBackground();
    this.drawCenteredString(this.fontRenderer, title, this.width / 2, this.height / 4, Color.WHITE.getRGB());
    super.drawScreen(mx, my, f);
  }

  @Override
  public void updateScreen() {
    super.updateScreen();
    if (EagRuntime.fileChooserHasResult()) {
      SaveUtils.i._import(this.mc.loadingScreen, EagRuntime.getFileChooserResult());
      this.mc.displayGuiScreen(this.parent);
    }
  }

  @Override
  protected void actionPerformed(GuiButton btn) {
    if (btn.enabled) {
      if (btn.id == 0) {
        this.mc.displayGuiScreen(new GuiCreateWorld(this.parent));
      } else if (btn.id == 1) {
        ScuffedUtils.showZipFileChooser();
      } else if (btn.id == 2) {
        this.mc.displayGuiScreen(this.parent);
      }
    }
  }
}