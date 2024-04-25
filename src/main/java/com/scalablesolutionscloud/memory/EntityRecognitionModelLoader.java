package com.scalablesolutionscloud.memory;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class provides static methods to load embedded resources for entity recognition.
 * It loads various NLP models for recognizing entities such as money, dates, persons, percentages, organizations,
 * locations, and times. These models are accessed as InputStream objects through the provided getters.
 */
public class EntityRecognitionModelLoader {

    // Input streams for loading the NLP models
    private static InputStream moneyRecognizerStream = EntityRecognitionModelLoader.class.getClassLoader().getResourceAsStream("en-ner-money.bin");
    private static InputStream dateRecognizerStream = EntityRecognitionModelLoader.class.getClassLoader().getResourceAsStream("en-ner-date.bin");
    private static InputStream personRecognizerStream = EntityRecognitionModelLoader.class.getClassLoader().getResourceAsStream("en-ner-person.bin");
    private static InputStream percentageRecognizerStream = EntityRecognitionModelLoader.class.getClassLoader().getResourceAsStream("en-ner-percentage.bin");
    private static InputStream organizationRecognizerStream = EntityRecognitionModelLoader.class.getClassLoader().getResourceAsStream("en-ner-organization.bin");
    private static InputStream locationRecognizerStream = EntityRecognitionModelLoader.class.getClassLoader().getResourceAsStream("en-ner-location.bin");
    private static InputStream timeRecognizerStream = EntityRecognitionModelLoader.class.getClassLoader().getResourceAsStream("en-ner-time.bin");

    // Private constructor to prevent instantiation
    private EntityRecognitionModelLoader() {
        // This constructor is private to prevent instantiation of this class.
    }

    /**
     * Retrieves the input stream for the money recognizer NLP model.
     *
     * @return the input stream for the money recognizer NLP model
     * @throws IOException if the model file is not found or cannot be accessed
     */
    public static InputStream getMoneyRecognizer() throws IOException {
        if (moneyRecognizerStream == null) {
            throw new IOException("en-ner-money.bin file not found");
        }
        return moneyRecognizerStream;
    }

    /**
     * Retrieves the input stream for the date recognizer NLP model.
     *
     * @return the input stream for the date recognizer NLP model
     * @throws IOException if the model file is not found or cannot be accessed
     */
    public static InputStream getDateRecognizer() throws IOException {
        if (dateRecognizerStream == null) {
            throw new IOException("en-ner-date.bin file not found");
        }
        return dateRecognizerStream;
    }

    /**
     * Retrieves the input stream for the person recognizer NLP model.
     *
     * @return the input stream for the person recognizer NLP model
     * @throws IOException if the model file is not found or cannot be accessed
     */
    public static InputStream getPersonRecognizer() throws IOException {
        if (personRecognizerStream == null) {
            throw new IOException("en-ner-person.bin file not found");
        }
        return personRecognizerStream;
    }

    /**
     * Retrieves the input stream for the percentage recognizer NLP model.
     *
     * @return the input stream for the percentage recognizer NLP model
     * @throws IOException if the model file is not found or cannot be accessed
     */
    public static InputStream getPercentageRecognizer() throws IOException {
        if (percentageRecognizerStream == null) {
            throw new IOException("en-ner-percentage.bin file not found");
        }
        return percentageRecognizerStream;
    }

    /**
     * Retrieves the input stream for the organization recognizer NLP model.
     *
     * @return the input stream for the organization recognizer NLP model
     * @throws IOException if the model file is not found or cannot be accessed
     */
    public static InputStream getOrganizationRecognizer() throws IOException {
        if (organizationRecognizerStream == null) {
            throw new IOException("en-ner-organization.bin file not found");
        }
        return organizationRecognizerStream;
    }

    /**
     * Retrieves the input stream for the location recognizer NLP model.
     *
     * @return the input stream for the location recognizer NLP model
     * @throws IOException if the model file is not found or cannot be accessed
     */
    public static InputStream getLocationRecognizer() throws IOException {
        if (locationRecognizerStream == null) {
            throw new IOException("en-ner-location.bin file not found");
        }
        return locationRecognizerStream;
    }

    /**
     * Retrieves the input stream for the time recognizer NLP model.
     *
     * @return the input stream for the time recognizer NLP model
     * @throws IOException if the model file is not found or cannot be accessed
     */
    public static InputStream getTimeRecognizer() throws IOException {
        if (timeRecognizerStream == null) {
            throw new IOException("en-ner-time.bin file not found");
        }
        return timeRecognizerStream;
    }
}
