package net.minecraft.src;

public class BlockSalt extends BlockCrystal {
	protected BlockSalt(int var1, int var2) {
		super(var1, var2);
	}

	public int quantityDropped(Random var1) {
		return 2 + var1.nextInt(3);
	}

	public int idDropped(int var1, Random var2) {
		return Item.saltI.shiftedIndex;
	}
}
