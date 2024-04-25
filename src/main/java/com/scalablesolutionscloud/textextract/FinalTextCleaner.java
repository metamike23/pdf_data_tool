package com.scalablesolutionscloud.textextract;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for cleaning text strings.
 */
public class FinalTextCleaner {

	/**
     * Constructor for FinalTextCleaner.
     */
    public FinalTextCleaner() {
    }

    /**
     * Processes an array of strings by applying multiple checks.
     *
     * @param inputs the array of strings to process
     * @return an array of cleaned strings
     */
    public String[] processStrings(String[] inputs) {
        List<String> processedStrings = new ArrayList<>();
        for (String input : inputs) {
        	if(input == null) {
        		continue;
        	}
            String resultOne = checkOne(input);
            String resultTwo = checkTwo(resultOne); // Apply checkTwo on the result of checkOne
            
            // Only add the string if it is not empty after being processed
            if (!resultTwo.isEmpty()) {
                processedStrings.add(resultTwo);
            }
        }
        return processedStrings.toArray(new String[0]);
    }

    /**
     * Checks and filters strings based on a set of criteria.
     *
     * @param input the input string to process
     * @return the processed string
     */
    public String checkOne(String input) {
        String[] tokens = input.split("\\s+");
        
        StringBuilder finalString = new StringBuilder();
        for (String token : tokens) {
            long specialCharCount = token.chars()
                .filter(c -> !Character.isLetterOrDigit(c))
                .count();
            
            if (specialCharCount <= 3) {
                if (finalString.length() > 0) {
                    finalString.append(" ");
                }
                finalString.append(token);
            }
        }
        
        return finalString.toString();
    }

    /**
     * Applies additional checks and filters to strings.
     *
     * @param input the input string to process
     * @return the processed string
     */
    public String checkTwo(String input) {
        String[] tokens = input.split("\\s+");
        
        StringBuilder finalString = new StringBuilder();
        for (String token : tokens) {
            long specialCharCount = token.chars()
                .filter(c -> !Character.isLetterOrDigit(c))
                .count();
            long nonSpecialCharCount = token.length() - specialCharCount;

            if (specialCharCount < nonSpecialCharCount) {
                if (finalString.length() > 0) {
                    finalString.append(" ");
                }
                finalString.append(token);
            }
        }
        
        return finalString.toString();
    }

    
}
