package lab2.part1;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class Cryptoanalyzer {

    private final List<Character> alphabet;

    public Cryptoanalyzer() {
        alphabet =  List.of('а', 'б', 'в', 'г', 'д', 'е',
                'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м',
                'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф',
                'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь',
                'э', 'ю', 'я');
    }
    public void broot(String text) {
        for(int i = 0; i<34; i++) {
            replaceCharacterInString(i, text);
        }
    }
    public String decrypt(String text) {
        log.info(text);
        AtomicReference<Character> mostRepeated = new AtomicReference<>(' ');
        getMostRepeatedSymbol(text).ifPresent(mostRepeated::set);
        log.info("most repeated: " + mostRepeated.get() + " index: " +alphabet.indexOf(mostRepeated.get()) + " index o: " + alphabet.indexOf('о'));
        int key = getKey(mostRepeated.get());
        log.info("key: " + key);
        return replaceCharacterInString(key, text);
    }

    private String replaceCharacterInString(int key, String text) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i<text.length(); i++) {
            char charForReplace = text.charAt(i);
            if(alphabet.contains(charForReplace)) {
                int indexChar = alphabet.indexOf(charForReplace);
                    if(indexChar - key + alphabet.size() > 32){
                        charForReplace = alphabet.get(indexChar - key);
                    }
                    else {
                        charForReplace = alphabet.get(indexChar - key + alphabet.size());
                    }

            }

            result.append(charForReplace);
        }

        log.info(result.toString());
        return result.toString();
    }

    private int getKey(Character mostRepeatedSymbol) {
        int indexMostRepeated = alphabet.indexOf(mostRepeatedSymbol);
        int indexO = alphabet.indexOf('о');
        return indexMostRepeated > indexO ? indexMostRepeated - indexO : alphabet.size() - indexO + indexMostRepeated;
    }
    private Optional<Character> getMostRepeatedSymbol(String text) {
        return getRepeatsSymbolInText(text.replaceAll(" ", ""))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
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


}
