package Exom.Utils;

import java.io.IOException;
import java.io.FileOutputStream;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

/**
 * Contains methods used for byte manipulation.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class ByteUtils {

	/**
	 * Returns the contents of a file as a byte array.
	 * 
	 * @param path The file to read the contents of.
	 * 
	 * @return The content of the file as a byte array.
	 * 
	 * @throws IOException 
	 *
	 * @since 1.0
	 */
	public static byte[] FileToBytes(String path) throws IOException {
		return Files.readAllBytes(Paths.get(path));
	}
	
	/**
	 * Writes a byte array to a file. If the file already exists, it will be overwritten.
	 * 
	 * @param data The byte array to write to the file.
	 * @param path The file to write to.
	 * 
	 * @throws IOException 
	 *
	 * @since 1.0
	 */
	public static void ToFile(byte[] data, String path) throws IOException {
		FileOutputStream writef = new FileOutputStream(path);
		writef.write(data);
		writef.close();
	}
	
	/**
	 * Returns the hex representation of a byte array.
	 * 
	 * @param data The byte array to convert.
	 * 
	 * @return The hex representation of the byte array.
	 *
	 * @since 1.0
	 */
	public static String ToHex(byte[] data) {
		StringBuilder sb = new StringBuilder();
	    for (byte b : data)
	        sb.append(String.format("%02X", b));
	    return sb.toString();
	}
	
	/**
	 * Returns the unsigned counterpart of a signed byte.
	 * 
	 * @param data The byte to get the unsigned counterpart of.
	 * 
	 * @return The unsigned counterpart of the signed byte.
	 *
	 * @since 1.0
	 */
	public static int ToUnsigned(byte data)
	{
		return data & 0xFF;
	}

	/**
	 * Returns the bytes of an integer,
	 * 
	 * @param data The integer to get the bytes of.
	 * 
	 * @return The bytes of the integer.
	 *
	 * @since 1.0
	 */
	public static byte[] IntToBytes(int data) {
	    byte[] result = new byte[4]; // 8;
	    for (int i = 3; i >= 0; i--) {
	        result[i] = (byte)(data & 0xFF);
	        data >>= 8;
	    }
	    return result;
	}

	/**
	 * Returns the int representation of 4 bytes.
	 * 
	 * @param data The bytes to get the int representaion of.
	 * 
	 * @return The int representation of the bytes.
	 *
	 * @since 1.0
	 */
	public static int ToInt(byte[] data) {
	    int result = 0;
	    for (int i = 0; i < 4; i++) {
	        result <<= 8;
	        result |= (data[i] & 0xFF);
	    }
	    return result;
	}

	/**
	 * Converts a byte array to a string in ASCII format.
	 *
	 * @param data The byte array to convert.
	 * @return The string in ASCII format.
	 *
	 * @since 1.0
	 */
	public static String ToString(byte[] data) {
		return new String(data, StandardCharsets.US_ASCII);
	}
}
