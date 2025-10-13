package net.minecraft.src;

public class ItemSickle extends ItemTool {
	private static Block[] blocksEffectiveAgainst = new Block[]{Block.crops, Block.potatoCrops, Block.netherriceWild, Block.netherriceCrops, Block.leaves};

	protected ItemSickle(int var1, EnumToolMaterial var2) {
		super(var1, 3, var2, blocksEffectiveAgainst);
	}
}
