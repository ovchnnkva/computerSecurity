package lab3.part2;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

@Slf4j
public class Lab3p2 {

    private char[] testText1;
    private char[] testText2;
    private AlghoritmSHA256 alghoritmSHA256;

    @Before
    public void init(){
        alghoritmSHA256 = new AlghoritmSHA256();
        testText1 = "hello world".toCharArray();
        testText2 = "hello zorld".toCharArray();
    }

    @Test
    public void testSHA256() {
        log.info(alghoritmSHA256.hash(testText1));
        log.info(alghoritmSHA256.hash(testText2));


    }
}
