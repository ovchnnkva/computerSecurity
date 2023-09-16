package lab3.part1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class Lab3p1 {

    private PolynomialHash polynomialHash;
    private char[] testText1;
    private char[] testText2;

    @Before
    public void init() {
        polynomialHash = new PolynomialHash();
        testText1 = "hello, world!".toCharArray();
        testText2 = "iello, world!".toCharArray();
    }

    @Test
    public void testText1() {
        log.info(String.valueOf(polynomialHash.hash(testText1)));

        long sum = 0;
        for(int i = 0; i<testText1.length; i++){
            sum += testText1[i];
        }
        log.info(Long.toBinaryString(sum));
        log.info(Long.toBinaryString(polynomialHash.hash(testText1)));
    }

    @Test
    public void testText2() {
        log.info(String.valueOf(polynomialHash.hash(testText2)));

        long sum = 0;
        for(int i = 0; i<testText2.length; i++){
            sum += testText2[i];
        }
        log.info(Long.toBinaryString(sum));
        log.info(Long.toBinaryString(polynomialHash.hash(testText2)));
    }
}
