package net.lax1dude.eaglercraft.profile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import net.peyton.eagler.minecraft.TextureLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.NBTTagCompound;

public class Profile {
	
	public static class EaglerProfileSkin {
		public String name;
		public byte[] data;
		public boolean slim;
		public int glTex;
		public EaglerProfileSkin(String name, byte[] data, boolean slim, int glTex) {
			this.name = name;
			this.data = data;
			this.slim = slim;
			this.glTex = glTex;
		}
	}
	
	public static int presetSkinId;
	public static final int SKIN_DATA_SIZE = 64*32*4;
	public static ArrayList<EaglerProfileSkin> skins = new ArrayList();
	
	private static String username = "";
	
	public static byte[] getSelfSkinPacket() {
		return new byte[] { (byte)0, (byte)presetSkinId };
	}

	//not in og, custom way to get skins to work in singleplayer
	public static int getSelectedSkinTexture() {
		if (presetSkinId >= 0 && presetSkinId < defaultOptionsTextures.length) {
			return defaultOptionsTextures[presetSkinId].getTextureID();
		}

		int customSkinId = presetSkinId + skins.size();
		if (customSkinId >= 0 && customSkinId < skins.size()) {
			return skins.get(customSkinId).glTex;
		}

		return -1;
	}
	//
	
	private static class CachedSkin {
		
		protected final String username;
		protected UserPresetSkin skin;
		protected long age;
		
		protected CachedSkin(String username, UserPresetSkin skin) {
			this.username = username;
			this.skin = skin;
			this.age = System.currentTimeMillis();
		}
		
	}

	private static class UserPresetSkin {
		
		protected final int skinType;
		
		protected UserPresetSkin(int skin) {
			this.skinType = skin;
		}

		public int getSkin() {
			return skinType;
		}

		public int getTexture() {
			return (skinType >= defaultOptionsTextures.length || skinType < 0) ? -1 : defaultOptionsTextures[skinType].getTextureID();
		}

		public void free() {
		}
		
	}
	
	public static String getName() {
		return username;
	}
	
	public static void setName(String str) {
		username = str;
		Minecraft mc = Minecraft.getMinecraft();
		if(mc != null) {
			mc.session.username = str;
		}
	}
	
	public static void loadSkins() {
		readSkinData(GL11.EaglerAdapterImpl2.loadLocalStorage("SKINS"));
	}
	
	public static void readSkinData(byte[] data) {
		if (data == null) {
			return;
		}
		
		NBTTagCompound profile;
		try {
			profile = CompressedStreamTools.func_1138_a(new ByteArrayInputStream(data));
		}catch(IOException ex) {
			return;
		}
		
		if (profile == null || profile.hasNoTags()) {
			return;
		}
		
		if(!profile.hasKey("skin")) {
			return;
		}
		
		presetSkinId = profile.getInteger("skin");
	}
	
	public static void read() {
		read(GL11.EaglerAdapterImpl2.loadLocalStorage("P"));
	}
	
	public static void read(byte[] profileStorage) {
		if (profileStorage == null) {
			return;
		}
		
		NBTTagCompound profile;
		try {
			profile = CompressedStreamTools.func_1138_a(new ByteArrayInputStream(profileStorage));
		}catch(IOException ex) {
			return;
		}
		
		if (profile == null || profile.hasNoTags()) {
			return;
		}
		
		String loadUsername = profile.getString("username").trim();

		if(!loadUsername.isEmpty()) {
			username = loadUsername.replaceAll("[^A-Za-z0-9]", "_");
		}
	}
	
	private static byte[] write() {
		NBTTagCompound profile = new NBTTagCompound();
		profile.setString("username", username);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			CompressedStreamTools.writeGzippedCompoundToOutputStream(profile, baos);
		} catch (IOException e) {
			return null;
		}
		return baos.toByteArray();
	}
	
	public static void save() {
		byte[] b = write();
		if(b != null) {
			GL11.EaglerAdapterImpl2.saveLocalStorage("P", b);
		}
	}
	
	private static byte[] writeSkin() {
		NBTTagCompound profile = new NBTTagCompound();
		profile.setInteger("skin", presetSkinId);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			CompressedStreamTools.writeGzippedCompoundToOutputStream(profile, baos);
		} catch (IOException e) {
			return null;
		}
		return baos.toByteArray();
	}
	
	public static void saveSkin() {
		byte[] b = writeSkin();
		if(b != null) {
			GL11.EaglerAdapterImpl2.saveLocalStorage("SKINS", b);
		}
	}
	
	static {
		read();
		loadSkins();
	}
	
	public static final TextureLocation[] defaultOptionsTextures = new TextureLocation[] {
			new TextureLocation("/skins/01.default_steve.png"),
			new TextureLocation("/skins/02.tennis_steve.png"),
			new TextureLocation("/skins/03.tuxedo_steve.png"),
			new TextureLocation("/skins/04.athlete_steve.png"),
			new TextureLocation("/skins/05.cyclist_steve.png"),
			new TextureLocation("/skins/06.boxer_steve.png"),
			new TextureLocation("/skins/07.prisoner_steve.png"),
			new TextureLocation("/skins/08.scottish_steve.png"),
			new TextureLocation("/skins/09.dev_steve.png"),
			new TextureLocation("/skins/10.plastic_steve.png"),
			new TextureLocation("/skins/11.modern_steve.png"),
			new TextureLocation("/skins/12.mossy_steve.png"),
			new TextureLocation("/skins/13.glasses_steve.png"),
			new TextureLocation("/skins/14.festive_steve.png"),
			new TextureLocation("/skins/15.creeper_steve.png"),
			new TextureLocation("/skins/16.ext05_steve.png"),
			new TextureLocation("/skins/17.notch.png"),
			new TextureLocation("/skins/18.herobrine.png"),
			new TextureLocation("/skins/19.ez.png"),
			new TextureLocation("/skins/20.testvan.png"),
			new TextureLocation("/skins/21.mezo.png"),
			new TextureLocation("/skins/22.camo_mezo.png")
	};

}