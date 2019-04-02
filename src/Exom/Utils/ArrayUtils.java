package Exom.Utils;

/**
 * Contains methods used for Array manipulation.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class ArrayUtils {

	/**
	 * Concatenates two byte arrays.
	 * 
	 * @param a The first array.
	 * @param b The second array.
	 * 
	 * @return The concatenated array.
	 *
	 * @since 1.0
	 */
	public static byte[] Concat(final byte[] a, final byte[] b) {
        final byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }
}
