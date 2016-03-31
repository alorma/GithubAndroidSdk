package com.alorma.gitskarios.core.util;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DateParserTest {

    DateParser dateParser = new DateParser();

    @Before
    public void init(){
        dateParser = new DateParser();
    }

    @Test(expected = NullPointerException.class)
    public void shouldLaunchANPE_whenPassingNull() throws Exception {
        dateParser.getMillisFromDateClearDay(null);
    }

    @Test
    public void shouldGiveGoodValue_whenPassingBerniesValue() throws Exception {
        String berniesDate = "2016-02-21T18:22:06Z";

        long result = dateParser.getMillisFromDateClearDay(berniesDate);

        assertThat(result).isEqualTo(62526000);
    }
}