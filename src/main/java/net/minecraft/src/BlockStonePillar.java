package net.minecraft.src;

public class BlockStonePillar extends Block {
	public BlockStonePillar(int var1, int var2) {
		super(var1, var2, Material.rock);
    this.blockIndexInTexture = 126;
	}

	public int idDropped(int var1, Random var2) {
		return Block.cobblestone.blockID;
	}
  
  public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? 127 : (var1 == 0 ? 127 : 126);
	}
}
