/**
 * Exom-Legacy (https://github.com/UnexomWid/Exom-Legacy)
 *
 * This project is licensed under the MIT license.
 * Copyright (c) 2017-2019 UnexomWid (https://uw.exom.dev)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
