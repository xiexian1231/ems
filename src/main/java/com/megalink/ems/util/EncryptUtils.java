package com.megalink.ems.util;

import java.security.Key;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

import com.megalink.ems.model.User;

/**
 * 备注： shiro进行加密解密的工具类封装
 */
public final class EncryptUtils {

	// 需要与shiro配置文件中一致
	private static final int HASH_ITERATIONS = 3;

	/**
	 * base64进制加密
	 * 
	 * @param password
	 * @return
	 */
	public static String encrytBase64(String password) {
		byte[] bytes = password.getBytes();
		return Base64.encodeToString(bytes);
	}

	/**
	 * base64进制解密
	 * 
	 * @param cipherText
	 * @return
	 */
	public static String decryptBase64(String cipherText) {
		return Base64.decodeToString(cipherText);
	}

	/**
	 * 16进制加密
	 * 
	 * @param password
	 * @return
	 */
	public static String encrytHex(String password) {
		byte[] bytes = password.getBytes();
		return Hex.encodeToString(bytes);
	}

	/**
	 * 16进制解密
	 * 
	 * @param cipherText
	 * @return
	 */
	public static String decryptHex(String cipherText) {
		return new String(Hex.decode(cipherText));
	}

	public static String generateKey() {
		AesCipherService aesCipherService = new AesCipherService();
		Key key = aesCipherService.generateNewKey();
		return Base64.encodeToString(key.getEncoded());
	}

	/**
	 * 对密码进行md5加密,并返回密文和salt，包含在User对象中
	 * 
	 * @param password
	 *            密码
	 * @return 密文和salt
	 */
	public static User md5Password(User user) {
		SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
		String salt = secureRandomNumberGenerator.nextBytes().toHex();
		String password_cipherText = new Md5Hash(user.getPassword(), salt, HASH_ITERATIONS).toHex();
		user.setPassword(password_cipherText);
		user.setSalt(salt);
		return user;
	}

}
