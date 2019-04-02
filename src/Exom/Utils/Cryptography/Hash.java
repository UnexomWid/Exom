package Exom.Utils.Cryptography;

import Exom.Utils.ByteUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Contains methods used for hashing.
 *
 * @author UnexomWid
 *
 * @since 1.0
 */
public class Hash {

    /**
     * Computes the MD5 hash of a byte array.
     *
     * @param data The byte array to compute the hash of.
     *
     * @return The MD5 hash of the byte array.
     *
     * @since 1.0
     */
    public static byte[] RawMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(data);
    }
    /**
     * Computes the MD5 hash of a file.
     *
     * @param data The file to compute the hash of.
     *
     * @return The MD5 hash of the file.
     *
     * @since 1.0
     */
    public static byte[] RawMD5(String data) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(ByteUtils.FileToBytes(data));
    }

    /**
     * Computes the MD5 hash of a byte array, as a hex string.
     *
     * @param data The byte array to compute the hash of.
     *
     * @return The MD5 hash of the byte array, as a hex string.
     *
     * @since 1.0
     */
    public static String MD5(byte[] data) throws NoSuchAlgorithmException {
        return ByteUtils.ToHex(RawMD5(data));
    }
    /**
     * Computes the MD5 hash of a file as a hex string.
     *
     * @param data The file to compute the hash of.
     *
     * @return The MD5 hash of the file, as a hex string.
     *
     * @since 1.0
     */
    public static String MD5(String data) throws IOException, NoSuchAlgorithmException {
        return ByteUtils.ToHex(RawMD5(data));
    }

    /**
     * Computes the SHA1 hash of a byte array.
     *
     * @param data The byte array to compute the hash of.
     *
     * @return The SHA1 hash of the byte array.
     *
     * @since 1.0
     */
    public static byte[] RawSHA1(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return md.digest(data);
    }
    /**
     * Computes the SHA1 hash of a file.
     *
     * @param data The file to compute the hash of.
     *
     * @return The SHA1 hash of the file.
     *
     * @since 1.0
     */
    public static byte[] RawSHA1(String data) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return md.digest(ByteUtils.FileToBytes(data));
    }

    /**
     * Computes the SHA1 hash of a byte array, as a hex string.
     *
     * @param data The byte array to compute the hash of.
     *
     * @return The SHA1 hash of the byte array, as a hex string.
     *
     * @since 1.0
     */
    public static String SHA1(byte[] data) throws NoSuchAlgorithmException {
        return ByteUtils.ToHex(RawSHA1(data));
    }
    /**
     * Computes the SHA1 hash of a file as a hex string.
     *
     * @param data The file to compute the hash of.
     *
     * @return The SHA1 hash of the file, as a hex string.
     *
     * @since 1.0
     */
    public static String SHA1(String data) throws IOException, NoSuchAlgorithmException {
        return ByteUtils.ToHex(RawSHA1(data));
    }

    /**
     * Computes the SHA256 hash of a byte array.
     *
     * @param data The byte array to compute the hash of.
     *
     * @return The SHA256 hash of the byte array.
     *
     * @since 1.0
     */
    public static byte[] RawSHA256(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(data);
    }
    /**
     * Computes the SHA256 hash of a file.
     *
     * @param data The file to compute the hash of.
     *
     * @return The SHA256 hash of the file.
     *
     * @since 1.0
     */
    public static byte[] RawSHA256(String data) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(ByteUtils.FileToBytes(data));
    }

    /**
     * Computes the SHA256 hash of a byte array, as a hex string.
     *
     * @param data The byte array to compute the hash of.
     *
     * @return The SHA256 hash of the byte array, as a hex string.
     *
     * @since 1.0
     */
    public static String SHA256(byte[] data) throws NoSuchAlgorithmException {
        return ByteUtils.ToHex(RawSHA256(data));
    }
    /**
     * Computes the SHA256 hash of a file as a hex string.
     *
     * @param data The file to compute the hash of.
     *
     * @return The SHA256 hash of the file, as a hex string.
     *
     * @since 1.0
     */
    public static String SHA256(String data) throws IOException, NoSuchAlgorithmException {
        return ByteUtils.ToHex(RawSHA256(data));
    }
}
