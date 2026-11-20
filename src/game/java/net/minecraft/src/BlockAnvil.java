package net.minecraft.src;

import java.util.ArrayList;
import net.lax1dude.eaglercraft.Random;

public class BlockAnvil extends Block {
	protected BlockAnvil(int var1, int var2) {
		super(var1, var2, Material.rock);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public boolean blockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5) {
		var5.displayAnvilGUI(var2, var3, var4);
		return true;
	}

	public int getRenderType() {
		return 16;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void getCollidingBoundingBoxes(World var1, int var2, int var3, int var4, AxisAlignedBB var5, ArrayList var6) {
		this.setBlockBounds(0.0F, 0.0F, 3.0F / 16.0F, 1.0F, 2.0F / 16.0F, 13.0F / 16.0F);
		super.getCollidingBoundingBoxes(var1, var2, var3, var4, var5, var6);
		this.setBlockBounds(5.0F / 16.0F, 2.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 10.0F / 16.0F, 11.0F / 16.0F);
		super.getCollidingBoundingBoxes(var1, var2, var3, var4, var5, var6);
		this.setBlockBounds(0.0F, 10.0F / 16.0F, 3.0F / 16.0F, 1.0F, 1.0F, 13.0F / 16.0F);
		super.getCollidingBoundingBoxes(var1, var2, var3, var4, var5, var6);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public int idDropped(int var1, Random var2) {
		return this.blockID;
	}
}
