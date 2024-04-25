package com.scalablesolutionscloud.textextract;

/**
 * Represents a cleaned text obtained after applying various cleaning processes.
 * The cleaning process involves removing noise, formatting issues, and other unwanted elements from the input text.
 */
public class CleanedText {
	
	private StringBuilder cleaned_text_builder;
	private FinalTextCleaner ftc;
	
	private String cleaned_text;
	private int sentence_count;
	private int character_count;
	private int token_count;
	
	
	/**
     * Constructs a CleanedText object from an array of sentences.
     * Initializes the cleaning process by invoking FinalTextCleaner's processStrings method.
     * Counts the number of sentences, tokens, and characters in the cleaned text.
     *
     * @param sentences an array of sentences to be cleaned
     */
	public CleanedText(String[] sentences) {
		
		this.ftc = new FinalTextCleaner();
		this.cleaned_text_builder = new StringBuilder();
		
		// Use the FinalTextCleaner processStrings() method
		// This is the second stage and last stage of the text cleaning process
		String[] temp = this.ftc.processStrings(sentences);
		this.sentence_count = temp.length;
		
		// Iterate over the array and append each string to the StringBuilder
        for (String str : temp) {
        	this.cleaned_text_builder.append(str);
        	this.cleaned_text_builder.append(" "); // Add a space between each string (optional)
        }

        // Convert StringBuilder to a String
        this.cleaned_text = this.cleaned_text_builder.toString();
        
        count(this.cleaned_text);
	
	}
	

	/**
     * Counts the number of tokens and characters in the input string.
     *
     * @param input the input string
     */
	private void count(String input) {
        // Counting tokens
        String[] tokens = input.split("\\s+"); // Split the string by whitespace
        this.token_count  = tokens.length;

        // Counting characters
        this.character_count = input.length();
    }
	
	
	/**
     * Retrieves the cleaned text.
     *
     * @return the cleaned text
     */
	public String getCleanedText() {
		return this.cleaned_text;
	}
	
	/**
     * Retrieves the number of sentences in the cleaned text.
     *
     * @return the number of sentences
     */
	public int getSentenceCount() {
		return this.sentence_count;
	}
	
	
	/**
     * Retrieves the number of characters in the cleaned text.
     *
     * @return the number of characters
     */
	public int getCharacterCount() {
		return this.character_count;
	}
	
	
	/**
     * Retrieves the number of tokens in the cleaned text.
     *
     * @return the number of tokens
     */
	public int getTokenCount() {
		return this.token_count;
	}
	
	

}


