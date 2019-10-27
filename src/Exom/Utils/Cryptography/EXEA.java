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

package Exom.Utils.Cryptography;

import java.io.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import Exom.Utils.*;

import java.security.*;
import java.util.Arrays;


/**
 * Contains methods used for encrypting and decrypting bytes with the Exom Encryption Algorithm.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class EXEA {

	/**
	 * The EXEA magic bytes.
	 */
    public static final byte[] EXEA_MAGIC = StringUtils.ToBytes("EXEA");

    /**
     * Encrypts a byte array.
     * 
     * @param data The byte array to encrypt.
     * @param key The key to encrypt with.
     * 
     * @return The encrypted byte array.
     * 
     * @throws IOException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws InvalidAlgorithmParameterException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     *
	 * @since 1.0
     */
    public static byte[] Encrypt(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Encrypt(bais, baos, key);
		
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
     * @param key The key to encrypt with.
     * 
     * @throws IOException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws InvalidAlgorithmParameterException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     *
	 * @since 1.0
     */
	public static void Encrypt(InputStream in, OutputStream out, String key) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] buffer = new byte[8192];
		int count = 0;
		out.write(EXEA.EXEA_MAGIC);
		
		while((count = in.read(buffer)) != -1) {
			byte[] encoded = EXEA.RawEncrypt(count == 8192 ? buffer : Arrays.copyOfRange(buffer, 0, count), key);
			out.write(encoded);
		}
	}
	
	/**
     * Decrypts a byte array.
     * 
     * @param data The byte array to decrypt.
     * @param key The key to decrypt with.
     * 
     * @return The decrypted byte array.
     * 
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 *
	 * @since 1.0
     */
	public static byte[] Decrypt(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Decrypt(bais, baos, key);
		
		baos.flush();
		
		byte[] fin = baos.toByteArray();
		
		bais.close();
		baos.close();
		
		return fin;
	}
	/**
     * Decrypts bytes from a stream and writes them to another stream.
     * 
     * @param in The stream to decrypt from.
     * @param out The stream to write to.
     * @param key The key to decrypt with.
     * 
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 *
	 * @since 1.0
     */
	public static void Decrypt(InputStream in, OutputStream out, String key) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] buffer = new byte[8208];
		int count = 0;
		
		byte[] shouldBeMagic = new byte[EXEA_MAGIC.length];
		in.read(shouldBeMagic);
        if (!Arrays.equals(shouldBeMagic, EXEA_MAGIC)) 
            throw new IllegalArgumentException("Initial bytes from input do not match EXEA_MAGIC.");
	
		while((count = in.read(buffer)) != -1) {
			byte[] encoded = count == 8208 ? buffer : Arrays.copyOfRange(buffer, 0, count);
			byte[] decoded = EXEA.RawDecrypt(encoded, key);
			out.write(decoded);
		}
	}
	
	/**
     * Encrypts a byte array.
     * 
     * @param plain The byte array to encrypt.
     * @param password The key to encrypt with.
     * 
     * @return The encrypted byte array.
     * 
	 * @throws NoSuchAlgorithmException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 *
	 * @since 1.0
     */
    private static byte[] RawEncrypt(byte[] plain, String password) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    	final MessageDigest md = MessageDigest.getInstance("MD5");
		
        final byte[] pass = StringUtils.ToBytes(password);
        final byte[] salt = Arrays.copyOfRange(md.digest(pass), 0, 8);

        final byte[] passAndSalt = ArrayUtils.Concat(pass, salt);
        byte[] hash = new byte[0];
        byte[] keyAndIv = new byte[0];
        for (int i = 0; i < 3 && keyAndIv.length < 48; i++) {
            final byte[] hashData = ArrayUtils.Concat(hash, passAndSalt);
            hash = md.digest(hashData);
            keyAndIv = ArrayUtils.Concat(keyAndIv, hash);
        }

        final byte[] keyValue = Arrays.copyOfRange(keyAndIv, 0, 32);
        final byte[] iv = Arrays.copyOfRange(keyAndIv, 32, 48);
        final SecretKeySpec key = new SecretKeySpec(keyValue, "AES");

        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] data = cipher.doFinal(plain);
        //data = ArrayUtils.Concat(EXEA_MAGIC, data);
        return data;
    }
    /**
     * Decrypts a byte array.
     * 
     * @param encrypted The byte array to decrypt.
     * @param password The key to decrypt with.
     * 
     * @return The decrypted byte array.
     * 
     * @throws NoSuchAlgorithmException 
     * @throws NoSuchPaddingException 
     * @throws InvalidAlgorithmParameterException 
     * @throws InvalidKeyException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     *
	 * @since 1.0
     */
    private  static byte[] RawDecrypt(byte[] encrypted, String password) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        /*final byte[] shouldBeMagic = Arrays.copyOfRange(encrypted, 0, EXEA_MAGIC.length);
        if (!Arrays.equals(shouldBeMagic, EXEA_MAGIC)) 
        {
            throw new IllegalArgumentException("Initial bytes from input do not match EXOM_MAGIC.");
        }*/
        
        final MessageDigest md = MessageDigest.getInstance("MD5");

        final byte[] pass = StringUtils.ToBytes(password);
        final byte[] salt = Arrays.copyOfRange(md.digest(pass), 0, 8);

        final byte[] passAndSalt = ArrayUtils.Concat(pass, salt);

        byte[] hash = new byte[0];
        byte[] keyAndIv = new byte[0];
        for (int i = 0; i < 3 && keyAndIv.length < 48; i++) {
            final byte[] hashData = ArrayUtils.Concat(hash, passAndSalt);
            hash = md.digest(hashData);
            keyAndIv = ArrayUtils.Concat(keyAndIv, hash);
        }

        final byte[] keyValue = Arrays.copyOfRange(keyAndIv, 0, 32);
        final SecretKeySpec key = new SecretKeySpec(keyValue, "AES");

        final byte[] iv = Arrays.copyOfRange(keyAndIv, 32, 48);

        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        //final byte[] clear = cipher.doFinal(encrypted, EXEA_MAGIC.length, encrypted.length - EXEA_MAGIC.length);
        final byte[] clear = cipher.doFinal(encrypted);
        
        return clear;
    }
}
