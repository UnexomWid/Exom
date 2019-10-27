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
