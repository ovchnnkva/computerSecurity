import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class FrequencyCryptanalyzer {
    /**
     *
     * @param letterFrequencyDecrypted частотности расшифрованных символов
     * @param text зашифрованный текст
     * @return расшифрованный текст
     */
    public String decrypt(Map<Character, Double> letterFrequencyDecrypted, String text){
        //<encrypted, decrypted>
        Map<Character, Character> correspondencesForSymbol = new HashMap<>();
        Map<Character, Double> letterFrequencyEncrypted = getLettersFrequenciesInEncryptedText(text);

        for(Map.Entry<Character, Double> encrypted : letterFrequencyEncrypted.entrySet()) {
            Optional<Character> decrypted = findMinCorrespondence(letterFrequencyDecrypted, encrypted.getValue());
            decrypted.ifPresent(o -> {
                correspondencesForSymbol.put(encrypted.getKey(), decrypted.get());
                letterFrequencyDecrypted.put(decrypted.get(), 1.0);
            });
        }
        log.info(text);
        return replaceCharacterInString(correspondencesForSymbol, text);
    }

    /**
     * Поиск расшифрованного символа с наиболее похожей частотностью
     * @param decrypted частотности расшифрованных символов
     * @param encryptedFrequence частотность зашифрованного символа, для которого производится поиск расшифрованного символа
     * @return наиболее подходящий расшифрованный символ
     */
    private Optional<Character> findMinCorrespondence(Map<Character, Double> decrypted, double encryptedFrequence) {

        Map<Character, Double> correspondences = decrypted.entrySet()
                .stream()
//                .filter(e -> (encryptedFrequence - e.getValue() >= 0.0000))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> Math.abs(encryptedFrequence - entry.getValue())
                ));

        return correspondences.entrySet().stream().min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    /**
     * Замена зашифрованных символов
     * @param correspondence соотношения зашифрованных символов с расшифрованными
     * @param text зашифрованный текст
     * @return расшифрованный текст
     */
    private String replaceCharacterInString(Map<Character, Character> correspondence, String text) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i<text.length(); i++) {
            char charForReplace = text.charAt(i);
            if(correspondence.containsKey(charForReplace)) {
                charForReplace = correspondence.get(charForReplace);
            }
            result.append(charForReplace);
        }

        log.info(result.toString());
        return result.toString();
    }

    /**
     * Поиск частотностей символов в зашифрованом тексте
     * @param text зашифрованный текст
     * @return частотности символов в зашифрованном тексте
     */
    public Map<Character, Double> getLettersFrequenciesInEncryptedText(String text){
        log.info("length: " + delNonLetterCharacters(text).length());

        Map<Character, Integer> repeats = sorted(getRepeatsSymbolInText(delNonLetterCharacters(text)));

        Map<Character, Double> lettersFrequencyPercent = new LinkedHashMap<>();
        repeats.forEach((key1, value1) -> lettersFrequencyPercent.put(key1, getSymbolFrequency(value1, delNonLetterCharacters(text).length())));

        lettersFrequencyPercent.forEach((key, value) -> log.info("symbol: " + key + " count: " + value));
        return lettersFrequencyPercent;
    }

    /**
     * Расчет частотности
     * @param countRepeats частота повторений символа в тексте
     * @param countSymbols общее количество символов
     * @return частотность символа
     */
    private Double getSymbolFrequency(int countRepeats, int countSymbols) {
        return (double) Math.round(countRepeats / (double)countSymbols * 10000.0) / 10000.0;
    }

    /**
     * Удаление из текста небуквенных символов
     * @param text текст для преобразования
     * @return текст, содержащий только буквенные символы
     */
    private String delNonLetterCharacters(String text) {
        return text.replaceAll("[^А-Яа-яЁё]", "");
    }

    /**
     * Получение повторений символа
     * @param text текст, для которого будет определяться повторения символов
     * @return количество повторений для каждого символа
     */
    private Map<Character, Integer> getRepeatsSymbolInText(String text) {
        Map<Character, Integer> repeats = new HashMap<>();

        for(int i = 0; i<text.length();i++) {
            if(repeats.containsKey(text.charAt(i))) {
                repeats.put(text.charAt(i), repeats.get(text.charAt(i)) + 1);
            } else {
                repeats.put(text.charAt(i), 1);
            }
        }

        return repeats;
    }

    /**
     * Сортировка коллекции частотности для удобного вывода в консоль
     * @param nonSortedMap несортированная коллекция частотности
     * @return сортированная коллекция
     */
    private Map<Character, Integer> sorted(Map<Character, Integer> nonSortedMap) {
        Map<Character, Integer> result = new LinkedHashMap<>();
        nonSortedMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(e ->result.put(e.getKey(), e.getValue()));

        return result;
    }
}
