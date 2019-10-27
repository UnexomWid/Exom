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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Contains methods used for stream manipulation.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class StreamUtils {

	/**
	 * Copies bytes from a stream to another, with a buffer size of 8192.
	 * 
	 * @param in The stream to copy from.
	 * @param out The stream to copy to.
	 * 
	 * @throws IOException 
	 *
	 * @since 1.0
	 */
	public static void CopyStream(InputStream in, OutputStream out) throws IOException {
		CopyStream(in, out, 8192);
	}
	
	/**
	 * Copies bytes from a stream to another.
	 * 
	 * @param in The stream to copy from.
	 * @param out The stream to copy to.
	 * @param bufferSize The buffer size.
	 * 
	 * @throws IOException 
	 *
	 * @since 1.0
	 */
	public static void CopyStream(InputStream in, OutputStream out, int bufferSize) throws IOException {
		byte[] buffer = new byte[bufferSize];
		int count;
		
		while((count = in.read(buffer)) != -1)
			out.write(buffer, 0, count);
	}
	
	/**
	 * Skips an amount of bytes from a byte stream.
	 * 
	 * @param in The stream to skip from.
	 * @param count The amount of bytes to skip.
	 * 
	 * @throws IOException 
	 *
	 * @since 1.0
	 */
	public static void SkipBytes(InputStream in, int count) throws IOException {
		byte[] tmp = new byte[count];
		in.read(tmp, 0, count);
		tmp = null;
	}

	/**
	 * Writes a string to a stream.
	 *
	 * @param out The stream.
	 * @param string The string to write.
	 *
	 * @since 1.0
	 */
	public static void WriteString(OutputStream out, String string) throws IOException {
		out.write(StringUtils.ToBytes(string));
	}
}
