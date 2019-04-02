package Exom.Utils.Cryptography;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Exom.Utils.ByteUtils;

/**
 * Contains methods used for encrypting and decrypting data with the Exom Dictionary Algorithm.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class EXDA {

	/**
	 * Encrypts a byte array.
	 * 
	 * @param data The data to encrypt.
	 * @param dictionary The EXDA dictionary.
	 * 
	 * @return The encrypted.
	 * 
	 * @throws IOException
	 *
	 * @since 1.0
	 */
	public static byte[] Encrypt(byte[] data, byte[] dictionary) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Encrypt(bais, baos, dictionary);
		
		baos.flush();
		
		byte[] fin = baos.toByteArray();
		
		bais.close();
		baos.close();
		
		return fin;
	}
	/**
	 * Encrypts bytes from a stream and writes them to another stream.
	 * 
	 * @param in The stream to encrypt from.
	 * @param out The stream to write to.
	 * @param dictionary The dictionary.
	 * 
	 * @throws IOException 
	 *
	 * @since 1.0
	 */
	public static void Encrypt(InputStream in, OutputStream out, byte[] dictionary) throws IOException {
		byte[] buffer = new byte[8192];
		int count = 0;

		while((count = in.read(buffer)) != -1) {
			out.write(Replace(buffer, 0, count, dictionary));
		}
	}
	
	/**
	 * Decrypts a byte array.
	 * 
	 * @param data The data to decrypt.
	 * @param dictionary The EXDA dictionary.
	 * 
	 * @return The decrypted byte array.
	 * 
	 * @throws IOException
	 *
	 * @since 1.0
	 */
	public static byte[] Decrypt(byte[] data, byte[] dictionary) throws IOException {
		return Encrypt(data, GetDictionaryPair(dictionary));
	}
	/**
	 * Decrypts bytes from a stream and writes them to another stream.
	 * 
	 * @param in The stream to decrypt from.
	 * @param out The stream to write to.
	 * @param dictionary The dictionary.
	 * 
	 * @throws IOException 
	 *
	 * @since 1.0
	 */
	public static void Decrypt(InputStream in, OutputStream out, byte[] dictionary) throws IOException {
		Encrypt(in, out, GetDictionaryPair(dictionary));
	}
	
	/**
	 * Generates a random dictionary.
	 * 
	 * @return The generated dictionary.
	 *
	 * @since 1.0
	 */
	public static byte[] Generate() {
		Random rand = new Random();
		
		byte[] fin = new byte[256];
		List<Byte> pool = new ArrayList<Byte>();

		for(int u = -128; u < 128; u++)
			pool.add((byte)u);
		
		for(int u = 0; u < 256; u++) {
			int index = rand.nextInt(pool.size());
			byte chosen = pool.get(index);

			fin[u] = chosen;
			pool.remove(index);
		}
		
		return fin;
	}
	/**
	 * Generates a random dictionary.
	 * 
	 * @param seed The random generator seed.
	 * 
	 * @return The generated dictionary.
	 *
	 * @since 1.0
	 */
	public static byte[] Generate(long seed) {
		Random rand = new Random(seed);
		
		byte[] fin = new byte[256];
		List<Byte> pool = new ArrayList<Byte>();

		for(int u = -128; u < 128; u++)
			pool.add((byte)u);
		
		for(int u = 0; u < 256; u++) {
			int index = rand.nextInt(pool.size());
			byte chosen = pool.get(index);

			fin[u] = chosen;
			pool.remove(index);
		}
		
		return fin;
	}
	/**
	 * Generates a random dictionary.
	 * 
	 * @param rand The random number generator.
	 * 
	 * @return The generated dictionary.
	 *
	 * @since 1.0
	 */
	public static byte[] Generate(Random rand) {
		byte[] fin = new byte[256];
		List<Byte> pool = new ArrayList<Byte>();

		for(int u = -128; u < 128; u++)
			pool.add((byte)u);
		
		for(int u = 0; u < 256; u++) {
			int index = rand.nextInt(pool.size());
			byte chosen = pool.get(index);

			fin[u] = chosen;
			pool.remove(index);
		}
		
		return fin;
	}
	
	/**
	 * Gets the reversed counterpart of a dictionary.
	 * 
	 * @param dictionary The dictionary.
	 * 
	 * @return The reversed counterpart of the dictionary.
	 *
	 * @since 1.0
	 */
	public static byte[] GetDictionaryPair(byte[] dictionary) {
		byte[] fin = new byte[256];
		
		for(int u = 0; u < 256; u++)
			fin[ByteUtils.ToUnsigned(dictionary[u])] = (byte)u;
		
		return fin;
	}
	
	/**
	 * Replaces the bytes in a byte array using a dictionary.
	 * 
	 * @param data The bytes to replace.
	 * @param offset The starting offset.
	 * @param count The amount of bytes to replace.
	 * @param dictionary The dictionary.
	 * 
	 * @return The new byte array.
	 *
	 * @since 1.0
	 */
	public static byte[] Replace(byte[] data, int offset, int count, byte[] dictionary) {
		byte[] fin = new byte[data.length];
		
		for(int u = offset; u < count; u++)
			fin[u] = dictionary[ByteUtils.ToUnsigned(data[u])];
		
		return fin;
	}
}
