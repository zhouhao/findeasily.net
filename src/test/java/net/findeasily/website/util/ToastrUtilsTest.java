package net.findeasily.website.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToastrUtilsTest {

    @Test
    public void info() {
        assertEquals("toastr.info('Hello');", ToastrUtils.info("Hello"));
    }

    @Test
    public void BuildWithSingleQuotation() {
        assertEquals("toastr.info('Hello\\'s World');", ToastrUtils.info("Hello's World"));
    }

    @Test
    public void BuildWithDoubleQuotation() {
        assertEquals("toastr.info('Hello\"s World');", ToastrUtils.info("Hello\"s World"));
    }
}