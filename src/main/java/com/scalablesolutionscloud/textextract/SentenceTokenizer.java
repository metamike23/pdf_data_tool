package com.scalablesolutionscloud.textextract;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.scalablesolutionscloud.memory.EnSentModelLoader;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

/**
 * This class provides static methods for tokenizing sentences using an embedded NLP model.
 * The model is loaded as an InputStream using the EnSentModelLoader class.
 * The en-sent.bin model is loaded into the SentenceDetectorME object for sentence detection.
 */
public class SentenceTokenizer {

    private static SentenceDetectorME sentenceDetector;

    static {
        try {
            InputStream modelIn = EnSentModelLoader.getOpenLpBinStream();
            if (modelIn == null) {
                throw new IOException("Model file 'en-sent.bin' not found in classpath");
            }
            SentenceModel model = new SentenceModel(modelIn);
            sentenceDetector = new SentenceDetectorME(model);
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    /**
     * Tokenizes a list of texts into sentences.
     *
     * @param texts the list of texts to be tokenized
     * @return a list of sentences extracted from the input texts
     * @throws IOException if an I/O error occurs during sentence tokenization
     */
    public static List<String> tokenizeSentences(List<String> texts) throws IOException {
        if (sentenceDetector == null) {
            throw new IOException("SentenceDetectorME not initialized. Ensure model loading was successful.");
        }
        String combinedText = String.join(" ", texts);
        String[] detectedSentences = sentenceDetector.sentDetect(combinedText);
        return Arrays.asList(detectedSentences);
    }
}
