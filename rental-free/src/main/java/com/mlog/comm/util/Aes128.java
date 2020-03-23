package com.mlog.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class Aes128 {
	private static final int KEY_LENGTH = 16;
	private static final int KEY_SIZE_BIT = 128;
	
	public static byte[] simpleEncrypt(byte[] plain, byte[] key) throws Exception {
	    SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	    byte[] encrypted = cipher.doFinal(plain);
		return encrypted;
	}

	public static byte[] simpleDecrypt(byte[] encrypted, byte[] key) throws Exception { 
	    SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	    byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}
	
	
	public static byte[] encrypt(byte[] plain, byte[] key) throws InvalidCipherTextException {
		return encrypt(plain, key, null);
	}

	public static byte[] decrypt(byte[] cipher, byte[] key) throws InvalidCipherTextException {
		return decrypt(cipher, key, null);
	}

	public static byte[] decrypt(byte[] cipher, byte[] key, byte[] iv) 
			throws InvalidCipherTextException {
		PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
		CipherParameters cparam = null;
		if (iv == null)
			cparam = new KeyParameter(key);
		else
			cparam = new ParametersWithIV(new KeyParameter(key), iv);
		aes.init(false, cparam);
		return cipherData(aes, cipher);
	}

	public static byte[] encrypt(byte[] plain, byte[] key, byte[] iv) 
			throws InvalidCipherTextException {
		PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
		CipherParameters cparam = null;
		if (iv == null)
			cparam = new KeyParameter(key);
		else
			cparam = new ParametersWithIV(new KeyParameter(key), iv);
		aes.init(true, cparam);
		return cipherData(aes, plain);
	}
	
	public static byte[] getKeyFromStream(InputStream in) {
		byte[] key = new byte[KEY_LENGTH];
		try {
			in.read(key);
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			IOUtils.closeQuietly(in);
		}
		return key;
	}

	/**
	 * keyfile이 있으면 읽고 없으면 새로 생성한다.
	 * IV 지정 방법 등은 http://dukeom.wordpress.com/2013/01/08/aes256-암호화-java-샘플/ 참조.
	 */
	public static byte[] getKeyFromFile(File keyfile) throws NoSuchAlgorithmException {
        byte[] key = null; 
		if (keyfile.exists()) {
			key = readKey(keyfile);
		}
		else {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(KEY_SIZE_BIT);
			SecretKey sk = kgen.generateKey();
			key = sk.getEncoded();
			writeKey(keyfile, key);
		}
		return key;
	}

	/**
	 * keyfile이 있으면 file에서 key를 읽고 
	 * 없으면 Plain Text로 받은 값으로 SecretKey를 만들어 반환한다.
	 * keyfile이 없으면 파일을 생성한다. 
	 */
	public static byte[] getKeyFromFile(File keyfile, String keyStr) {
        byte[] key = null; 
		if (keyfile.exists()) {
			key = readKey(keyfile);
		}
		else {
			Key sk = new SecretKeySpec(keyStr.getBytes(), "AES");
			key = sk.getEncoded();
			
			byte[] padding = new byte[KEY_LENGTH];
			System.arraycopy(key, 0, padding, 0, key.length);
			key = padding;
			writeKey(keyfile, key);
		}
		return key;
	}
	
	private static byte[] readKey(File keyfile) {
		byte[] key = new byte[KEY_LENGTH];
		InputStream in = null;
		try {
			in = new FileInputStream(keyfile);
			in.read(key);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			IOUtils.closeQuietly(in);
		}
		return key;
	}
	
	private static void writeKey(File keyfile, byte[] key) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(keyfile);
			out.write(key);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			IOUtils.closeQuietly(out);
		}
	}
	
	private static byte[] cipherData(PaddedBufferedBlockCipher cipher, byte[] data) 
			throws InvalidCipherTextException {
		int minSize = cipher.getOutputSize(data.length);
		byte[] outBuf = new byte[minSize];
		int length1 = cipher.processBytes(data, 0, data.length, outBuf, 0);
		int length2 = cipher.doFinal(outBuf, length1);
		int actualLength = length1 + length2;
		byte[] result = new byte[actualLength];
		System.arraycopy(outBuf, 0, result, 0, result.length);
		return result;
	}
	
}
