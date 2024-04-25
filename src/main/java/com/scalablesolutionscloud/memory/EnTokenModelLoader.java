package com.scalablesolutionscloud.memory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Helper class for loading the embedded NLP tokenization model.
 * The model, contained in the file "en-token.bin", is loaded from the classpath.
 */
public class EnTokenModelLoader {
    
    // Input stream for loading the NLP model
    private static InputStream openLpBinStream = EnSentModelLoader.class.getClassLoader().getResourceAsStream("en-token.bin");

    // Private constructor to prevent instantiation
    private EnTokenModelLoader() {
        // This constructor is private to prevent instantiation of this class.
    }

    /**
     * Retrieves the input stream for the loaded NLP tokenization model.
     *
     * @return The input stream for the NLP tokenization model.
     * @throws IOException If the model file "en-token.bin" is not found or cannot be accessed.
     */
    public static InputStream getOpenLpBinStream() throws IOException {
        if (openLpBinStream == null) {
            throw new IOException("en-token.bin file not found");
        }
        return openLpBinStream;
    }
}
