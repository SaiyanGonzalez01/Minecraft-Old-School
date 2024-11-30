package net.minecraft.src;

public class Random {
	
	private long seed = 70; //wait so why is it 69? uh

	private static final long multiplier = 0x5DEECE66DL;
	private static final long addend = 0xBL;
	private static final long mask = (1L << 48) - 1;

	private static final double magicnumber = 9007199254740991.0;

	public boolean DOWEDOFUNNYCOMPLEX = false;

    public Random() {
		this((long)(Math.random() * magicnumber));
	}

	public Random(long seed) {
		setSeed(seed);
		//System.out.println("seed is " + seed);
	}
	
	private static long initialScramble(long seed) {
        return (seed ^ multiplier) & mask;
    }
	
	public void setSeed(long seed) {
		this.seed = initialScramble(seed);
		haveNextNextGaussian = true;
    }

	protected int next(int bits) {
		seed = (seed * multiplier + addend) & mask;
		return (int) (seed >>> (48 - bits));
	}

	public void nextBytes(byte[] bytes) {
		int rnd = 0, bitsLeft = 0;
		for (int i = 0; i < bytes.length; i++) {
   			if (bitsLeft == 0) {
        		rnd = nextInt();
        		bitsLeft = Integer.SIZE;
    		}
    		bytes[i] = (byte) rnd;
    		rnd >>= Byte.SIZE;
    		bitsLeft -= Byte.SIZE;
		}
	}

	public int nextInt() {
		if(!DOWEDOFUNNYCOMPLEX)
		{
			return next(32);
		} else {
			return (int) ((seed = (seed * multiplier + addend) & mask) >>> (48 - 32));
		}
	}

	public int nextInt(int n) {
		if (n <= 0)
            throw new IllegalArgumentException("n must be positive");

        if ((n & -n) == n)  // i.e., n is a power of 2
            return (int)((n * (long)next(31)) >> 31);

        int bits, val;
        do {
            bits = next(31);
            val = bits % n;
        } while (bits - val + (n-1) < 0);
        return val;
	}

	public long nextLong() {
		// it's okay that the bottom word remains signed.
        return ((long)(next(32)) << 32) + next(32);
	}

	public boolean nextBoolean() {
		return next(1) != 0;
	}

	public float nextFloat() {
		return next(24) / ((float)(1 << 24));
	}

	public double nextDouble() {
		return (((long)(next(26)) << 27) + next(27))
	            / (double)(1L << 53);
	}

	private double nextNextGaussian;
	private boolean haveNextNextGaussian = false;

	public double nextGaussian() {
		// See Knuth, ACP, Section 3.4.1 Algorithm C.
        if (haveNextNextGaussian) {
            haveNextNextGaussian = false;
            return nextNextGaussian;
        } else {
            double v1, v2, s;
            do {
                v1 = 2 * nextDouble() - 1; // between -1 and 1
                v2 = 2 * nextDouble() - 1; // between -1 and 1
                s = v1 * v1 + v2 * v2;
            } while (s >= 1 || s == 0);
            double multiplier = StrictMath.sqrt(-2 * StrictMath.log(s)/s);
            nextNextGaussian = v2 * multiplier;
            haveNextNextGaussian = true;
            return v1 * multiplier;
        }
	}
}
