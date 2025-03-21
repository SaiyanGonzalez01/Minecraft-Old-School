package net.minecraft.src;

public class BlockPillar extends Block {
	protected BlockPillar(int var1) {
		super(var1, Material.rock);
		this.blockIndexInTexture = 127;
	}

	public int quantityDropped(Random var1) {
		return 1;
	}
	
	public int getBlockTextureFromSideAndMetadata(int var1, int var2) {
		return var1 == 1 ? 127 : (var1 == 0 ? 127 : (var2 == 1 ? 116 : (var2 == 2 ? 117 : 126)));
  
	}
}
