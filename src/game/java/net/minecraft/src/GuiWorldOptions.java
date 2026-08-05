package net.minecraft.src;

public class GuiWorldOptions extends GuiScreen {
    private GuiScreen parentGuiScreen;
    private int selectedDifficulty = 2;
    private int selectedGameMode = 0;
    private GuiButton difficultyButton;
    private GuiButton gameModeButton;

    public GuiWorldOptions(GuiScreen var1) {
        this.parentGuiScreen = var1;
        if(var1 instanceof GuiCreateWorld) {
            GuiCreateWorld parent = (GuiCreateWorld)var1;
            this.selectedDifficulty = parent.getSelectedDifficulty();
            this.selectedGameMode = parent.getSelectedGameMode();
        }
    }

    public void initGui() {
        this.controlList.clear();
        this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, "Done"));
        this.controlList.add(this.difficultyButton = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 40, 200, 20, this.getDifficultyLabel()));
        this.controlList.add(this.gameModeButton = new GuiButton(2, this.width / 2 - 100, this.height / 4 + 68, 200, 20, this.getGameModeLabel()));
    }

    private String getDifficultyLabel() {
        StringTranslate var1 = StringTranslate.getInstance();
        if(this.selectedDifficulty == 0) {
            return "Difficulty: " + var1.translateKey("options.difficulty.peaceful");
        } else if(this.selectedDifficulty == 1) {
            return "Difficulty: " + var1.translateKey("options.difficulty.easy");
        } else if(this.selectedDifficulty == 3) {
            return "Difficulty: " + var1.translateKey("options.difficulty.hard");
        } else {
            return "Difficulty: " + var1.translateKey("options.difficulty.normal");
        }
    }

    private String getGameModeLabel() {
        return this.selectedGameMode == 1 ? "Gamemode: Creative" : "Gamemode: Survival";
    }

    protected void actionPerformed(GuiButton var1) {
        if(var1.id == 0) {
            if(this.parentGuiScreen instanceof GuiCreateWorld) {
                GuiCreateWorld parent = (GuiCreateWorld)this.parentGuiScreen;
                parent.setSelectedDifficulty(this.selectedDifficulty);
                parent.setSelectedGameMode(this.selectedGameMode);
            }

            this.mc.displayGuiScreen(this.parentGuiScreen);
        } else if(var1.id == 1) {
            this.selectedDifficulty = (this.selectedDifficulty + 1) & 3;
            this.difficultyButton.displayString = this.getDifficultyLabel();
        } else if(var1.id == 2) {
            this.selectedGameMode = 1 - this.selectedGameMode;
            this.gameModeButton.displayString = this.getGameModeLabel();
        }

    }
    public void drawScreen(int var1, int var2, float var3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "World Options", this.width / 2, this.height / 4 - 40, 16777215);
        super.drawScreen(var1, var2, var3);
    }
}
