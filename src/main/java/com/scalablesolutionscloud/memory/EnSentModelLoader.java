package com.scalablesolutionscloud.memory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Helper class for loading embedded resources.
 * The NLP model for tokenizing sentences is loaded as a classpath resource.
 * The model file, en-sent.bin, is located in the res folder.
 * This class provides access to the model as an InputStream.
 */
public class EnSentModelLoader {

	 // Input stream for loading the NLP model
    private static InputStream openLpBinStream = EnSentModelLoader.class.getClassLoader().getResourceAsStream("en-sent.bin");

    // Private constructor to prevent instantiation
    private EnSentModelLoader() {
       
    }

    /**
     * Retrieves the input stream for the loaded NLP model.
     *
     * @return the input stream for the NLP model
     * @throws IOException if the model file is not found or cannot be accessed
     */
    public static InputStream getOpenLpBinStream() throws IOException {
        if (openLpBinStream == null) {
            throw new IOException("en-sent.bin file not found");
        }
        return openLpBinStream;
    }
}
