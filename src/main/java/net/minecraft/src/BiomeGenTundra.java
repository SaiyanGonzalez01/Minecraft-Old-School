package net.minecraft.src;

public class BiomeGenTundra extends BiomeGenBase {
  public BiomeGenTundra() {
		this.spawnableCreatureList.add(new SpawnListEntry(EntityIceSlime.class, 1));
	}

}
