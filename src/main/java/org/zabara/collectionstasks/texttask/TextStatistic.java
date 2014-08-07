package org.zabara.collectionstasks.texttask;

import org.zabara.collectionstasks.texttask.comparators.WordCountsComparator;
import org.zabara.collectionstasks.texttask.comparators.WordSizeComparator;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 Задан английский текст. Выделить отдельные слова и для каждого посчитать частоту встречаемости.
 Слова, отличающиеся регистром, считать одинаковыми
 */
@Deprecated
public class TextStatistic implements TextTask{

    private static String DEFAULT_OUTPUT_FILE_NAME = "outputInfo.txt";
    private static Logger logger = Logger.getLogger(TextStatistic.class.toString());
    //информация по словам
    Map<String,Integer> wordsInfo = new HashMap<String, Integer>();

    @Override
    @Deprecated
    public  Map<String,Integer> getWordsInfo(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        while (line != null){
            parseLine(line);
            line = bufferedReader.readLine();
        }

        bufferedReader.close();
        fileReader.close();

        //прочитали - выдали инфу
        return wordsInfo;
    }

    private void parseLine(String line){
        String[] words = line.split("\\s+");
        for (String word : words) {
            //для каждого слова обновляем инфу
            Integer cutCount = wordsInfo.get(word.toLowerCase()) == null ? 1 : wordsInfo.get(word.toLowerCase()) + 1;
            wordsInfo.put(word.toLowerCase(),cutCount);
        }
    }

    /**
     *
     * @param results
     * @throws IOException
     */


    @Override
    @Deprecated
    public void writeResults(Map<String, Integer> results) throws IOException {
        //удаляем пустой
        results.remove("");

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        String outputFileName = DEFAULT_OUTPUT_FILE_NAME;

        System.out.println("Write results to file? (Y/N)");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try {
            s = bufferRead.readLine();
        } catch (Exception ex) {
            logger.log(Level.INFO, "Error input - default output is console.");
        }


        if (s.equals("Y") || s.equals("y")) {
            outputStreamWriter = new FileWriter(outputFileName);
        }

        List list = new ArrayList(results.entrySet());

        Collections.sort(list, new WordCountsComparator());
        // Находит десять наиболее распространенных слов
        int count = 10;
        outputStreamWriter.write("Counts statistic \n");

        for (int i = 0; i < count && i < list.size(); i++) {
            Map.Entry<String, Integer> entrySet = (Map.Entry<String, Integer>) list.get(i);
            outputStreamWriter.write((i+1) + ") word: " + entrySet.getKey() + " , counts: " + entrySet.getValue() + "\n");

        }

        //используется как минимум десять и десять самых длинных слов в введенном тексте .
        Collections.sort(list, new WordSizeComparator());
        Map.Entry<String, Integer> biggest = (Map.Entry<String, Integer>) list.get(0);
        outputStreamWriter.write("The biggest words: \n");

        int maxSize = biggest.getKey().length();
        int index = 1;
        for (Object entry : list) {
            Map.Entry<String, Integer> entrySet = (Map.Entry<String, Integer>) entry;
            if (entrySet.getKey().length() != maxSize) {
                break;
            }
            outputStreamWriter.write(index + ") word: " + entrySet.getKey() + ", size :" + entrySet.getKey().length() + ", counts:" + entrySet.getValue() + "\n");
            index++;
        }

        index = 0;
        Map.Entry<String, Integer> smallest = (Map.Entry<String, Integer>) list.get(list.size() - 1);
        outputStreamWriter.write("The smallest word: \n");
        int minSize = smallest.getKey().length();
        for (int i = list.size() - 1; i > 0; i--) {
            Map.Entry<String, Integer> entrySet = (Map.Entry<String, Integer>) list.get(i);
            if (entrySet.getKey().length() != minSize) {
                break;
            }
            outputStreamWriter.write(index + ")word: " + entrySet.getKey() + ", size :" + entrySet.getKey().length() + ", counts:" + entrySet.getValue() + "\n");
            index++;
        }

        outputStreamWriter.close();
    }
}
