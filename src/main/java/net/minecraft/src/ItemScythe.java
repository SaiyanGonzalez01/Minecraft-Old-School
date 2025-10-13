package net.minecraft.src;

public class ItemScythe extends ItemTool {
	private static Block[] blocksEffectiveAgainst = new Block[]{Block.crops, Block.potatoCrops, Block.netherriceWild, Block.netherriceCrops, Block.leaves};

	protected ItemScythe(int var1, EnumToolMaterial var2) {
		super(var1, 3, var2, blocksEffectiveAgainst);
	}
}
