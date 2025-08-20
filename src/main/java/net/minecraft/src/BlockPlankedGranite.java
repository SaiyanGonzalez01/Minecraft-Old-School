package net.minecraft.src;

public class BlockPlankedGranite extends Block {
	public BlockPlankedGranite(int var1, int var2) {
		super(var1, var2, Material.rock);
    this.blockIndexInTexture = 174;
	}

  public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? 4 : (var1 == 0 ? 4 : 174);
	}
}
