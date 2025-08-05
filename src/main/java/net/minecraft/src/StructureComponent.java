package net.minecraft.src;

import java.util.Iterator;
import java.util.List;


public abstract class StructureComponent {
	protected StructureBoundingBox boundingBox;
	protected int coordBaseMode;
	protected int componentType;

	protected StructureComponent(int var1) {
		this.componentType = var1;
		this.coordBaseMode = -1;
	}

	public void buildComponent(StructureComponent var1, List var2, Random var3) {
	}

	public abstract boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3);

	public StructureBoundingBox getBoundingBox() {
		return this.boundingBox;
	}

	public int getComponentType() {
		return this.componentType;
	}

	public static StructureComponent findIntersecting(List var0, StructureBoundingBox var1) {
		Iterator var2 = var0.iterator();

		StructureComponent var3;
		do {
			if(!var2.hasNext()) {
				return null;
			}

			var3 = (StructureComponent)var2.next();
		} while(var3.getBoundingBox() == null || !var3.getBoundingBox().intersectsWith(var1));

		return var3;
	}

	public ChunkPosition getCenter() {
		return new ChunkPosition(this.boundingBox.getCenterX(), this.boundingBox.getCenterY(), this.boundingBox.getCenterZ());
	}

	protected boolean isLiquidInStructureBoundingBox(World var1, StructureBoundingBox var2) {
		int var3 = Math.max(this.boundingBox.minX - 1, var2.minX);
		int var4 = Math.max(this.boundingBox.minY - 1, var2.minY);
		int var5 = Math.max(this.boundingBox.minZ - 1, var2.minZ);
		int var6 = Math.min(this.boundingBox.maxX + 1, var2.maxX);
		int var7 = Math.min(this.boundingBox.maxY + 1, var2.maxY);
		int var8 = Math.min(this.boundingBox.maxZ + 1, var2.maxZ);

		int var9;
		int var10;
		int var11;
		for(var9 = var3; var9 <= var6; ++var9) {
			for(var10 = var5; var10 <= var8; ++var10) {
				var11 = var1.getBlockId(var9, var4, var10);
				if(var11 > 0 && Block.blocksList[var11].blockMaterial.isLiquid()) {
					return true;
				}

				var11 = var1.getBlockId(var9, var7, var10);
				if(var11 > 0 && Block.blocksList[var11].blockMaterial.isLiquid()) {
					return true;
				}
			}
		}

		for(var9 = var3; var9 <= var6; ++var9) {
			for(var10 = var4; var10 <= var7; ++var10) {
				var11 = var1.getBlockId(var9, var10, var5);
				if(var11 > 0 && Block.blocksList[var11].blockMaterial.isLiquid()) {
					return true;
				}

				var11 = var1.getBlockId(var9, var10, var8);
				if(var11 > 0 && Block.blocksList[var11].blockMaterial.isLiquid()) {
					return true;
				}
			}
		}

		for(var9 = var5; var9 <= var8; ++var9) {
			for(var10 = var4; var10 <= var7; ++var10) {
				var11 = var1.getBlockId(var3, var10, var9);
				if(var11 > 0 && Block.blocksList[var11].blockMaterial.isLiquid()) {
					return true;
				}

				var11 = var1.getBlockId(var6, var10, var9);
				if(var11 > 0 && Block.blocksList[var11].blockMaterial.isLiquid()) {
					return true;
				}
			}
		}

		return false;
	}

	protected int getXWithOffset(int var1, int var2) {
		switch(this.coordBaseMode) {
		case 0:
		case 2:
			return this.boundingBox.minX + var1;
		case 1:
			return this.boundingBox.maxX - var2;
		case 3:
			return this.boundingBox.minX + var2;
		default:
			return var1;
		}
	}

	protected int getYWithOffset(int var1) {
		return this.coordBaseMode == -1 ? var1 : var1 + this.boundingBox.minY;
	}

	protected int getZWithOffset(int var1, int var2) {
		switch(this.coordBaseMode) {
		case 0:
			return this.boundingBox.minZ + var2;
		case 1:
		case 3:
			return this.boundingBox.minZ + var1;
		case 2:
			return this.boundingBox.maxZ - var2;
		default:
			return var2;
		}
	}

	protected int getMetadataWithOffset(int var1, int var2) {
		if(var1 == Block.rail.blockID) {
			if(this.coordBaseMode == 1 || this.coordBaseMode == 3) {
				if(var2 == 1) {
					return 0;
				}

				return 1;
			}
		} else if(var1 != Block.doorWood.blockID && var1 != Block.doorSteel.blockID) {
			if(var1 != Block.stairCompactCobblestone.blockID && var1 != Block.stairCompactPlanks.blockID && var1 != Block.stairsNetherBrick.blockID && var1 != Block.stairsStoneBrickSmooth.blockID) {
				if(var1 == Block.ladder.blockID) {
					if(this.coordBaseMode == 0) {
						if(var2 == 2) {
							return 3;
						}

						if(var2 == 3) {
							return 2;
						}
					} else if(this.coordBaseMode == 1) {
						if(var2 == 2) {
							return 4;
						}

						if(var2 == 3) {
							return 5;
						}

						if(var2 == 4) {
							return 2;
						}

						if(var2 == 5) {
							return 3;
						}
					} else if(this.coordBaseMode == 3) {
						if(var2 == 2) {
							return 5;
						}

						if(var2 == 3) {
							return 4;
						}

						if(var2 == 4) {
							return 2;
						}

						if(var2 == 5) {
							return 3;
						}
					}
				} else if(var1 == Block.button.blockID) {
					if(this.coordBaseMode == 0) {
						if(var2 == 3) {
							return 4;
						}

						if(var2 == 4) {
							return 3;
						}
					} else if(this.coordBaseMode == 1) {
						if(var2 == 3) {
							return 1;
						}

						if(var2 == 4) {
							return 2;
						}

						if(var2 == 2) {
							return 3;
						}

						if(var2 == 1) {
							return 4;
						}
					} else if(this.coordBaseMode == 3) {
						if(var2 == 3) {
							return 2;
						}

						if(var2 == 4) {
							return 1;
						}

						if(var2 == 2) {
							return 3;
						}

						if(var2 == 1) {
							return 4;
						}
					}
				}
			} else if(this.coordBaseMode == 0) {
				if(var2 == 2) {
					return 3;
				}

				if(var2 == 3) {
					return 2;
				}
			} else if(this.coordBaseMode == 1) {
				if(var2 == 0) {
					return 2;
				}

				if(var2 == 1) {
					return 3;
				}

				if(var2 == 2) {
					return 0;
				}

				if(var2 == 3) {
					return 1;
				}
			} else if(this.coordBaseMode == 3) {
				if(var2 == 0) {
					return 2;
				}

				if(var2 == 1) {
					return 3;
				}

				if(var2 == 2) {
					return 1;
				}

				if(var2 == 3) {
					return 0;
				}
			}
		} else if(this.coordBaseMode == 0) {
			if(var2 == 0) {
				return 2;
			}

			if(var2 == 2) {
				return 0;
			}
		} else {
			if(this.coordBaseMode == 1) {
				return var2 + 1 & 3;
			}

			if(this.coordBaseMode == 3) {
				return var2 + 3 & 3;
			}
		}

		return var2;
	}

	protected void placeBlockAtCurrentPosition(World var1, int var2, int var3, int var4, int var5, int var6, StructureBoundingBox var7) {
		int var8 = this.getXWithOffset(var4, var6);
		int var9 = this.getYWithOffset(var5);
		int var10 = this.getZWithOffset(var4, var6);
		if(var7.isVecInside(var8, var9, var10)) {
			var1.setBlockAndMetadata(var8, var9, var10, var2, var3);
		}
	}

	protected int getBlockIdAtCurrentPosition(World var1, int var2, int var3, int var4, StructureBoundingBox var5) {
		int var6 = this.getXWithOffset(var2, var4);
		int var7 = this.getYWithOffset(var3);
		int var8 = this.getZWithOffset(var2, var4);
		return !var5.isVecInside(var6, var7, var8) ? 0 : var1.getBlockId(var6, var7, var8);
	}

	protected void fillWithBlocks(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, boolean var11) {
		for(int var12 = var4; var12 <= var7; ++var12) {
			for(int var13 = var3; var13 <= var6; ++var13) {
				for(int var14 = var5; var14 <= var8; ++var14) {
					if(!var11 || this.getBlockIdAtCurrentPosition(var1, var13, var12, var14, var2) != 0) {
						if(var12 != var4 && var12 != var7 && var13 != var3 && var13 != var6 && var14 != var5 && var14 != var8) {
							this.placeBlockAtCurrentPosition(var1, var10, 0, var13, var12, var14, var2);
						} else {
							this.placeBlockAtCurrentPosition(var1, var9, 0, var13, var12, var14, var2);
						}
					}
				}
			}
		}

	}

	protected void fillWithRandomizedBlocks(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9, Random var10, StructurePieceBlockSelector var11) {
		for(int var12 = var4; var12 <= var7; ++var12) {
			for(int var13 = var3; var13 <= var6; ++var13) {
				for(int var14 = var5; var14 <= var8; ++var14) {
					if(!var9 || this.getBlockIdAtCurrentPosition(var1, var13, var12, var14, var2) != 0) {
						var11.selectBlocks(var10, var13, var12, var14, var12 == var4 || var12 == var7 || var13 == var3 || var13 == var6 || var14 == var5 || var14 == var8);
						this.placeBlockAtCurrentPosition(var1, var11.getSelectedBlockId(), var11.getSelectedBlockMetaData(), var13, var12, var14, var2);
					}
				}
			}
		}

	}

	protected void randomlyFillWithBlocks(World var1, StructureBoundingBox var2, Random var3, float var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, boolean var13) {
		for(int var14 = var6; var14 <= var9; ++var14) {
			for(int var15 = var5; var15 <= var8; ++var15) {
				for(int var16 = var7; var16 <= var10; ++var16) {
					if(var3.nextFloat() <= var4 && (!var13 || this.getBlockIdAtCurrentPosition(var1, var15, var14, var16, var2) != 0)) {
						if(var14 != var6 && var14 != var9 && var15 != var5 && var15 != var8 && var16 != var7 && var16 != var10) {
							this.placeBlockAtCurrentPosition(var1, var12, 0, var15, var14, var16, var2);
						} else {
							this.placeBlockAtCurrentPosition(var1, var11, 0, var15, var14, var16, var2);
						}
					}
				}
			}
		}

	}

	protected void randomlyPlaceBlock(World var1, StructureBoundingBox var2, Random var3, float var4, int var5, int var6, int var7, int var8, int var9) {
		if(var3.nextFloat() < var4) {
			this.placeBlockAtCurrentPosition(var1, var8, var9, var5, var6, var7, var2);
		}

	}

	protected void randomlyRareFillWithBlocks(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10) {
		float var11 = (float)(var6 - var3 + 1);
		float var12 = (float)(var7 - var4 + 1);
		float var13 = (float)(var8 - var5 + 1);
		float var14 = (float)var3 + var11 / 2.0F;
		float var15 = (float)var5 + var13 / 2.0F;

		for(int var16 = var4; var16 <= var7; ++var16) {
			float var17 = (float)(var16 - var4) / var12;

			for(int var18 = var3; var18 <= var6; ++var18) {
				float var19 = ((float)var18 - var14) / (var11 * 0.5F);

				for(int var20 = var5; var20 <= var8; ++var20) {
					float var21 = ((float)var20 - var15) / (var13 * 0.5F);
					if(!var10 || this.getBlockIdAtCurrentPosition(var1, var18, var16, var20, var2) != 0) {
						float var22 = var19 * var19 + var17 * var17 + var21 * var21;
						if(var22 <= 1.05F) {
							this.placeBlockAtCurrentPosition(var1, var9, 0, var18, var16, var20, var2);
						}
					}
				}
			}
		}

	}

	protected void clearCurrentPositionBlocksUpwards(World var1, int var2, int var3, int var4, StructureBoundingBox var5) {
		int var6 = this.getXWithOffset(var2, var4);
		int var7 = this.getYWithOffset(var3);
		int var8 = this.getZWithOffset(var2, var4);
		if(var5.isVecInside(var6, var7, var8)) {
			while(!var1.isAirBlock(var6, var7, var8) && var7 < 255) {
				var1.setBlockAndMetadata(var6, var7, var8, 0, 0);
				++var7;
			}

		}
	}

	protected void fillCurrentPositionBlocksDownwards(World var1, int var2, int var3, int var4, int var5, int var6, StructureBoundingBox var7) {
		int var8 = this.getXWithOffset(var4, var6);
		int var9 = this.getYWithOffset(var5);
		int var10 = this.getZWithOffset(var4, var6);
		if(var7.isVecInside(var8, var9, var10)) {
			while((var1.isAirBlock(var8, var9, var10) || var1.getBlockMaterial(var8, var9, var10).isLiquid()) && var9 > 1) {
				var1.setBlockAndMetadata(var8, var9, var10, var2, var3);
				--var9;
			}

		}
	}

	protected void createTreasureChestAtCurrentPosition(World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, StructurePieceTreasure[] var7, int var8) {
		int var9 = this.getXWithOffset(var4, var6);
		int var10 = this.getYWithOffset(var5);
		int var11 = this.getZWithOffset(var4, var6);
		if(var2.isVecInside(var9, var10, var11) && var1.getBlockId(var9, var10, var11) != Block.chest.blockID) {
			var1.setBlockWithNotify(var9, var10, var11, Block.chest.blockID);
			TileEntityChest var12 = (TileEntityChest)var1.getBlockTileEntity(var9, var10, var11);
			if(var12 != null) {
				fillTreasureChestWithLoot(var3, var7, var12, var8);
			}
		}

	}

	private static void fillTreasureChestWithLoot(Random var0, StructurePieceTreasure[] var1, TileEntityChest var2, int var3) {
		for(int var4 = 0; var4 < var3; ++var4) {
			StructurePieceTreasure var5 = (StructurePieceTreasure)WeightedRandom.getRandomItem(var0, (WeightedRandomChoice[])var1);
			int var6 = var5.minItemStack + var0.nextInt(var5.maxItemStack - var5.minItemStack + 1);
			if(Item.itemsList[var5.itemID].getItemStackLimit() >= var6) {
				var2.setInventorySlotContents(var0.nextInt(var2.getSizeInventory()), new ItemStack(var5.itemID, var6, var5.itemMetadata));
			} else {
				for(int var7 = 0; var7 < var6; ++var7) {
					var2.setInventorySlotContents(var0.nextInt(var2.getSizeInventory()), new ItemStack(var5.itemID, 1, var5.itemMetadata));
				}
			}
		}

	}

	protected void placeDoorAtCurrentPosition(World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, int var7) {
		int var8 = this.getXWithOffset(var4, var6);
		int var9 = this.getYWithOffset(var5);
		int var10 = this.getZWithOffset(var4, var6);
		if(var2.isVecInside(var8, var9, var10)) {
			ItemDoor.placeDoorBlock(var1, var8, var9, var10, var7, Block.doorWood);
		}

	}
}
