package net.minecraft.src;

public final class BlockStep {
	private BlockStep() {
	}

	public static void setBounds(Block block, int model) {
		if (model == Hammerable.MODEL_SLAB) {
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		} else {
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	public static int getTexture(Block block, int side, int model) {
		if ((model == Hammerable.MODEL_SLAB || model == Hammerable.MODEL_DOUBLE_SLAB) && block == Block.stonePolished) {
			return side <= 1 ? block.getBlockTextureFromSide(side) : 5;
		}

		return block.getBlockTextureFromSide(side);
	}
}
