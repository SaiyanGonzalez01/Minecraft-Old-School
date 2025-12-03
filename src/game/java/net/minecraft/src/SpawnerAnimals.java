package net.minecraft.src;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.peyton.eagler.minecraft.suppliers.EntitySupplier;

public final class SpawnerAnimals {
	private static Set eligibleChunksForSpawning = new HashSet();
	protected static final EntitySupplier[] field_22391_a = new EntitySupplier[]{EntitySpider::new, EntityZombie::new, EntitySkeleton::new};

	protected static ChunkPosition getRandomSpawningPointInChunk(World var0, int var1, int var2) {
		int var3 = var1 + var0.rand.nextInt(16);
		int var4 = var0.rand.nextInt(128);
		int var5 = var2 + var0.rand.nextInt(16);
		return new ChunkPosition(var3, var4, var5);
	}

	public static final int performSpawning(World var0, boolean var1, boolean var2) {
		if(!var1 && !var2) {
			return 0;
		} else {
			eligibleChunksForSpawning.clear();

			int var3;
			int var6;
			for(var3 = 0; var3 < var0.playerEntities.size(); ++var3) {
				EntityPlayer var4 = (EntityPlayer)var0.playerEntities.get(var3);
				int var5 = MathHelper.floor_double(var4.posX / 16.0D);
				var6 = MathHelper.floor_double(var4.posZ / 16.0D);
				byte var7 = 8;

				for(int var8 = -var7; var8 <= var7; ++var8) {
					for(int var9 = -var7; var9 <= var7; ++var9) {
						eligibleChunksForSpawning.add(new ChunkCoordIntPair(var8 + var5, var9 + var6));
					}
				}
			}

			var3 = 0;
			ChunkCoordinates var33 = var0.func_22137_s();
			EnumCreatureType[] var34 = EnumCreatureType.values();
			var6 = var34.length;

			label112:
			for(int var35 = 0; var35 < var6; ++var35) {
				EnumCreatureType var36 = var34[var35];
				if((!var36.func_21168_d() || var2) && (var36.func_21168_d() || var1) && var0.countEntities(var36.getCreatureClass()) <= var36.getMaxNumberOfCreature() * eligibleChunksForSpawning.size() / 256) {
					Iterator var37 = eligibleChunksForSpawning.iterator();

					label109:
					while(true) {
						EntitySupplier[] var12;
						int var13;
						int var15;
						int var16;
						int var17;
						do {
							do {
								ChunkCoordIntPair var10;
								do {
									do {
										if(!var37.hasNext()) {
											continue label112;
										}

										var10 = (ChunkCoordIntPair)var37.next();
										MobSpawnerBase var11 = var0.getWorldChunkManager().func_4074_a(var10);
										var12 = var11.getEntitiesForType(var36);
									} while(var12 == null);
								} while(var12.length == 0);

								var13 = var0.rand.nextInt(var12.length);
								ChunkPosition var14 = getRandomSpawningPointInChunk(var0, var10.chunkXPos * 16, var10.chunkZPos * 16);
								var15 = var14.x;
								var16 = var14.y;
								var17 = var14.z;
							} while(var0.isBlockOpaqueCube(var15, var16, var17));
						} while(var0.getBlockMaterial(var15, var16, var17) != var36.getCreatureMaterial());

						int var18 = 0;

						for(int var19 = 0; var19 < 3; ++var19) {
							int var20 = var15;
							int var21 = var16;
							int var22 = var17;
							byte var23 = 6;

							for(int var24 = 0; var24 < 4; ++var24) {
								var20 += var0.rand.nextInt(var23) - var0.rand.nextInt(var23);
								var21 += var0.rand.nextInt(1) - var0.rand.nextInt(1);
								var22 += var0.rand.nextInt(var23) - var0.rand.nextInt(var23);
								if(func_21203_a(var36, var0, var20, var21, var22)) {
									float var25 = (float)var20 + 0.5F;
									float var26 = (float)var21;
									float var27 = (float)var22 + 0.5F;
									if(var0.getClosestPlayer((double)var25, (double)var26, (double)var27, 24.0D) == null) {
										float var28 = var25 - (float)var33.field_22395_a;
										float var29 = var26 - (float)var33.field_22394_b;
										float var30 = var27 - (float)var33.field_22396_c;
										float var31 = var28 * var28 + var29 * var29 + var30 * var30;
										if(var31 >= 576.0F) {
											EntityLiving var38;
											try {
												var38 = (EntityLiving)var12[var13].createEntity(var0);
											} catch (Exception var32) {
												var32.printStackTrace();
												return var3;
											}

											var38.setLocationAndAngles((double)var25, (double)var26, (double)var27, var0.rand.nextFloat() * 360.0F, 0.0F);
											if(var38.getCanSpawnHere()) {
												++var18;
												var0.entityJoinedWorld(var38);
												func_21204_a(var38, var0, var25, var26, var27);
												if(var18 >= var38.getMaxSpawnedInChunk()) {
													continue label109;
												}
											}

											var3 += var18;
										}
									}
								}
							}
						}
					}
				}
			}

			return var3;
		}
	}

