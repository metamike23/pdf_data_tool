package com.scalablesolutionscloud.textextract;

/**
 * Utility class for checking character and string properties.
 */

public class CharacterCheck {
	   
	/**
     * Checks if a string starts with a capital letter.
     *
     * @param input the input string to check
     * @return true if the string starts with a capital letter, false otherwise
     */
	public static boolean startsWithCapital(String input) {
        if (input == null || input.isEmpty()) {
            return false; // Return false for empty or null strings
        }
        char firstChar = input.charAt(0);
        return Character.isUpperCase(firstChar);
    }
	
	/**
     * Checks if a string contains no consecutive spaces.
     *
     * @param tokens the string to check for consecutive spaces
     * @return true if the string contains no consecutive spaces, false otherwise
     */
    public static boolean containsNoConsecutiveSpaces(String tokens) {
            if (tokens.contains("    ")) { // Four consecutive spaces
                return false;
            }
        
        return true;
    }
    
    /**
     * Checks if a string contains a link (HTTP or HTTPS).
     *
     * @param token the string to check for links
     * @return true if the string contains a link, false otherwise
     */
    public static boolean containsLink(String token) {
        return token.contains("http://") || token.contains("https://");
    }
       
    /**
     * Checks if a sentence starts with a lowercase letter.
     *
     * @param str the sentence to check
     * @return true if the sentence starts with a lowercase letter, false otherwise
     */
    public static boolean startsWithLowerCase(String str) {
        // Check if the first character of the string is lowercase
    	if(str.length() == 0) {
    		return false;
    	}
    	else {
    		char firstChar = str.charAt(0);
            return Character.isLowerCase(firstChar);
    	}
        
    }
    
    /**
     * Checks if a string ends with punctuation.
     *
     * @param str the string to check for punctuation at the end
     * @return true if the string ends with punctuation, false otherwise
     */    
   public static boolean endsWithPunctuation(String str) {
       // Regular expression to match any form of punctuation at the end of the string
       String regex = ".*[.,!?;:]$";

       return str.matches(regex);
   }
   
   
}
