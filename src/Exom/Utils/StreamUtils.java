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
