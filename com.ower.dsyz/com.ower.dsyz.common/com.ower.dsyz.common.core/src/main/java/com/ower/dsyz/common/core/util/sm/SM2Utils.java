package com.ower.dsyz.common.core.util.sm;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;


public class SM2Utils {

	
	/**
	 * 生成秘钥对
	 * @return
	 */
	public static Map<String,String> generateKeyPair() {
		Map<String,String> keys = new HashMap<>(2);
		AsymmetricCipherKeyPair key = SM2.Instance().ecc_key_pair_generator.generateKeyPair();
		ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters) key.getPrivate();
		ECPublicKeyParameters ecpub = (ECPublicKeyParameters) key.getPublic();
		keys.put("publicKey", SMUtil.byteToHex(ecpub.getQ().getEncoded()));// publicKey
		keys.put("privateKey", SMUtil.byteToHex(ecpriv.getD().toByteArray()));// privateKey
		return keys;
	}

	private static String encrypt(byte[] publicKey, byte[] data) throws IOException {
		if (publicKey == null || publicKey.length == 0) {
			return null;
		}
		if (data == null || data.length == 0) {
			return null;
		}
		byte[] source = new byte[data.length];
		System.arraycopy(data, 0, source, 0, data.length);
		Cipher cipher = new Cipher();
		SM2 sm2 = SM2.Instance();
		ECPoint userKey = sm2.ecc_curve.decodePoint(publicKey);
		ECPoint c1 = cipher.Init_enc(sm2, userKey);
		cipher.Encrypt(source);
		byte[] c3 = new byte[32];
		cipher.Dofinal(c3);
		return SMUtil.byteToHex(c1.getEncoded()) + SMUtil.byteToHex(source) + SMUtil.byteToHex(c3);

	}

	private static byte[] decrypt(byte[] privateKey, byte[] encryptedData) throws IOException {
		if (privateKey == null || privateKey.length == 0) {
			return null;
		}
		if (encryptedData == null || encryptedData.length == 0) {
			return null;
		}
		String data = SMUtil.byteToHex(encryptedData);
		byte[] c1Bytes = SMUtil.hexToByte(data.substring(0, 130));
		int c2Len = encryptedData.length - 97;
		byte[] c2 = SMUtil.hexToByte(data.substring(130, 130 + 2 * c2Len));
		byte[] c3 = SMUtil.hexToByte(data.substring(130 + 2 * c2Len, 194 + 2 * c2Len));
		SM2 sm2 = SM2.Instance();
		BigInteger userD = new BigInteger(1, privateKey);
		ECPoint c1 = sm2.ecc_curve.decodePoint(c1Bytes);
		Cipher cipher = new Cipher();
		cipher.Init_dec(userD, c1);
		cipher.Decrypt(c2);
		cipher.Dofinal(c3);
		return c2;
	}
	
	
	/**
	 * 私钥解密数据
	 * @param privateKey
	 * @param encryptedData
	 * @return
	 */
	public static String decrypt(String privateKey, String encryptedData){
		try {
			return new String(decrypt(privateKey.getBytes(),encryptedData.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 公钥加密数据
	 * @param publicKey
	 * @param encryptedData
	 * @return
	 */
	public static String encrypt(String publicKey, String encryptedData){
		try {
			return new String(encrypt(publicKey.getBytes(),encryptedData.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}