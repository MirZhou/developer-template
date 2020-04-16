package cn.mir.common.utilities.encryption;


import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

/**
 * DES算法实现
 *
 * @author Mir
 */
public class DesUtils {
    /**
     * 字符串加密
     *
     * @param data 要加密的字符串
     * @param key  密钥
     * @return 加密后的字符串
     * @throws Exception 异常
     */
    public static String encrypt(String data, String key) throws Exception {
        // 获取加密后的字节
        byte[] encrypt = encrypt(data.getBytes(), key.getBytes());

        return Base64.getEncoder().encodeToString(encrypt);
    }

    /**
     * 加密
     *
     * @param data 要加密的对象
     * @param key  密钥
     * @return 加密后的字节
     * @throws Exception 异常
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = cipherInit(key, Cipher.ENCRYPT_MODE);

        return cipher.doFinal(data);
    }

    /**
     * 加密字符串解密
     *
     * @param data 要解密的加密字符串
     * @param key  密钥
     * @return 解密后的字符串
     * @throws Exception 异常
     */
    public static String decrypt(String data, String key) throws Exception {
        byte[] buff = Base64.getDecoder().decode(data);

        // 获取解密后的字节
        byte[] decrypt = decrypt(buff, key.getBytes());

        // 返回解密后的字符串
        return new String(decrypt, StandardCharsets.UTF_8);
    }

    /**
     * 解密
     *
     * @param data 要解密的对象
     * @param key  密钥
     * @return 解密后的字节
     * @throws Exception 异常
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = cipherInit(key, Cipher.DECRYPT_MODE);

        // 解密并返回
        return cipher.doFinal(data);
    }

    private static Cipher cipherInit(byte[] key, int cipherValue) throws Exception {
        /* 生成一个可信任的随机数源**/
        SecureRandom sr = new SecureRandom();
        /* 从原始密钥数据创建DESKeySpec对象**/
        DESKeySpec dks = new DESKeySpec(key);
        /* 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象**/
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(dks);
        Security.addProvider(new BouncyCastleProvider());

        /* Cipher对象实际完成加密或解密操作**/
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS7Padding", "BC");
        /*用密钥初始化Cipher对象**/
        cipher.init(cipherValue, secretKey, sr);

        return cipher;
    }
}
