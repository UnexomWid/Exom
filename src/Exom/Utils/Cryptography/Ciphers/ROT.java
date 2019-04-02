package Exom.Utils.Cryptography.Ciphers;

import Exom.Utils.Cryptography.*;

/**
 * Contains methods used for encrypting and decrypting text with the ROT cipher.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class ROT {

	/**
	 * Runs the ROT cipher.
	 * 
	 * @param data The string to run on.
	 * @param n The ROT step.
	 * 
	 * @return The processed string.
	 *
	 * @since 1.0
	 */
	public static String Run(String data, int n) {
        String fin = "";

        n %= 26;

        if (n == 0)
            return data;

        for (int u = 0; u < data.length(); u++) {
            if (Constants.LowerAlphabet.contains(String.valueOf(data.charAt(u))))
                fin += Constants.LowerAlphabet.charAt(((byte)data.charAt(u) - 97 + n) % 26);
            else if (Constants.UpperAlphabet.contains(String.valueOf(data.charAt(u))))
                fin += Constants.UpperAlphabet.charAt(((byte)data.charAt(u) - 65 + n) % 26);
            else fin += data.charAt(u);
        }

        return fin;
    }
}
