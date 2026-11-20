package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class Hammerable {
	public static final int BLOCK = 0;
	public static final int STAIR = 1;
	public static final int LAST_STAIR = 4;
	public static final int SLAB = 5;
	public static final int DOUBLE_SLAB = 6;

	public static final int MODEL_BLOCK = 0;
	public static final int MODEL_STAIRS = 1;
	public static final int MODEL_SLAB = 2;
	public static final int MODEL_DOUBLE_SLAB = 3;

	private static final int[] NORMAL_MODELS = new int[] { MODEL_BLOCK, MODEL_STAIRS, MODEL_SLAB };
	private static final int[] POLISHED_STONE_MODELS = new int[] { MODEL_BLOCK, MODEL_STAIRS, MODEL_SLAB, MODEL_DOUBLE_SLAB };
	private static final Hammerable instance = new Hammerable();
	private final List<Block> blocks = new ArrayList<Block>();

	private Hammerable() {
		this.addHammerable(Block.planks);
		this.addHammerable(Block.cobblestone);
		this.addHammerable(Block.stone);
		this.addHammerable(Block.sandStone);
		this.addHammerable(Block.cobblestoneMossy);
		this.addHammerable(Block.brick);
		this.addHammerable(Block.stoneBrick);
		this.addHammerable(Block.bloodStone);
		this.addHammerable(Block.stonePolished);
	}

	public static Hammerable getInstance() {
		return instance;
	}

	public void addHammerable(Block block) {
		if (block != null && !this.blocks.contains(block)) {
			this.blocks.add(block);
		}
	}

	public boolean isHammerable(Block block) {
		return this.blocks.contains(block);
	}

	public int getModel(Block block, int metadata) {
		if (!this.isHammerable(block)) {
			return MODEL_BLOCK;
		}

		int[] models = this.getModels(block);
		if (metadata >= STAIR && metadata <= LAST_STAIR) {
			return models[1];
		}
		if (metadata == SLAB) {
			return models[2];
		}
		if (metadata == DOUBLE_SLAB && models.length > 3) {
			return models[3];
		}
		return models[0];
	}

	public int getStairMetadata(int orientation) {
		return STAIR + (orientation & 3);
	}

	public int getNextMetadata(Block block, int metadata) {
		int model = this.getModel(block, metadata);
		if (model == MODEL_BLOCK) {
			return this.getStairMetadata(0);
		}
		if (model == MODEL_STAIRS) {
			return SLAB;
		}
		if (model == MODEL_SLAB) {
			return block == Block.stonePolished ? DOUBLE_SLAB : BLOCK;
		}
		return BLOCK;
	}

	public boolean isNonFullShape(Block block, int metadata) {
		int model = this.getModel(block, metadata);
		return model == MODEL_STAIRS || model == MODEL_SLAB;
	}

	public boolean isOpaque(Block block, int metadata) {
		int model = this.getModel(block, metadata);
		return model != MODEL_STAIRS && model != MODEL_SLAB && block != null && block.isOpaqueCube();
	}

	private int[] getModels(Block block) {
		return block == Block.stonePolished ? POLISHED_STONE_MODELS : NORMAL_MODELS;
	}
}
