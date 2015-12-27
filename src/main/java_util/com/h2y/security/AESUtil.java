package com.h2y.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 使用AES进行加密解密

 * @author：段晓刚

 * @update：2014年7月28日 下午7:42:38

 * @Email：
 */
public class AESUtil {

	/**
	 * 获取秘钥
	 * 解决Linux下解码错误(获取的秘钥错误问题)
	 * @param strKey
	 * @return
	 * @update：2014年7月29日 下午4:39:50
	 */
	private static SecretKey getKey(String password) {  
		try  {           
			KeyGenerator _generator = KeyGenerator.getInstance("AES");  
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");  
			secureRandom.setSeed(password.getBytes());  
			_generator.init(128 ,secureRandom);  
			return  _generator.generateKey();  
		}  catch  (Exception e) {  
			throw   new  RuntimeException(  " 初始化密钥出现异常 "  );  
		}  
	}   


	/**
	 * 加密
	 * @param content 需要加密的内容
	 * @param password  加密密码
	 * @return
	 * @throws Exception
	 * @update：2014年7月28日 下午7:46:50
	 */
	public static String encrypt2Str(String content,String password) throws Exception{
		return parseByte2HexStr(encrypt(content, password));
	}

	/**
	 * 加密
	 * @param content 需要加密的内容
	 * @param password  加密密码
	 * @return
	 * @throws Exception
	 * @update：2014年7月28日 下午7:47:06
	 */
	public static byte[] encrypt(String content, String password) throws Exception{
		//KeyGenerator kgen = KeyGenerator.getInstance("AES");
		//kgen.init(128, new SecureRandom(password.getBytes()));
		SecretKey secretKey = getKey(password);//kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器
		byte[] byteContent = content.getBytes("utf-8");
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(byteContent);
		return result; // 加密

	}

	public static String decrypt2Str(String content,String password) throws Exception{
		byte[] cs16 = parseHexStr2Byte(content);

		return new String(decrypt(cs16, password));
	}

	/**
	 * 解密
	 * @param content  待解密内容
	 * @param password 解密密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String password) throws Exception{
		//KeyGenerator kgen = KeyGenerator.getInstance("AES");
		//kgen.init(128, new SecureRandom(password.getBytes()));
		SecretKey secretKey = getKey(password);//kgen.generateKey();

		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");            
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(content);
		return result; // 加密
	}

	/**
	 * 将二进制转换成16进制
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**将16进制转换为二进制
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	//测试
	public static void main(String[] args) throws Exception {
		String content = "{\"total\":\"0.01\",\"notifyurl\":\"''\",\"orderno\":\"1000000001\",\"orderurl\":\"'段晓刚'\"}";
		String password = "12345678";
		//加密
		System.out.println("加密前：" + content);
		byte[] encryptResult = encrypt(content, password);
		String encryptResultStr = parseByte2HexStr(encryptResult);
		System.out.println("加密后：" + encryptResultStr);
		//解密
		byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
		byte[] decryptResult = decrypt(decryptFrom,password);
		System.out.println("解密后：" + new String(decryptResult));


		String encode = encrypt2Str(content, password);
		System.out.println("encode \t"+encode);
		String decode = decrypt2Str(encode, password);
		System.out.println("decode \t"+decode);
	}
}