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

import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.charset.StandardCharsets.*;

/**
 * Contains methods used for string manipulation.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class StringUtils {

	/**
	 * Reverses a string.
	 * 
	 * @param data The string to reverse.
	 * 
	 * @return The reversed string.
	 *
	 * @since 1.0
	 */
	public static String Reverse(String data)
	{
		return new StringBuilder(data).reverse().toString();
	}
	
	/**
	 * Returns the path to the folder where a file is located.
	 * 
	 * @param path The path to the file.
	 * 
	 * @return The path to the folder where the file is located.
	 *
	 * @since 1.0
	 */
	public static String FolderName(String path) {
		return Paths.get(path).getParent().toString();
	}

	/**
	 * Returns the file name from a path.
	 *
	 * @param path The path.
	 *
	 * @return The file name.
	 *
	 * @since 1.0
	 */
	public static String FileName(String path) {
		return Paths.get(path).getFileName().toString();
	}
	
	/**
	 * Trims all spaces and tabs from a string.
	 * 
	 * @param data The string to trim.
	 * 
	 * @return The trimmed string.
	 *
	 * @since 1.0
	 */
	public static String Trim(String data) {
		if(data == null)
			return null;

		int startIndex = 0;
		int endIndex = data.length();

		while(startIndex < data.length() && (data.charAt(startIndex) == ' ' || data.charAt(startIndex) == '\t'))
			++startIndex;
		while(endIndex > 0 && (data.charAt(endIndex - 1) == ' ' || data.charAt(endIndex - 1) == '\t'))
			--endIndex;
		return data.substring(startIndex, endIndex);
	}
	
	/**
	 * Returns the bytes of a string in ASCII format.
	 * 
	 * @param data The string to get the bytes of.
	 *
	 * @return The bytes of the string in ASCII format.
	 *
	 * @since 1.0
	 */
	public static byte[] ToBytes(String data) {
		return data.getBytes(US_ASCII);
	}

	/**
	 * Converts a string to an array of command-line-like string arguments.
	 *
	 * @param args The string to convert.
	 *
	 * @return The array of string arguments.
	 *
	 * @since 1.0
	 */
	@Deprecated
	public static String[] ToCmdArgs(String args) {
		ArrayList<String> fin = new ArrayList<>();

		int quoteCount = 0;
		StringBuilder tmp = new StringBuilder();

		for(int u = 0; u < args.length(); u++) {
			char c = args.charAt(u);

			if(c == '\"') {
				if(quoteCount > 0) {
					if(tmp.length() > 0) {
						quoteCount -= 2;
					} else {
						++quoteCount;
					}
				} else {
					++quoteCount;
				}
			} else if(quoteCount > 0) {
				tmp.append(c);
			} else {
				if(c == ' ') {
					fin.add(tmp.toString());
					tmp = new StringBuilder();
				} else {
					tmp.append(c);
				}
			}
		}

		if(tmp.length() > 0)
			fin.add(tmp.toString());

		return fin.toArray(new String[0]);
	}

	/**
	 * Converts a string to an array of command-line-like string arguments.
	 *
	 * @param args The string to convert.
	 *
	 * @return The array of string arguments.
	 *
	 * @since 1.0
	 */
	public static String[] ToArgs(String args) {
		ArrayList<String> fin = new ArrayList<>();

		int quoteCount = 0;
		StringBuilder tmp = new StringBuilder();

		for(int u = 0; u < args.length(); u++) {
			char c = args.charAt(u);

			if(c == '\\') {
				if(u < args.length() - 1) {
					if(args.charAt(u + 1) == '\"') {
						tmp.append("\"");
						u++;
						continue;
					}
				}
			}
			if(c == '\"') {
				if(quoteCount > 0) {
					if(tmp.length() > 0) {
						quoteCount -= 2;
					} else {
						--quoteCount;
					}
				} else {
					++quoteCount;
				}
			} else if(quoteCount > 0) {
				tmp.append(c);
			} else {
				if(c == ' ') {
					fin.add(tmp.toString());
					tmp = new StringBuilder();
				} else {
					tmp.append(c);
				}
			}
		}

		if(tmp.length() > 0)
			fin.add(tmp.toString());

		return fin.toArray(new String[0]);
	}
}
