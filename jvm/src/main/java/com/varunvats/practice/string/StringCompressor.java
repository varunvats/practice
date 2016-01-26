package com.varunvats.practice.string;

public class StringCompressor {

    private static StringCompressor ourInstance = new StringCompressor();

    public static StringCompressor getInstance() {
        return ourInstance;
    }

    private StringCompressor() {
    }

    public static String compress(String uncompressed) {
        if (uncompressed == null)
            throw new IllegalArgumentException("Cannot compress null strings");
        StringBuilder compressed = new StringBuilder();
        final int length = uncompressed.length();
        for (int i = 0; i < length; ) {
            char ch = uncompressed.charAt(i);
            int count = 1;
            while (++i < length && uncompressed.charAt(i) == ch)
                ++count;
            compressed.append(ch);
            compressed.append(count);
        }
        return compressed.toString();
    }
}
