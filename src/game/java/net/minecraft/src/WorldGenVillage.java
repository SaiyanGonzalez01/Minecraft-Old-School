package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

import net.lax1dude.eaglercraft.Random;

public class WorldGenVillage extends WorldGenerator {
	private final WorldGenHouse house = new WorldGenHouse();
	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		List<HouseSite> generatedSites = new ArrayList<HouseSite>();
		int targetHouses = 5 + random.nextInt(9 - 5 + 1);
		for (int attempt = 0; attempt < 500 && generatedSites.size() < targetHouses; attempt++) {
			int siteX = x + random.nextInt(24) - 4;
			int siteZ = z + random.nextInt(22) - 3;
			int surfaceY = world.getHeightValue(siteX, siteZ);
			if (canGenerateHouse(world, siteX, surfaceY, siteZ) && !isTooCloseToExistingSite(generatedSites, siteX, siteZ) && house.generate(world, random, siteX, surfaceY - 1, siteZ)) {
				generatedSites.add(new HouseSite(siteX, surfaceY - 1, siteZ));
			}
		}

		return !generatedSites.isEmpty();
	}

	private boolean isTooCloseToExistingSite(List<HouseSite> sites, int x, int z) {
		for (HouseSite site : sites) {
			int deltaX = site.x - x;
			int deltaZ = site.z - z;
			if (deltaX * deltaX + deltaZ * deltaZ < 16 * 16) {
				return true;
			}
		}
		return false;
	}

	private boolean canGenerateHouse(World world, int x, int surfaceY, int z) {
		if (surfaceY < 4 || surfaceY > 112) {
			return false;
		}

		int surfaceBlock = world.getBlockId(x, surfaceY - 1, z);
		return surfaceBlock == Block.grass.blockID || surfaceBlock == Block.dirt.blockID || surfaceBlock == Block.sand.blockID;
	}

	private static final class HouseSite {
		private final int x;
		private final int y;
		private final int z;

		private HouseSite(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
