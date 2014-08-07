/*
 * Copyright (c) EPAM Systems. All Rights Reserved.
 * @author Alexander_Shushunov@epam.com
 */
package org.zabara.collectionstasks.texttask.version2014;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TextParserImplTest {

    @Test
    public void testGetWordsCounts() throws Exception {

        TextParser parser = new TextParserImpl();
        try (Reader reader = new InputStreamReader(IOUtils.toInputStream("у у у\nу у у"))) {
            Map<String, Integer> result = parser.getWordsCounts(reader);
            assertThat(result.get("у"), is(6));

        }

    }
}