package lab1.part1;

public class CaesarsСipher {
    private static final int SHIFT = 15;

    /**
     * Расшифровывание текста
     * @param text зашифрованый текст
     * @return расшифрованый текст
     */
    public String decrypt(String text) {
        return text.chars()
                .mapToObj(this::decryptAlghorithm)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * Зашифровывание текста
     * @param text исходный текст
     * @return зашифрованный текст
     */
    public String encrypt(String text) {
        return text.chars()
                .mapToObj(this::encryptAlgorithm)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * Алгоритм шифравания символа
     * @param character символ для шифрования
     * @return зашифрованный символ
     */
    private int encryptAlgorithm(int character) {
        int newCharacter = character;

        if(character >= 'a' && character <= 'z') {
            if (character + SHIFT > 'z') {
                newCharacter = character + SHIFT - 26;
            } else {
                newCharacter = character + SHIFT;
            }
        }

        return newCharacter;
    }

    /**
     * Алгоритм расшифровки символа
     * @param character символ для расшифровки
     * @return расшифрованый символ
     */
    private int decryptAlghorithm(int character) {
        int newCharacter = character;

        if(character >= 'a' && character <= 'z') {
            if (character - SHIFT + 26 > 'z') {
                newCharacter = character - SHIFT;
            } else {
                newCharacter = character - SHIFT + 26;
            }
        }

        return newCharacter;
    }

}
