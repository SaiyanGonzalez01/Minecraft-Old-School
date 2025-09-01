package net.minecraft.src;

public enum EnumArt {
	Kebab("Kebab", 16, 16, 0, 0),
	Aztec("Aztec", 16, 16, 16, 0),
	Alban("Alban", 16, 16, 32, 0),
	Aztec2("Aztec2", 16, 16, 48, 0),
	Bomb("Bomb", 16, 16, 64, 0),
	Plant("Plant", 16, 16, 80, 0),
	Wasteland("Wasteland", 16, 16, 96, 0),
	Hero("Hero", 16, 16, 112, 0),
	Meditative("Meditative", 16, 16, 128, 0),
	Hs("Hs", 16, 16, 144, 0),
	Distant("Distant", 16, 16, 32, 16),
	Militia("Militia", 16, 16, 48, 16),
	Then("Then", 16, 16, 96, 16),
	Dust("Dust", 16, 16, 112, 16),
	Pool("Pool", 32, 16, 0, 32),
	Courbet("Courbet", 32, 16, 32, 32),
	Sea("Sea", 32, 16, 64, 32),
	Sunset("Sunset", 32, 16, 96, 32),
	Creebet("Creebet", 32, 16, 128, 32),
	Abstract("Abstract", 32, 16, 0, 48),
	Sunset2("Sunset2" 32, 16, 0, 16),
	Chateau("Chateau" 32, 16, 64, 16),
	Italy("Italy" 32, 16, 128, 16),
	Master("Master" 32, 16, 160, 160),
	Wanderer("Wanderer", 16, 32, 0, 64),
	Graham("Graham", 16, 32, 16, 64),
	Match("Match", 32, 32, 0, 128),
	Bust("Bust", 32, 32, 32, 128),
	Stage("Stage", 32, 32, 64, 128),
	Void("Void", 32, 32, 96, 128),
	SkullAndRoses("SkullAndRoses", 32, 32, 128, 128),
	Fighters("Fighters", 64, 32, 0, 96),
	Lowmist("Lowmist",64, 32, 64, 96),
	Pointer("Pointer", 64, 64, 0, 192),
	Pigscene("Pigscene", 64, 64, 64, 192),
	BurningSkull("BurningSkull", 64, 64, 128, 192),
	Skeleton("Skeleton", 64, 48, 192, 64),
	DonkeyKong("DonkeyKong", 64, 48, 192, 112),
	Fern("Fern", 48, 48, 32, 48),
	Tides("Tides", 48, 48, 80, 48);

	public static final int maxArtTitleLength = "SkullAndRoses".length();
	public final String title;
	public final int sizeX;
	public final int sizeY;
	public final int offsetX;
	public final int offsetY;

	private EnumArt(String var3, int var4, int var5, int var6, int var7) {
		this.title = var3;
		this.sizeX = var4;
		this.sizeY = var5;
		this.offsetX = var6;
		this.offsetY = var7;
	}
}
