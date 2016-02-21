package com.alorma.gitskarios.core.util;

import org.junit.Test;

public class DateParserTest {

    @Test(expected = NullPointerException.class)
    public void testGetMilisFromDateClearDay() throws Exception {
        DateParser dateParser = new DateParser();

        long result = dateParser.getMilisFromDateClearDay(null);
    }
}