package net.minecraft.src;

import java.util.Iterator;
import java.util.LinkedList;


public abstract class StructureStart {
	protected LinkedList components = new LinkedList();
	protected StructureBoundingBox boundingBox;

	public StructureBoundingBox getBoundingBox() {
		return this.boundingBox;
	}

	public LinkedList getComponents() {
		return this.components;
	}

	public void generateStructure(World var1, Random var2, StructureBoundingBox var3) {
		Iterator var4 = this.components.iterator();

		while(var4.hasNext()) {
			StructureComponent var5 = (StructureComponent)var4.next();
			if(var5.getBoundingBox().intersectsWith(var3) && !var5.addComponentParts(var1, var2, var3)) {
				var4.remove();
			}
		}

	}

	protected void updateBoundingBox() {
		this.boundingBox = StructureBoundingBox.getNewBoundingBox();
		Iterator var1 = this.components.iterator();

		while(var1.hasNext()) {
			StructureComponent var2 = (StructureComponent)var1.next();
			this.boundingBox.expandTo(var2.getBoundingBox());
		}

	}

	protected void markAvailableHeight(World var1, Random var2, int var3) {
		int var4 = 63 - var3;
		int var5 = this.boundingBox.getYSize() + 1;
		if(var5 < var4) {
			var5 += var2.nextInt(var4 - var5);
		}

		int var6 = var5 - this.boundingBox.maxY;
		this.boundingBox.offset(0, var6, 0);
		Iterator var7 = this.components.iterator();

		while(var7.hasNext()) {
			StructureComponent var8 = (StructureComponent)var7.next();
			var8.getBoundingBox().offset(0, var6, 0);
		}

	}

	protected void setRandomHeight(World var1, Random var2, int var3, int var4) {
		int var5 = var4 - var3 + 1 - this.boundingBox.getYSize();
		boolean var6 = true;
		int var10;
		if(var5 > 1) {
			var10 = var3 + var2.nextInt(var5);
		} else {
			var10 = var3;
		}

		int var7 = var10 - this.boundingBox.minY;
		this.boundingBox.offset(0, var7, 0);
		Iterator var8 = this.components.iterator();

		while(var8.hasNext()) {
			StructureComponent var9 = (StructureComponent)var8.next();
			var9.getBoundingBox().offset(0, var7, 0);
		}

	}

	public boolean isSizeableStructure() {
		return true;
	}
}
