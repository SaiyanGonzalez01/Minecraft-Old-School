package net.minecraft.src;

public class ItemHammer extends Item {
	public ItemHammer(int var1, EnumToolMaterial var2) {
		super(var1);
		this.maxStackSize = 1;
		this.maxDamage = var2.getMaxUses();
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
		int blockId = world.getBlockId(x, y, z);
		Block block = Block.blocksList[blockId];
		Hammerable hammerable = Hammerable.getInstance();
		if (!hammerable.isHammerable(block)) {
			return false;
		}

		int metadata = world.getBlockMetadata(x, y, z);
		int nextMetadata = hammerable.getNextMetadata(block, metadata);
		if (hammerable.getModel(block, metadata) == Hammerable.MODEL_BLOCK) {
			int facing = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			int orientation = facing == 0 ? 2 : (facing == 1 ? 1 : (facing == 2 ? 3 : 0));
			nextMetadata = hammerable.getStairMetadata(orientation);
		}

		world.playSoundAtEntity(player, "random.hammer", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		if (world.multiplayerWorld) {
			return true;
		}

		if (world.setBlockAndMetadataWithNotify(x, y, z, blockId, nextMetadata)) {
			stack.damageItem(1);
		}
		return true;
	}
}
