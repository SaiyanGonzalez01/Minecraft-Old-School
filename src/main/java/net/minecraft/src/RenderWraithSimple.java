package net.minecraft.src;

import net.lax1dude.eaglercraft.EaglerAdapter;
import net.lax1dude.eaglercraft.TextureLocation;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class RenderWraithSimple extends RenderLiving {
	
	private static final TextureLocation wraithTexture = new TextureLocation("/mob/wraith.png");

	public RenderWraithSimple(ModelBase modelbase, float f, float f1) {
		super(modelbase, f * f1);
		scale = f1;
	}

	protected void preRenderScale(EntityWraithSimple entitywraithsimple, float f) {
		EaglerAdapter.glScalef(scale, scale, scale);
	}

	protected void preRenderCallback(EntityLiving entityliving, float f) {
		preRenderScale((EntityWraithSimple) entityliving, f);
	}

	private float scale;

	@Override
	protected boolean loadDownloadableImageTexture(String s, String s1) {
		wraithTexture.bindTexture();
		return true;
	}
}
