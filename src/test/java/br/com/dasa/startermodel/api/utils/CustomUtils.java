package br.com.dasa.startermodel.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomUtils {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}