package net.minecraft.src;

public class BiomeGenTundra extends BiomeGenBase {
  public BiomeGenTundra() {
	  	this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityIceSlime.class, 8));
	  	this.spawnableMonsterList.add(new SpawnListEntry(EntityIceSkeleton.class, 6));
	}

}
