package com.scalablesolutionscloud.memory;

import java.io.IOException;
import java.io.InputStream;

public class LoadTester {

 
    /*
     * public static void main(String[] args) {
        try {
            testModelLoading();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */
    public static void testModelLoading() throws IOException {
        System.out.println("Testing model loading...");
        testModelLoading("Money", EntityRecognitionModelLoader.getMoneyRecognizer());
        testModelLoading("Date", EntityRecognitionModelLoader.getDateRecognizer());
        testModelLoading("Person", EntityRecognitionModelLoader.getPersonRecognizer());
        testModelLoading("Percentage", EntityRecognitionModelLoader.getPercentageRecognizer());
        testModelLoading("Organization", EntityRecognitionModelLoader.getOrganizationRecognizer());
        testModelLoading("Location", EntityRecognitionModelLoader.getLocationRecognizer());
        testModelLoading("Time", EntityRecognitionModelLoader.getTimeRecognizer());
    }

    private static void testModelLoading(String modelName, InputStream inputStream) {
        if (inputStream != null) {
            System.out.println(modelName + " model loaded successfully.");
        } else {
            System.out.println("Error loading " + modelName + " model.");
        }
    }
}

