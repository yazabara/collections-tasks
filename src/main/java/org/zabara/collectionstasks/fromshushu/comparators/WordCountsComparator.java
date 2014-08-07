package org.zabara.collectionstasks.fromshushu.comparators;

import java.util.Comparator;
import java.util.Map;


public class WordCountsComparator implements Comparator<Map.Entry<String,Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o2.getValue() - o1.getValue();
    }
}
