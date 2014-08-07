package org.zabara.collectionstasks.texttask.comparators;

import java.util.Comparator;
import java.util.Map;

public class WordSizeComparator implements Comparator<Map.Entry<String,Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return -new Integer(o1.getKey().length()).compareTo(o2.getKey().length());
    }
}
