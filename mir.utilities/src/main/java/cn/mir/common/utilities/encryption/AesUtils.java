package cn.mir.common.utilities.encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;

/**
 * AES加解密帮助类
 * <p>Create time: 2018/11/27 17:18</p>
 *
 * @author 周光兵
 **/
public class AesUtils {
    /**
     * 初始化完成标识
     */
    private static boolean initialized = false;

    /**
     * AES解密
     *
     * @param content 密文
     * @return 解密后的结果
     */
    public byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");

            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIv(ivByte));

            // 返回解密后的结果
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 初始化
     */
    private static void initialize() {
        if (initialized) {
            return;
        }

        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }

    /**
     * 生成加密参数
     *
     * @param iv 向量
     * @return 加密参数
     * @throws Exception 初始化异常
     */
    private static AlgorithmParameters generateIv(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }
}