package util;

public class Mth {
	private static final int BIG_ENOUGH_INT = 1024;
	private static final float BIG_ENOUGH_FLOAT = 1024.0F;
	public static final float PI = 3.1415927F;
	public static final float RAD_TO_GRAD = 0.017453292F;
	public static final float DEGRAD = 0.017453292F;
	public static final float RADDEG = 57.295776F;
	private static float[] sin = new float[65536];
	private static final float sinScale = 10430.378F;

	public static final float sin(float var0) {
		return sin[(int)(var0 * 10430.378F) & '\uffff'];
	}

	public static final float cos(float var0) {
		return sin[(int)(var0 * 10430.378F + 16384.0F) & '\uffff'];
	}

	public static final float sqrt(float var0) {
		return (float)Math.sqrt((double)var0);
	}

	public static final float sqrt(double var0) {
		return (float)Math.sqrt(var0);
	}

	public static int floor(float var0) {
		int var1 = (int)var0;
		return var0 < (float)var1 ? var1 - 1 : var1;
	}

	public static int fastFloor(double var0) {
		return (int)(var0 + 1024.0D) - 1024;
	}

	public static int floor(double var0) {
		int var2 = (int)var0;
		return var0 < (double)var2 ? var2 - 1 : var2;
	}

	public static int absFloor(double var0) {
		return (int)(var0 >= 0.0D ? var0 : -var0 + 1.0D);
	}

	public static float abs(float var0) {
		return var0 >= 0.0F ? var0 : -var0;
	}

	public static int ceil(float var0) {
		int var1 = (int)var0;
		return var0 > (float)var1 ? var1 + 1 : var1;
	}

	public static void main(String[] var0) {
		for(int var1 = -64; var1 <= 64; ++var1) {
			System.out.println(var1 + " -> " + intFloorDiv(var1, 32));
		}

	}

	public static double asbMax(double var0, double var2) {
		if(var0 < 0.0D) {
			var0 = -var0;
		}

		if(var2 < 0.0D) {
			var2 = -var2;
		}

		return var0 > var2 ? var0 : var2;
	}

	public static int intFloorDiv(int var0, int var1) {
		return var0 < 0 ? -((-var0 - 1) / var1) - 1 : var0 / var1;
	}

	static {
		for(int var0 = 0; var0 < 65536; ++var0) {
			sin[var0] = (float)Math.sin((double)var0 * Math.PI * 2.0D / 65536.0D);
		}

	}
}
