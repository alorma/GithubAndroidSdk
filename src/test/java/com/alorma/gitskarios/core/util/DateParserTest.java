package com.alorma.gitskarios.core.util;

import net.danlew.android.joda.JodaTimeAndroid;

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
        dateParser.getMilisFromDateClearDay(null);
    }

    @Test
    public void shouldLaunchANPE_whenPassingBerniesValue() throws Exception {
        String gitHubDate = "2016-02-21T18:22:06Z";

        long result = dateParser.getMilisFromDateClearDay(gitHubDate);

        assertThat(result).isEqualTo(62526000);
    }
}