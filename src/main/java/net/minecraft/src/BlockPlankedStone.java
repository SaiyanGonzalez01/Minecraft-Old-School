package net.minecraft.src;

public class BlockPlankedStone extends Block {
	public BlockPlankedStone(int var1, int var2) {
		super(var1, var2, Material.rock);
    this.blockIndexInTexture = 173;
	}

  public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? 4 : (var1 == 0 ? 4 : 173);
	}
}
