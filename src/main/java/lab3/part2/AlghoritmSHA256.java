package lab3.part2;

import lombok.extern.slf4j.Slf4j;

import javax.sql.rowset.serial.SerialStruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class AlghoritmSHA256 {

    private long h0 = Long.valueOf("01101010000010011110011001100111", 2);
    private long h1 = Long.valueOf("10111011011001111010111010000101", 2);
    private long h2 = Long.valueOf("00111100011011101111001101110010", 2);
    private long h3 = Long.valueOf("10100101010011111111010100111010", 2);
    private long h4 = Long.valueOf("01010001000011100101001001111111", 2);

    /**
     * Основной алгоритм преобразования строки к ключу SHA-256
     * @param text исходный текст
     * @return ключ SHA-256
     */
    public String hash(char[] text) {
        String[] converted = changingIndexesAtEndOfArray(to32bit(fillingUpToMultiplicityOf512(charsToBinary(text))));

        long a = h0;
        long b = h1;
        long c = h2;
        long d = h3;
        long e = h4;

        long f =0L;
        long k = 0L;
        long temp;
        for(int i =0; i < converted.length; i++){
            if((i <=19) ) {
                f = (b & c) | ((~b) & d);
                k = Long.valueOf("5A827999", 16);
            } else if ((i >= 20) && (i <= 39)) {
                f = b ^ c ^ d;
                k = Long.valueOf("6ED9EBA1", 16);
            } else if ((i >= 40) && (i <=59)) {
                f = (b & c) | (b & d) | (c & d);
                k = Long.valueOf("8F1BBCDC", 16);
            } else if ((i >= 60) && (i <=79)) {
                f = b ^ c ^ d;
                k = Long.valueOf("CA62C1D6", 16);
            }

            temp = (a << 5) + f + e + k + Long.valueOf(converted[i], 2);
            e = d;
            d = c;
            c = b << 30;
            b = a;
            a = temp;

            h0 += a;
            h1 += b;
            h2 += c;
            h3 += d;
            h4 += e;;
        }

        return getHexString();
    }

    /**
     * Соединение хешей в строку
     * @return SHA-256 ключ
     */
    private String getHexString() {

        return Long.toHexString(h0) +
                Long.toHexString(h1) +
                Long.toHexString(h2) +
                Long.toHexString(h3) +
                Long.toHexString(h4);
    }

    /**
     * Преобразование символов к бинарным 8-ми битным словам
     * @param text исходный текст
     * @return бинарная строка
     */
    private String charsToBinary(char[] text) {
        StringBuilder binaryText = new StringBuilder();
        for (char c : text) {
            String binarySymbol = Long.toBinaryString(c);
            int fillingUpCount = 8 - binarySymbol.length();
            binaryText.append("0".repeat(Math.max(0, fillingUpCount)));
            binaryText.append(binarySymbol);
            binaryText.append(" ");
        }
        return binaryText.toString();
    }

    /**
     * Заполнение бинарной строки до 512 бит
     * @param text
     * @return 512 битная бинарная строка
     */
    private String fillingUpToMultiplicityOf512(String text) {
        StringBuilder binaryString = new StringBuilder(text);
        binaryString.append("1");
        long length = 512 - text.replaceAll(" ", "").length() - 64;
        for (int i = 0; i < length; i++) {
            int indexLastSpace = binaryString.lastIndexOf(" ");
            if ((binaryString.length() - 1 - indexLastSpace) % 8 == 0) {
                binaryString.append(" ");
            }
            binaryString.append("0");
        }
        return addLengthToBinary(binaryString);
    }

    /**
     * Преобразования 8-ми битных слов к 32-х битным
     * @param text 8-ми битные бинарные слова
     * @return 32-х битные бинарные слова
     */
    private List<String> to32bit(String text) {
        String[] mass8Bit = text.split(" ");
        List<String> mass32bit = new ArrayList<>();
        for (int i = 0; i < mass8Bit.length; i += 4) {
            StringBuilder word = new StringBuilder();
            for (int j = i; j < i + 4; j++) {
                if (j > mass8Bit.length - 1) break;
                word.append(mass8Bit[j]);
            }
            mass32bit.add(word.toString());
        }
        return fillingUpTo64Words(mass32bit);
    }

    /**
     * Дополнение до 64-х 32-ух битных слов
     * @param words список бинарных слов
     * @return список из 64-х бинарных 32-ух битных слов
     */
    private List<String> fillingUpTo64Words(List<String> words) {
        int countNewWords = 64 - words.size();
        for (int i = 0; i < countNewWords; i++) {
            words.add("00000000000000000000000000000000");
        }

        return words;
    }

    /**
     * Циклический сдвиг влево
     * @param binaryString бинарное значение
     * @return бинарное значение, сдинутое влево
     */
    private String[] changingIndexesAtEndOfArray(List<String> binaryString) {
        String[] binaryArray = binaryString.toArray(new String[0]);

        for(int i = 16; i<binaryString.size(); i++) {
            long result = Integer.valueOf(binaryArray[i -3], 2) ^ Integer.valueOf(binaryArray[i -8], 2) ^
                    Integer.valueOf(binaryArray[i -14], 2) ^ Integer.valueOf(binaryArray[i -16], 2);

            binaryArray[i] = fillingUpTo8bit(Long.toBinaryString(result));
        }

        return binaryArray;
    }

    /**
     * Добавление значения длины строки в виде бинарного 64-битного слова
     * @param binaryString строка, к которой добравляется значение длины исходного текста
     * @return бинарное представление длины входной строки
     */
    private String addLengthToBinary(StringBuilder binaryString) {
        StringBuilder binaryLength = new StringBuilder(Integer.toBinaryString(binaryString.length()).replaceAll(" ", ""));
        int countAdditions = 64 - binaryLength.length();

        for (int i = 0; i < countAdditions - 1; i++) {
            int indexLastSpace = binaryString.lastIndexOf(" ");
            if ((binaryString.length() - 1 - indexLastSpace) % 8 == 0) {
                binaryString.append(" ");
            }
            binaryString.append("0");
        }

        return binaryString.append(binaryLength).toString();
    }

    /**
     * Дополнение до 8 бит
     * @param binaryWord бинарное слово
     * @return 8-ми битное бинарное слово
     */
    private String fillingUpTo8bit(String binaryWord){
        StringBuilder resultStrBuilder = new StringBuilder();
        int fillingUpCount = 32 - binaryWord.length();
        resultStrBuilder.append("0".repeat(Math.max(0, fillingUpCount)));
        resultStrBuilder.append(binaryWord);
        return resultStrBuilder.toString();
    }
}

