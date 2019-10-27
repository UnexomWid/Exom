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

package Exom.Utils.Cryptography.Ciphers;

/**
 * Contains methods used for encrypting and decrypting text with the Vigenere cipher.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class Vigenere {

	/**
	 * Encrypts a string with a key.
	 * 
	 * @param data The string to encrypt.
	 * @param key The key to encrypt with.
	 * 
	 * @return The encrypted string.
	 *
	 * @since 1.0
	 */
	public static String Encrypt(String data, String key) {
        String fin = "";
            
        key = key.toLowerCase();

        if(key.length() > data.length())
            key = key.substring(0, data.length());
            
        int i = 0, mxi = key.length();
        while(key.length() < data.length()) {
            key += key.charAt(i);
            i++;
            if(i == mxi)
            	i = 0;
        }
            
        String lowData = data.toLowerCase();
        int keyIndex = 0;

        for (int u = 0; u < data.length(); u++) {
            char current = lowData.charAt(u);
            if((byte)current >= 97 && (byte)current <= 122)
            {
            	fin += (char)(((current + key.charAt(keyIndex) - 194) % 26) + ((byte)data.charAt(u) > 90 ? 97 : 65));
            	keyIndex++;
            }
            else fin += data.charAt(u);
        }

        return fin;
    }
	
	/**
	 * Decrypts a string with a key.
	 * @param data The string to decrypt.
	 * @param key The key to decrypt with.
	 * 
	 * @return The decrypted string.
	 *
	 * @since 1.0
	 */
	public static String Decrypt(String data, String key) {
        String fin = "";
            
        key = key.toLowerCase();

        if(key.length() > data.length())
            key = key.substring(0, data.length());
            
        int i = 0, mxi = key.length();
        while(key.length() < data.length()) {
            key += key.charAt(i);
            i++;
            if(i == mxi)
            	i = 0;
        }
            
        String lowData = data.toLowerCase();
        int keyIndex = 0;

        for (int u = 0; u < data.length(); u++) {
            char current = lowData.charAt(u);
            if((byte)current >= 97 && (byte)current <= 122) {
	            int mod = (current - key.charAt(keyIndex)) % 26;
	            if(mod < 0)
	            	mod = 26 + mod;
	            	
	            fin += (char)(mod + ((byte)data.charAt(u) > 90 ? 97 : 65));
	            	
	            keyIndex++;
            }
            else fin += data.charAt(u);
        }

        return fin;
    }
}
