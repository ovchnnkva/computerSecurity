package lab1.part1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Slf4j
public class Lab1p1Test {
    private String testTextDecrypt;
    private String testTextEncrypt;

    private CaesarsСipher caesarsСipher;

    @Before
    public void init() {
        testTextDecrypt = "hello world!";

        testTextEncrypt = "wtaad ldgas!";

        caesarsСipher = new CaesarsСipher();
    }

    @Test
    public void testEncrypt() {
        log.info(caesarsСipher.encrypt(testTextDecrypt));
        assertEquals(caesarsСipher.encrypt(testTextDecrypt), testTextEncrypt);
    }

    @Test
    public void testDecrypt(){
        log.info(caesarsСipher.decrypt(testTextEncrypt));
        assertEquals(caesarsСipher.decrypt(testTextEncrypt), testTextDecrypt);
    }
}
