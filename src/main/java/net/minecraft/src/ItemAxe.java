package net.minecraft.src;

public class ItemAxe extends ItemTool {
	private static Block[] blocksEffectiveAgainst = new Block[]{Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.workbench, Block.stairCompactPlanks, Block.jukebox, Block.doorWood, Block.fence, Block.pressurePlatePlanks, Block.trapdoor};

	protected ItemAxe(int var1, EnumToolMaterial var2) {
		super(var1, 3, var2, blocksEffectiveAgainst);
	}
}
