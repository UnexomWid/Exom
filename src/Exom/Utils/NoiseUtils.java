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

import java.util.Random;

import Exom.Utils.MathUtils;

/**
 * Contains methods used for generating matrices with noise algorithms.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class NoiseUtils {

	/**
	 * Generates a white noise matrix.
	 * 
	 * @param width The width of the matrix.
	 * @param height The height of the matrix.
	 * 
	 * @return The white noise matrix.
	 *
	 * @since 1.0
	 */
	public static float[][] WhiteNoise(int width,int height) {
		Random random = new Random();
		float[][] noise = new float[width][height];
		 
		for (int i = 0; i < width; i++) {
		    for (int j = 0; j < height; j++) {
		        noise[i][j] = (float)random.nextDouble() % 1;
		    }
		}
		 
		return noise;
	}
	/**
	 * Generates a white noise matrix.
	 * 
	 * @param width The width of the matrix.
	 * @param height The height of the matrix.
	 * @param rand The random number generator.
	 * 
	 * @return The white noise matrix.
	 *
	 * @since 1.0
	 */
	public static float[][] WhiteNoise(int width,int height, Random rand) {
		float[][] noise = new float[width][height];
		 
		for (int i = 0; i < width; i++) {
		    for (int j = 0; j < height; j++) {
		        noise[i][j] = (float)rand.nextDouble() % 1;
		    }
		}
		 
		return noise;
	}
	
	/**
	 * Generates a Perlin noise matrix.
	 * 
	 * @param width The width of the matrix.
	 * @param height The height of the matrix.
	 * @param octave The smoothness value.
	 * 
	 * @return The Perlin noise matrix.
	 */
	public static float[][] PerlinNoise(int width, int height, int octave) {
		float[][] whiteNoise = WhiteNoise(width,height);
		 
	    float[][] smoothNoise = new float[width][height];
		 
	    int samplePeriod = 1 << octave;
		float sampleFrequency = 1.0f / samplePeriod;
		 
		for (int i = 0; i < width; i++) {
		   int sample_i0 = (i / samplePeriod) * samplePeriod;
		   int sample_i1 = (sample_i0 + samplePeriod) % width;
		   float horizontal_blend = (i - sample_i0) * sampleFrequency;
		 
		   for (int j = 0; j < height; j++) {
		       int sample_j0 = (j / samplePeriod) * samplePeriod;
		       int sample_j1 = (sample_j0 + samplePeriod) % height;
		       float vertical_blend = (j - sample_j0) * sampleFrequency;
		 
		       float top = MathUtils.Interpolate(whiteNoise[sample_i0][sample_j0],
		           whiteNoise[sample_i1][sample_j0], horizontal_blend);
		 
		       float bottom = MathUtils.Interpolate(whiteNoise[sample_i0][sample_j1],
		           whiteNoise[sample_i1][sample_j1], horizontal_blend);
		 
		         smoothNoise[i][j] = MathUtils.Interpolate(top, bottom, vertical_blend);
		    }
		 }
		 
		 return smoothNoise;
	}
	/**
	 * Generates a Perlin noise matrix.
	 * 
	 * @param width The width of the matrix.
	 * @param height The height of the matrix.
	 * @param octave The smoothness value.
	 * @param rand The random number generator.
	 * 
	 * @return The Perlin noise matrix.
	 */
	public static float[][] PerlinNoise(int width, int height, int octave, Random rand) {
		float[][] whiteNoise = WhiteNoise(width,height, rand);
		 
	    float[][] smoothNoise = new float[width][height];
		 
	    int samplePeriod = 1 << octave;
		float sampleFrequency = 1.0f / samplePeriod;
		 
		for (int i = 0; i < width; i++) {
		   int sample_i0 = (i / samplePeriod) * samplePeriod;
		   int sample_i1 = (sample_i0 + samplePeriod) % width;
		   float horizontal_blend = (i - sample_i0) * sampleFrequency;
		 
		   for (int j = 0; j < height; j++) {
		       int sample_j0 = (j / samplePeriod) * samplePeriod;
		       int sample_j1 = (sample_j0 + samplePeriod) % height;
		       float vertical_blend = (j - sample_j0) * sampleFrequency;
		 
		       float top = MathUtils.Interpolate(whiteNoise[sample_i0][sample_j0],
		           whiteNoise[sample_i1][sample_j0], horizontal_blend);
		 
		       float bottom = MathUtils.Interpolate(whiteNoise[sample_i0][sample_j1],
		           whiteNoise[sample_i1][sample_j1], horizontal_blend);
		 
		         smoothNoise[i][j] = MathUtils.Interpolate(top, bottom, vertical_blend);
		    }
		 }
		 
		 return smoothNoise;
	}
}
