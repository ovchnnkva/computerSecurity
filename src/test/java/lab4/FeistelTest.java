package lab4;

import org.junit.Before;
import org.junit.Test;

public class FeistelTest {
    private FeistelNetwork feistelNetwork;
    @Before
    public void init() {
        feistelNetwork = new FeistelNetwork();
    }

    @Test
    public void test1() {
        print(100, "helpword", "key for feistel ");
    }

    @Test
    public void test2() {
        print(1, "baracuda", "feistel cipher 1");
    }

    @Test
    public void test3() {
        print(2, "piroman1", "password for my1");
    }

    @Test
    public void test4() {
        print(3, "privet11", "hotel california");
    }

    @Test
    public void test5() {
        print(4, "hellosam", "abracadabra arba");
    }

    @Test
    public void test6() {
        print(5, "robocop1", "reboot you servr");
    }

    @Test
    public void test7() {
        print(6, "stimpank", "rostov ondon 43");
    }

    private void print(int studentNum, String message, String key) {
        feistelNetwork.generate(studentNum);
        System.out.printf("==========\nисходные данные(2x32бит): \"%s\"\n",message);
        System.out.printf("ключ шифрования(128 бит): \"%s\"\n",key);
        String cryptMsg = feistelNetwork.crypt(message, key);
        System.out.println("зашифрованные данные: "+ cryptMsg);
        System.out.println("расшифрованные данные: "+ feistelNetwork.decrypt(cryptMsg, key));
    }
}
