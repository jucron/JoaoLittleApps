package com.joao_renault.general_store;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneralStoreTest {

    @Test
    public void test () {
        assertEquals("spoon",checkPlural("spoons"));
        assertEquals("cup",checkPlural("cups"));
        assertEquals("broom",checkPlural("brooms"));
        assertEquals("broom",checkPlural("broom"));
        assertEquals("cup",checkPlural("cup"));
    }

    public String checkPlural (String str) {
        if (str.charAt(str.length() - 1) == 's') {
            return str.substring(0,str.length()-1);
        }
        return str;
    }
}