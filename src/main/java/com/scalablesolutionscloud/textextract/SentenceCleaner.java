package com.scalablesolutionscloud.textextract;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Provides functionality to clean and process text extracted from PDF documents.
 * This class is responsible for removing nonsensical tokens, special characters, and links
 * from the text to prepare it for further analysis or display. It is designed as the first
 * stage in a text cleaning pipeline.
 */
public class SentenceCleaner {
	
	
	/**
     * Replaces characters in the input string if they occur more frequently than the specified threshold.
     * Specifically targets '/', '-' and '_'.
     *
     * @param input     The string to be processed.
     * @param threshold The maximum allowed occurrences of each special character.
     * @return The processed string with excess characters removed, or the original string if under the threshold.
     */
	public String replaceIfMultipleOccurrences(String input, int threshold) {
        int slashCount = 0;
        int dashCount = 0;

        // Count occurrences of '/' and '-' and '_'
        for (char c : input.toCharArray()) {
            if (c == '/') {
                slashCount++;
            } else if (c == '-') {
                dashCount++;
            }
            else if (c == '_') {
            	dashCount++;
            }
        }

        // Check if counts exceed the threshold
        if (slashCount > threshold || dashCount > threshold) {
            return ""; // Return blank space if counts exceed the threshold
        } else {
            return input; // Return original string if counts are within the threshold
        }
    }
	
	
	/**
     * Removes HTTP and HTTPS links from the input string.
     *
     * @param input The string from which links should be removed.
     * @return A string with all recognizable links removed.
     */
	public String removeLinks(String input) {
        StringBuilder result = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (!containsLink(token)) {
                result.append(token).append(" ");
            } else {
                result.append(""); // Replace link-containing token with "empty"
            }
        }
        
        return result.toString().trim(); // Return the string with link-containing tokens replaced with "empty"
    }
    
    private boolean containsLink(String token) {
        return token.startsWith("http://") || token.startsWith("https://");
    }
	
	
    /**
     * Cleans special characters from the input string, focusing on tokens within the string.
     * Any sequence of more than two special characters is trimmed, except for certain allowed combinations
     * like periods or commas followed by quotation marks.
     *
     * @param input The string to clean.
     * @return A string where each token has been individually cleaned of unwanted special characters.
     */
	public String cleanSpecialCharacters(String input) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, " ");

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();

            if (containsAlphanumeric(token)) {
                if (isValidNumericToken(token)) {
                    tokens.add(token); // Preserve valid numeric tokens, including decimals and commas
                } else {
                    token = stripNonAlphabetic(token);
                    if (!token.isEmpty()) { // Add token if it's not empty after stripping
                        tokens.add(token);
                    }
                }
            }
        }
        return concatenateArrayListToString(tokens);
    }
	
	
	/**
     * Checks if a token contains alphanumeric characters.
     *
     * @param token The token to check.
     * @return true if the token contains at least one alphanumeric character, false otherwise.
     */
    private boolean containsAlphanumeric(String token) {
        return token.chars().anyMatch(Character::isLetterOrDigit);
    }

    
    /**
     * Determines if a string token is a valid numeric value, potentially including decimals.
     *
     * @param token The token to evaluate.
     * @return true if the token represents a valid numeric value, false otherwise.
     */
    private boolean isValidNumericToken(String token) {
        // Adjusted to handle numeric tokens with periods or commas correctly
        token = token.replaceAll(",", ""); // Remove commas for validation
        if (token.endsWith(".")) {
            token = token.substring(0, token.length() - 1); // Remove trailing period for validation
        }
        return token.matches("\\d*\\.?\\d+"); // Matches numbers with optional period & decimals
    }

    
    /**
     * Strips non-alphabetic characters from a token, with certain combinations of special characters preserved.
     *
     * @param token The token to strip.
     * @return The token stripped of unwanted characters.
     */
    private String stripNonAlphabetic(String token) {
        StringBuilder cleanedToken = new StringBuilder();
        int consecutiveSpecialCount = 0;
        char lastChar = '\0';

        for (char c : token.toCharArray()) {
            if (!Character.isLetter(c)) {
                consecutiveSpecialCount++;
                if (consecutiveSpecialCount >= 2 && !isPreservedCombination(lastChar, c)) {
                    continue;
                }
            } else {
                consecutiveSpecialCount = 0;
            }
            cleanedToken.append(c);
            lastChar = c;
        }
        return cleanedToken.toString();
    }
    

    /**
     * Determines if a combination of characters should be preserved during the cleaning process.
     *
     * @param firstChar The first character in the combination.
     * @param secondChar The second character in the combination.
     * @return true if the combination is to be preserved, false otherwise.
     */
    private boolean isPreservedCombination(char firstChar, char secondChar) {
        return (firstChar == ',' && secondChar == '"') ||
               (firstChar == '"' && secondChar == ',') ||
               (firstChar == '.' && secondChar == '"') ||
               (firstChar == '"' && secondChar == '.');
    }
    

    /**
     * Removes nonsensical numeric characters that are unlikely to be part of valid text.
     * Numeric characters at the beginning or end of tokens are stripped unless they form part of a valid decimal or date.
     *
     * @param input The string to clean.
     * @return A cleaned string with numeric characters removed from places they do not semantically belong.
     */
	public String cleanNumerics(String input) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, " ");

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();

            // Check if the token begins and ends with specific characters based on its starting character
            if (token.matches("[a-zA-Z].*")) {
                // If the token starts with an alphabetic character
                if (token.matches("[a-zA-Z].*[a-zA-Z\\p{Punct}]+$")) {
                    tokens.add(token);
                } else {
                    // Strip leading or trailing numeric characters if they don't represent a real number
                    if (!isRealNumber(token)) {
                        token = token.replaceAll("^\\d+|\\d+$", "");
                    }
                    tokens.add(token);
                }
            } else if (token.matches("\\d.*")) {
                // If the token starts with a numeric character
                if (token.matches("\\d.*[.,!?;:]$|\\d+")) {
                    tokens.add(token);
                } else {
                    // Strip leading or trailing numeric characters if they don't represent a real number
                    if (!isRealNumber(token)) {
                        token = token.replaceAll("^\\d+|\\d+$", "");
                    }
                    tokens.add(token);
                }
            }
        }
        return concatenateArrayListToString(tokens);
    }

	
	/**
	 * Checks if a given token represents a real number.
	 *
	 * @param token The token to be checked.
	 * @return {@code true} if the token represents a real number, {@code false} otherwise.
	 */
    private boolean isRealNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    
    /**
     * Concatenates elements of a list into a single string, with elements separated by spaces.
     *
     * @param arrayList The list of strings to concatenate.
     * @return A single string composed of the list elements.
     */
    private String concatenateArrayListToString(List<String> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        
        for(int i = 0; i < arrayList.size(); i++) {
        	if(i == arrayList.size() - 1) {
        		stringBuilder.append(arrayList.get(i));
        	}
        	else {
        		stringBuilder.append(arrayList.get(i) + " ");
        	}
        }
        
        return stringBuilder.toString();
    }
    
}
