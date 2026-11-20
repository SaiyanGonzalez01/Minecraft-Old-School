package net.minecraft.src;

import java.util.ArrayList;

public final class BlockStairs {
	private BlockStairs() {
	}

	public static int getOrientation(int metadata) {
		return (metadata - Hammerable.STAIR) & 3;
	}

	public static void setBounds(Block block) {
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public static void addCollisionBoxes(int x, int y, int z, int metadata, AxisAlignedBB mask, ArrayList boxes) {
		int orientation = getOrientation(metadata);
		if (orientation == 0) {
			addCollisionBox(x, y, z, 0.0D, 0.0D, 0.0D, 0.5D, 0.5D, 1.0D, mask, boxes);
			addCollisionBox(x, y, z, 0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D, mask, boxes);
		} else if (orientation == 1) {
			addCollisionBox(x, y, z, 0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D, mask, boxes);
			addCollisionBox(x, y, z, 0.5D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D, mask, boxes);
		} else if (orientation == 2) {
			addCollisionBox(x, y, z, 0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D, mask, boxes);
			addCollisionBox(x, y, z, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D, mask, boxes);
		} else {
			addCollisionBox(x, y, z, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D, mask, boxes);
			addCollisionBox(x, y, z, 0.0D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D, mask, boxes);
		}
	}

	private static void addCollisionBox(int x, int y, int z, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, AxisAlignedBB mask, ArrayList boxes) {
		AxisAlignedBB box = AxisAlignedBB.getBoundingBoxFromPool(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
		if (mask.intersectsWith(box)) {
			boxes.add(box);
		}
	}
}