	private static boolean func_21203_a(EnumCreatureType var0, World var1, int var2, int var3, int var4) {
		return var0.getCreatureMaterial() == Material.water ? var1.getBlockMaterial(var2, var3, var4).getIsLiquid() && !var1.isBlockOpaqueCube(var2, var3 + 1, var4) : var1.isBlockOpaqueCube(var2, var3 - 1, var4) && !var1.isBlockOpaqueCube(var2, var3, var4) && !var1.getBlockMaterial(var2, var3, var4).getIsLiquid() && !var1.isBlockOpaqueCube(var2, var3 + 1, var4);
	}

	private static void func_21204_a(EntityLiving var0, World var1, float var2, float var3, float var4) {
		if(var0 instanceof EntitySpider && var1.rand.nextInt(100) == 0) {
			EntitySkeleton var5 = new EntitySkeleton(var1);
			var5.setLocationAndAngles((double)var2, (double)var3, (double)var4, var0.rotationYaw, 0.0F);
			var1.entityJoinedWorld(var5);
			var5.mountEntity(var0);
		} else if(var0 instanceof EntitySheep) {
			((EntitySheep)var0).setFleeceColor(EntitySheep.func_21070_a(var1.rand));
		}

	}

	public static boolean func_22390_a(World var0, List var1) {
		boolean var2 = false;
		Pathfinder var3 = new Pathfinder(var0);
		Iterator var4 = var1.iterator();

		while(true) {
			EntityPlayer var5;
			EntitySupplier[] var6;
			do {
				do {
					if(!var4.hasNext()) {
						return var2;
					}

					var5 = (EntityPlayer)var4.next();
					var6 = field_22391_a;
				} while(var6 == null);
			} while(var6.length == 0);

			boolean var7 = false;

			for(int var8 = 0; var8 < 20 && !var7; ++var8) {
				int var9 = MathHelper.floor_double(var5.posX) + var0.rand.nextInt(32) - var0.rand.nextInt(32);
				int var10 = MathHelper.floor_double(var5.posZ) + var0.rand.nextInt(32) - var0.rand.nextInt(32);
				int var11 = MathHelper.floor_double(var5.posY) + var0.rand.nextInt(16) - var0.rand.nextInt(16);
				if(var11 < 1) {
					var11 = 1;
				} else if(var11 > 128) {
					var11 = 128;
				}

				int var12 = var0.rand.nextInt(var6.length);

				int var13;
				for(var13 = var11; var13 > 2 && !var0.isBlockOpaqueCube(var9, var13 - 1, var10); --var13) {
				}

				while(!func_21203_a(EnumCreatureType.monster, var0, var9, var13, var10) && var13 < var11 + 16 && var13 < 128) {
					++var13;
				}

				if(var13 < var11 + 16 && var13 < 128) {
					float var14 = (float)var9 + 0.5F;
					float var15 = (float)var13;
					float var16 = (float)var10 + 0.5F;

					EntityLiving var17;
					try {
						var17 = (EntityLiving)var6[var12].createEntity(var0);
					} catch (Exception var21) {
						var21.printStackTrace();
						return var2;
					}

					var17.setLocationAndAngles((double)var14, (double)var15, (double)var16, var0.rand.nextFloat() * 360.0F, 0.0F);
					if(var17.getCanSpawnHere()) {
						PathEntity var18 = var3.createEntityPathTo(var17, var5, 32.0F);
						if(var18 != null && var18.pathLength > 1) {
							PathPoint var19 = var18.func_22328_c();
							if(Math.abs((double)var19.xCoord - var5.posX) < 1.5D && Math.abs((double)var19.zCoord - var5.posZ) < 1.5D && Math.abs((double)var19.yCoord - var5.posY) < 1.5D) {
								ChunkCoordinates var20 = BlockBed.func_22028_g(var0, MathHelper.floor_double(var5.posX), MathHelper.floor_double(var5.posY), MathHelper.floor_double(var5.posZ), 1);
								var17.setLocationAndAngles((double)((float)var20.field_22395_a + 0.5F), (double)var20.field_22394_b, (double)((float)var20.field_22396_c + 0.5F), 0.0F, 0.0F);
								var0.entityJoinedWorld(var17);
								func_21204_a(var17, var0, (float)var20.field_22395_a + 0.5F, (float)var20.field_22394_b, (float)var20.field_22396_c + 0.5F);
								var5.func_22056_a(true, false);
								var17.func_22050_O();
								var2 = true;
								var7 = true;
							}
						}
					}
				}
			}
		}
	}
}
