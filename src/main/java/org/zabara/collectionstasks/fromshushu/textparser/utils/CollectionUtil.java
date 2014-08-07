/*
 * Copyright (c) EPAM Systems. All Rights Reserved.
 * @author Alexander_Shushunov@epam.com
 */
package org.zabara.collectionstasks.fromshushu.textparser.utils;

import java.util.HashMap;
import java.util.Map;

public final class CollectionUtil {

    private CollectionUtil() {
    }
    
    public static <K> Map<K, Integer> uniomCounterMap(Map<K, Integer> first, Map<K, Integer> second) {
        Map<K, Integer> ret = new HashMap<K, Integer>(first);
        for (K key : second.keySet()) {
            if (ret.containsKey(key)) {
                ret.put(key, ret.get(key) + second.get(key));
            } else {
                ret.put(key, second.get(key));
            }
        }
        return ret;
    }
}
