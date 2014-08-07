/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zabara.collectionstasks.fromshushu.textparser;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public interface TextParser {

    Map<String, Integer> getWordsCounts(Reader reader) throws IOException, IllegalArgumentException;
    
    Map<String, Integer> getPopularWordsCounts(Reader reader, int count) throws IOException, IllegalArgumentException;
}
