package net.minecraft.src;

import net.lax1dude.eaglercraft.Random;

public class WorldGenHouse extends WorldGenerator {
	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		int width = random.nextInt(3) + 7;
		int depth = random.nextInt(3) + 7;
		int wallHeight = random.nextInt(3)+ 3;
		int minX = x - width / 2;
		int maxX = minX + width - 1;
		int minZ = z - depth / 2;
		int maxZ = minZ + depth - 1;
		int roof = (width - 1) / 2;
		int floorY = 127;

		for (int houseX = minX; houseX <= maxX; houseX++) {
			for (int houseZ = minZ; houseZ <= maxZ; houseZ++) {
				int surfaceY = world.getHeightValue(houseX, houseZ);
				int groundY = surfaceY - 1;
				if (surfaceY < 4 || surfaceY >= 128) {
					return false;
				}

				if (!world.getBlockMaterial(houseX, groundY, houseZ).getIsSolid()) {
					return false;
				}
				floorY = Math.min(floorY, groundY);
			}
		}

		if (floorY < 4 || floorY > 118 || floorY + wallHeight + roof + 1 >= 128) {
			return false;
		}

		int[] doorway = getDoorway(world, minX, maxX, minZ, maxZ, floorY);
		if (doorway == null) {
			return false;
		}

		int roofTop = floorY + wallHeight + roof + 1;
		for (int houseX = minX; houseX <= maxX; houseX++) {
			for (int houseZ = minZ; houseZ <= maxZ; houseZ++) {
				int groundY = world.getHeightValue(houseX, houseZ) - 1;
				for (int underY = groundY + 1; underY < floorY; underY++) {
					world.setBlock(houseX, underY, houseZ, Block.cobblestone.blockID);
				}
				world.setBlock(houseX, floorY - 1, houseZ, Block.cobblestone.blockID);
				world.setBlock(houseX, floorY, houseZ, Block.planks.blockID);
				for (int clearY = floorY + 1; clearY <= roofTop; clearY++) {
					world.setBlock(houseX, clearY, houseZ, 0);
				}
			}
		}

		for (int wallY = floorY + 1; wallY <= floorY + wallHeight; wallY++) {
			for (int houseX = minX; houseX <= maxX; houseX++) {
				for (int houseZ = minZ; houseZ <= maxZ; houseZ++) {
					if (houseX != minX && houseX != maxX && houseZ != minZ && houseZ != maxZ) {
						continue;
					}

					int wallBlock = random.nextInt(5) == 0 ? Block.cobblestoneMossy.blockID : Block.cobblestone.blockID;
					world.setBlock(houseX, wallY, houseZ, wallBlock);
				}
			}
		}

		int windowY = floorY + 2;
		int windowX = minX + width / 2;
		int windowZ = minZ + depth / 2;
		world.setBlock(minX + width / 2 - 2, windowY, minZ, Block.glass.blockID);
		world.setBlock(minX + width / 2 + 2, windowY, minZ, Block.glass.blockID);
		world.setBlock(windowX, windowY, maxZ, Block.glass.blockID);
		world.setBlock(minX, windowY, windowZ, Block.glass.blockID);
		world.setBlock(maxX, windowY, windowZ, Block.glass.blockID);
		world.setBlockAndMetadata(doorway[0], floorY + 1, doorway[1], Block.doorWood.blockID, doorway[2]);
		world.setBlockAndMetadata(doorway[0], floorY + 2, doorway[1], Block.doorWood.blockID, doorway[2] + 8);

		for (int roofX = minX; roofX <= maxX; roofX++) {
			int roofY = floorY + wallHeight + Math.min(roofX - minX, maxX - roofX) + 1;
			for (int mixY = floorY + wallHeight + 1; mixY < roofY; mixY++) {
				int mixBlock = random.nextInt(5) == 0 ? Block.cobblestoneMossy.blockID : Block.cobblestone.blockID;
				world.setBlock(roofX, mixY, minZ, mixBlock);
				world.setBlock(roofX, mixY, maxZ, mixBlock);
			}
			for (int roofZ = minZ - 1; roofZ <= maxZ + 1; roofZ++) {
				world.setBlock(roofX, roofY, roofZ, Block.planks.blockID);
			}
		}

		if (random.nextInt(8) == 0) {
			int chestX = minX + 1;
			int chestY = floorY + 1;
			int chestZ = minZ + 1;
			if (!world.setBlockWithNotify(chestX, chestY, chestZ, Block.crate.blockID)) {
				throw new IllegalStateException("no place village house chest!!!");
			}
			TileEntityChest chest = (TileEntityChest) world.getBlockTileEntity(chestX, chestY, chestZ);
			if (chest == null) {
				throw new IllegalStateException("chest is missing its inventory! saiyan pls fix this!!!!");
			}
			chest.setInventorySlotContents(0, new ItemStack(Item.seeds, random.nextInt(4) + 1));
			chest.setInventorySlotContents(1, new ItemStack(Item.gunpowder, random.nextInt(3) + 1));
			chest.setInventorySlotContents(2, new ItemStack(Item.silk, random.nextInt(3) + 1));
		}

		return true;
	}

	private int[] getDoorway(World world, int minX, int maxX, int minZ, int maxZ, int floorY) {
		for (int x = minX + 1; x < maxX; x++) {
			if (world.getHeightValue(x, minZ) - 1 == floorY) {
				return new int[] { x, minZ, 0 };
			}
		}
		for (int x = minX + 1; x < maxX; x++) {
			if (world.getHeightValue(x, maxZ) - 1 == floorY) {
				return new int[] { x, maxZ, 2 };
			}
		}
		for (int z = minZ + 1; z < maxZ; z++) {
			if (world.getHeightValue(minX, z) - 1 == floorY) {
				return new int[] { minX, z, 3 };
			}
		}
		for (int z = minZ + 1; z < maxZ; z++) {
			if (world.getHeightValue(maxX, z) - 1 == floorY) {
				return new int[] { maxX, z, 1 };
			}
		}
		return null;
	}
}
