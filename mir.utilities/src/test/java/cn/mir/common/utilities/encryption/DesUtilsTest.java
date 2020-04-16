package cn.mir.common.utilities.encryption;

import org.junit.Test;

import static org.junit.Assert.*;

public class DesUtilsTest {
    @Test
    public void executionTest() throws Exception {
        String key = "develope";

        String clearText = "This is me.";

        String encryptedValue = DesUtils.encrypt(clearText, key);
        System.out.println(encryptedValue);

        String decryptedValue = DesUtils.decrypt(encryptedValue, key);
        System.out.println(decryptedValue);

        assertEquals(clearText, decryptedValue);
    }
}