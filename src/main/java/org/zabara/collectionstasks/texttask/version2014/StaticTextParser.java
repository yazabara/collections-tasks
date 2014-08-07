package org.zabara.collectionstasks.texttask.version2014;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Map;

/**
 * Created by Yaroslav on 13.02.14.
 */
public interface StaticTextParser {

    Map<String,Integer> getWordsCounts(Reader reader) throws Exception;

    public void parseMostPopularWords(Map<String,Integer> wordsInfo, OutputStreamWriter outputStreamWriter, int count) throws IOException;

    public void parseBiggerWords(Map<String,Integer> wordsInfo, OutputStreamWriter outputStreamWriter) throws IOException;

    public void parseMinWords(Map<String,Integer> wordsInfo, OutputStreamWriter outputStreamWriter) throws IOException;
}
