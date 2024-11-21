package net.minecraft.src;

public class BiomeGenDesert extends BiomeGenBase {
  public BiomeGenDesert() {
		this.spawnableCreatureList.add(new SpawnListEntry(EntityGiantZombie.class, 1));
	}

}
