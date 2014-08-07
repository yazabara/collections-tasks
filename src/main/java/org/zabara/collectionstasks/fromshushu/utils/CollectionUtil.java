/*
 * Copyright (c) EPAM Systems. All Rights Reserved.
 * @author Alexander_Shushunov@epam.com
 */
package org.zabara.collectionstasks.fromshushu.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CollectionUtil {

    private CollectionUtil() {
    }
    
    public static <K> Map<K, Integer> uniomCounterMap(Map<? extends K, Integer> first, Map<? extends K, Integer> second) {
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

	public static <K, V> Map<K, V> toMap(Collection<? extends Map.Entry<? extends K, ? extends V>> collection) {
		Map<K, V> ret = new HashMap<K, V>();
		for (Map.Entry<? extends K, ? extends V> entry : collection) {
			ret.put(entry.getKey(), entry.getValue());
		}
		return ret;
	}

}
