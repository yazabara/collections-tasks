package org.zabara.collectionstasks.texttask.version2014;

import org.zabara.collectionstasks.texttask.comparators.WordCountsComparator;
import org.zabara.collectionstasks.texttask.comparators.WordSizeComparator;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Yaroslav on 13.02.14.
 */
public class StaticTextParserImpl implements StaticTextParser {

	private static Logger logger = Logger.getLogger(StaticTextParserImpl.class.toString());

	@Override
	public Map<String, Integer> getWordsCounts(Reader reader) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(reader);
		Map<String, Integer> wordsInfo = new HashMap<String, Integer>();

		String line = bufferedReader.readLine();
		while (line != null) {
			parseLine(line, wordsInfo);
			line = bufferedReader.readLine();
		}
		//прочитали - выдали инфу
		wordsInfo.remove("");
		return wordsInfo;
	}

	private void parseLine(String line, Map<String, Integer> wordsInfo) {
		String[] words = line.split("\\s+");
		for (String word : words) {
			//для каждого слова обновляем инфу
			Integer cutCount = wordsInfo.get(word.toLowerCase()) == null ? 1 : wordsInfo.get(word.toLowerCase()) + 1;
			wordsInfo.put(word.toLowerCase(), cutCount);
		}
	}

	@Override
	public void parseMostPopularWords(Map<String, Integer> wordsInfo, OutputStreamWriter outputStreamWriter, int count) throws IOException {
		checkInput(wordsInfo, outputStreamWriter);
		List list = new ArrayList(wordsInfo.entrySet());
		Collections.sort(list, new WordCountsComparator());
		// Находит десять наиболее распространенных слов
		outputStreamWriter.write("Counts statistic \n");
		for (int i = 0; i < count && i < list.size(); i++) {
			Map.Entry<String, Integer> entrySet = (Map.Entry<String, Integer>) list.get(i);
			outputStreamWriter.write((i + 1) + ") word: " + entrySet.getKey() + " , counts: " + entrySet.getValue() + "\n");

		}
	}

	@Override
	public void parseBiggerWords(Map<String, Integer> wordsInfo, OutputStreamWriter outputStreamWriter) throws IOException {
		checkInput(wordsInfo, outputStreamWriter);
		List list = new ArrayList(wordsInfo.entrySet());
		Collections.sort(list, new WordSizeComparator());
		outputStreamWriter.write("The biggest words: \n");
		parseWidthWords(list, 1, 0, outputStreamWriter);

	}

	@Override
	public void parseMinWords(Map<String, Integer> wordsInfo, OutputStreamWriter outputStreamWriter) throws IOException {
		checkInput(wordsInfo, outputStreamWriter);
		List list = new ArrayList(wordsInfo.entrySet());
		Collections.sort(list, new WordSizeComparator());
		outputStreamWriter.write("The small words: \n");
		parseWidthWords(list, -1, list.size() - 1, outputStreamWriter);
	}

	private void parseWidthWords(List list, int increment, int firstValue, OutputStreamWriter outputStreamWriter) throws IOException {
		int index = 0;
		Map.Entry<String, Integer> smallest = (Map.Entry<String, Integer>) list.get(firstValue);

		int size = smallest.getKey().length();

		for (int i = firstValue; i < list.size() && i >= 0; i += increment) {
			Map.Entry<String, Integer> entrySet = (Map.Entry<String, Integer>) list.get(i);
			if (entrySet.getKey().length() != size) {
				break;
			}
			outputStreamWriter.write(index + ")word: " + entrySet.getKey() + ", size :" + entrySet.getKey().length() + ", counts:" + entrySet.getValue() + "\n");
			index++;
		}
	}

	private void checkInput(Map<String, Integer> wordsInfo, OutputStreamWriter outputStreamWriter) {
		if (wordsInfo == null) {
			throw new NullPointerException("wordsInfo is null");
		}

		if (outputStreamWriter == null) {
			throw new NullPointerException("outputStreamWriter is null");
		}
	}
}
