package net.minecraft.src;

class EnumOptionsMappingHelper {
	static final int[] enumOptionsMappingHelperArray = new int[EnumOptions.values().length];

	static {
		try {
			enumOptionsMappingHelperArray[EnumOptions.INVERT_MOUSE.ordinal()] = 1;
		} catch (NoSuchFieldError var5) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.VIEW_BOBBING.ordinal()] = 2;
		} catch (NoSuchFieldError var4) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.ANAGLYPH.ordinal()] = 3;
		} catch (NoSuchFieldError var3) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.ADVANCED_OPENGL.ordinal()] = 4;
		} catch (NoSuchFieldError var2) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.AMBIENT_OCCLUSION.ordinal()] = 5;
		} catch (NoSuchFieldError var1) {
		}
		
		try {
			enumOptionsMappingHelperArray[EnumOptions.PARTICLES.ordinal()] = 6;
		} catch (NoSuchFieldError var1) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.DO_PRINTS.ordinal()] = 7;
		} catch (NoSuchFieldError var1) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.CLOUDS.ordinal()] = 8;
		} catch (NoSuchFieldError var1) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.ENTITYSHADOWS.ordinal()] = 9;
		} catch (NoSuchFieldError var1) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.AUTOJUMP.ordinal()] = 10;
		} catch (NoSuchFieldError var1) {
		}
		
		try {
			enumOptionsMappingHelperArray[EnumOptions.RAIN.ordinal()] = 11;
		} catch (NoSuchFieldError var1) {
		}
		
		try {
			enumOptionsMappingHelperArray[EnumOptions.LIGHTNINGFLASH.ordinal()] = 12;
		} catch (NoSuchFieldError var1) {
		}
		
		try {
			enumOptionsMappingHelperArray[EnumOptions.RDBG.ordinal()] = 13;
		} catch (NoSuchFieldError var1) {
		}

	}
}
