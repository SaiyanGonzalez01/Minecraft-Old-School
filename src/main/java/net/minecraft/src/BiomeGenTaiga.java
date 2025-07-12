package net.minecraft.src;

public class BiomeGenTaiga extends BiomeGenBase {
	public BiomeGenTaiga() {
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 2));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityIceSlime.class, 8));
	}

	public WorldGenerator getRandomWorldGenForTrees(Random var1) {
		return (WorldGenerator)(var1.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2());
	}
}
