package lab3.part1;

/**
 * Формула прямого полиномиального хеша - hash(s0..n-1) = (s0 + pk1 + p2k2 +… + pnsn) mod p,
 * где p - фиксированное простое число
 * s[i] - код символа
 *k[i] - произвольное число, больше размера алфавита
 */
public class PolynomialHash {
    private final int p = 50000;
    private int k = 36;
    public long hash(char[] text) {
        int h = text[0];
        for(int i = 1; i < text.length; i++) {
            int s = text[i];
            h += (k * s);
            k *= k;
        }
        return h % p;
    }


}
