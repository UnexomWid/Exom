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
