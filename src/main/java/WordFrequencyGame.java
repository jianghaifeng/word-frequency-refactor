import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String BLANK_SPACE_REGEX = "\\s+";
    private static final String SPACE_DELIMITER = " ";

    public String getResult(String sentence) {
        if (sentence.split(BLANK_SPACE_REGEX).length == 1) {
            return sentence + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList = calculateWordFrequency(sentence);

                return formatWordFrequencyResult(wordInfoList);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(BLANK_SPACE_REGEX));
        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : new HashSet<>(words)) {
            int count = Collections.frequency(words, word);
            wordInfoList.add(new WordInfo(word, count));
        }
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        return wordInfoList;
    }

    private String formatWordFrequencyResult(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo wordInfo : wordInfoList) {
            String line = wordInfo.getValue() + SPACE_DELIMITER + wordInfo.getWordCount();
            joiner.add(line);
        }
        return joiner.toString();
    }
}
