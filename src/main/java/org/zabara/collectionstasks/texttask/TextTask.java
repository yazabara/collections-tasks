package org.zabara.collectionstasks.texttask;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Yaroslav on 12.02.14.
 *
 * Задан английский текст. Выделить отдельные слова и для каждого посчитать частоту встречаемости.
 Слова, отличающиеся регистром, считать одинаковыми
 */
@Deprecated
public interface TextTask {

    @Deprecated
    public Map<String,Integer> getWordsInfo(String fileName) throws IOException;

    @Deprecated
    public void writeResults(Map<String, Integer> results) throws IOException;

}
