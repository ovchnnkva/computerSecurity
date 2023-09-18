package lab3.part1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class Lab3p1 {

    private PolynomialHash polynomialHash;
    private char[] testText1;

    @Before
    public void init() {
        polynomialHash = new PolynomialHash();
        testText1 = "hello, world!".toCharArray();
    }

    @Test
    public void testText1() {
        log.info(String.valueOf(polynomialHash.hash(testText1)));
    }

}
