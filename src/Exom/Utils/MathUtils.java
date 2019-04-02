package Exom.Utils;

import java.math.BigInteger;

/**
 * Contains methods used for mathematical calculations.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class MathUtils {

	/**
	 * Interpolates two values with a given alpha.
	 * @param x0 The first value.
	 * @param x1 The second value.
	 * @param alpha The alpha.
	 * 
	 * @return The interpolation result.
	 *
	 * @since 1.0
	 */
	public static float Interpolate(float x0, float x1, float alpha)
	{
		return x0 * (1 - alpha) + alpha * x1;
	}
	
	/**
	 * Checks whether or not a number is prime.
	 * 
	 * @param x The number to check.
	 * 
	 * @return Whether or not the number is prime.
	 *
	 * @since 1.0
	 */
	public static boolean IsPrime(long x) {
		if(x < 2)
			return false;
		if(x < 4 || x == 5)
			return true;
		if(x % 2 == 0 || x % 3 == 0 || x % 5 == 0)
			return false;
		long sqrt = (long)Math.sqrt(x);
		for(long u = 6; u <= sqrt; u += 6)
			if(x % (u - 1) == 0 || x % (u + 1) == 0)
				return false;
		return true;
	}
	
	/**
	 * Computes n-factorial.
	 * 
	 * @param n The number.
	 * 
	 * @return n-factorial.
	 *
	 * @since 1.0
	 */
	public static BigInteger Factorial(int n) {
		if(n < 0)
			throw new IllegalArgumentException("The number cannot be negative.");
		if(n < 2)
			return BigInteger.ONE;

		long sum = n;
		BigInteger result = BigInteger.valueOf(n);
		for (int i = n - 2; i > 1; i -= 2) {
			sum += i;
			result = result.multiply(BigInteger.valueOf(sum));
		}

		if (n % 2 != 0)
			result = result.multiply(BigInteger.valueOf(n / 2 + 1));

		return result;
	}
}
