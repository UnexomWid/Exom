package Exom.Utils.Cryptography;

import static java.nio.charset.StandardCharsets.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import Exom.Utils.ByteUtils;
import Exom.Utils.StreamUtils;
import Exom.Utils.StringUtils;
import Exom.Objects.NamedObject;

/**
 * Contains methods used for Exom Data Package interaction.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class EXDP {

	/**
	 * The EXDP magic bytes.
	 *
	 * @since 1.0
	 */
    public static final byte[] EXDP_MAGIC = StringUtils.ToBytes("EXDP");
    
    /**
     * Writes an EXDP. If the key is empty, the EXDP will not be encrypted.
     * 
     * @param file The file to write in.
     * @param data The data to encode and pack.
     * @param key The encryption key.
     * 
     * @throws IOException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     * @throws InvalidAlgorithmParameterException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     *
	 * @since 1.0
     */
    public static void Write(String file, List<NamedObject<byte[]>> data, String key) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
    	FileOutputStream fos = new FileOutputStream(file);
    	ByteArrayInputStream bais = new ByteArrayInputStream(Encode(data));
    	
    	Pack(bais, fos, key);
    	
    	fos.close();
    	bais.close();
    }
    
    /**
     * Reads an EXDP. If the key is empty, the EXDP will not be decrypted.
     * 
     * @param file The file to read from.
     * @param key The decryption key.
     * 
     * @return The unpacked and decoded EXDP.
     * 
     * @throws IOException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     * @throws InvalidAlgorithmParameterException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     *
	 * @since 1.0
     */
    public static List<NamedObject<byte[]>> Read(String file, String key) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
    	FileInputStream fis = new FileInputStream(file);
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	
    	Unpack(fis, baos, key);
    	
    	List<NamedObject<byte[]>> fin = Decode(baos.toByteArray());
    	
    	fis.close();
    	baos.close();
    	
    	return fin;
    }
    
    /**
     * Encodes a list of named bytes.
     * 
     * @param data The list of named bytes to encode.
     * 
     * @return The encoded byte array.
     *
	 * @since 1.0
     */
    public static byte[] Encode(List<NamedObject<byte[]>> data) {
    	ByteArrayOutputStream fin = new ByteArrayOutputStream();
    	
    	for(int u = 0; u < data.size(); u++) {
    		NamedObject<byte[]> item = data.get(u);
    		int nameLength = item.Name.length();
    		if(nameLength > 255)
    			throw new IllegalArgumentException("Name length is greater than 255.");
    		
    		fin.write((byte)nameLength);
    		byte[] nameBytes = item.Name.getBytes(US_ASCII);
    		for(int l = 0; l < nameBytes.length; l++)
    			fin.write(nameBytes[l]);
    		
    		byte[] valueLengthBytes = ByteUtils.IntToBytes(item.Value.length);
    		for(int l = 0; l < 4; l++)
    			fin.write(valueLengthBytes[l]);
    		for(int l = 0; l < item.Value.length; l++)
    			fin.write(item.Value[l]);
    	}
    	
    	return fin.toByteArray();
    }
    
    /**
     * Decodes a byte array.
     * 
     * @param data The byte array to decode.
     * 
     * @return The decoded named bytes list.
     *
	 * @since 1.0
     */
    public static List<NamedObject<byte[]>> Decode(byte[] data) {
    	List<NamedObject<byte[]>> fin = new ArrayList<NamedObject<byte[]>>();
    	
    	int u = 0;
    	while(u < data.length) {
    		int nameLength = ByteUtils.ToUnsigned(data[u++]);
    		String name = new String(Arrays.copyOfRange(data, u, u + nameLength));
    		u += nameLength;

    		int valueLength = ByteUtils.ToInt(Arrays.copyOfRange(data, u, u + 4));
    		u += 4;
    		byte[] value = Arrays.copyOfRange(data, u, u + valueLength);
    		u += valueLength;
    		
    		fin.add(new NamedObject<byte[]>(name, value));
    	}
    	
    	return fin;
    }
    
    /**
     * Packs bytes from a stream and writes them to another stream.
     * 
     * @param in The input stream.
     * @param out The output stream.
     * @param key The key to pack with.
     * 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     * @throws InvalidAlgorithmParameterException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     *
	 * @since 1.0
     */
	public static void Pack(InputStream in, OutputStream out, String key) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		boolean encrypt = false;
		
		out.write(EXDP_MAGIC);
		if(key != null) {
			if(key.length() > 0) {
				encrypt = true;
				EXEA.Encrypt(in, out, key);
			}
		}
		if(!encrypt) {
			StreamUtils.CopyStream(in, out);
		}
	}
	/**
     * Packs bytes to an EXDP.
     * 
     * @param data The bytes to pack.
     * @param key The key to pack with.
     * 
     * @return The packed bytes.
     * 
	 * @throws IOException 
	 * @throws IllegalBlockSizeException 
	 * @throws BadPaddingException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 *
	 * @since 1.0
     */
	public static byte[] Pack(byte[] data, String key) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Pack(bais, baos, key);
		
		baos.flush();
		
		byte[] fin = baos.toByteArray();
		
		bais.close();
		baos.close();
		
		return fin;
	}
	
	/**
     * Unpacks bytes from a stream, and writes them to another stream.
     * 
     * @param in The input stream.
     * @param out The output stream.
     * @param key The key to unpack with.
     * 
	 * @throws IllegalBlockSizeException 
	 * @throws BadPaddingException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 *
	 * @since 1.0
     */
	public static void Unpack(InputStream in, OutputStream out, String key) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		byte[] shouldBeMagic = new byte[EXDP_MAGIC.length];
		in.read(shouldBeMagic);
        if (!Arrays.equals(shouldBeMagic, EXDP_MAGIC)) 
            throw new IllegalArgumentException("Initial bytes from input do not match EXDP_MAGIC.");

		if(key != null) {
			if(key.length() > 0)
				EXEA.Decrypt(in, out, key);
		}
		StreamUtils.CopyStream(in, out);
	}
	/**
     * Unpacks bytes from an EXDP.
     * 
     * @param data The bytes to unpack.
     * @param key The key to unpack with.
     * @return The unpacked bytes.
     * 
	 * @throws IllegalBlockSizeException 
	 * @throws BadPaddingException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 *
	 * @since 1.0
     */
	public static byte[] Unpack(byte[] data, String key) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Unpack(bais, baos, key);
		
		baos.flush();
		
		byte[] fin = baos.toByteArray();
		
		bais.close();
		baos.close();
		
		return fin;
	}
}
