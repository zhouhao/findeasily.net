package net.findeasily.website.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToastrUtilsTest {

    @Test
    public void info() {
        assertEquals("toastr.info('Hello');", ToastrUtils.info("Hello"));
    }
}