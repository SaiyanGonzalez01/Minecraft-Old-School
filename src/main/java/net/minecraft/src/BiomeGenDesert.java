package net.minecraft.src;

public class BiomeGenDesert extends BiomeGenBase {
  public BiomeGenDesert() {
		this.spawnableCreatureList.add(new SpawnListEntry(EntityGiant.class, 2));
	}

}
