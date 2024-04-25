package com.scalablesolutionscloud.textextract;


/**
 * A custom container class for storing and processing text strings. It is designed to dynamically manage a collection of strings,
 * automatically resizing as needed and applying text cleaning methods upon addition of new entries.
 * This class integrates text cleaning functionalities from the {@link SentenceCleaner} class.
 */
public class TextContainer implements TextContainerInterface {

	private SentenceCleaner sentence_cleaner;
	private String[] text;
	private int size = 0;
	
	/**
     * Constructs a new TextContainer with an initial capacity and initializes a {@link SentenceCleaner} for processing text.
     */
	public TextContainer() {
		text = new String[5];
		sentence_cleaner = new SentenceCleaner();
	}
	
	/**
     * Increases the capacity of the internal array to hold more strings.
     * @param capacity The new capacity of the array.
     */
	private void resize(int capacity) {
		String[] temp = new String[capacity];
		for(int i = 0; i < size; i++) {
			temp[i] = text[i];
		}
		text = temp;
	}
	
	
	/**
     * Adds a sentence to the container after applying a series of cleaning operations.
     * Sentences are only added if they pass certain checks such as not containing consecutive spaces
     * and starting with a capital letter after cleaning.
     *
     * @param sentence The sentence to be added to the container.
     */
	@Override
    public void add(String sentence) {
		// If the array is full resize it to a new capacity
		if(size == text.length) {
			int cap = size * 2;
			resize(cap);
		}
				
		if(CharacterCheck.containsNoConsecutiveSpaces(sentence)) {
			
			String str = sentence_cleaner.removeLinks(sentence);
			str = sentence_cleaner.cleanNumerics(str);
			str = sentence_cleaner.cleanSpecialCharacters(str);
			str = sentence_cleaner.replaceIfMultipleOccurrences(str, 2);
			
			if(str.length() > 1 && CharacterCheck.startsWithCapital(str)) {
				text[size] = str.stripTrailing();
				size++;
			}		
		}	
    }

	/**
     * Converts the contents of the container to a single string with each sentence on a new line.
     * @return A string representation of the container's contents.
     */
	@Override
	public String toString() {
		String result = "";
		for(int i = 0; i < size; i++) {
			result = result + "\n" + text[i];
		}
		return result;
	}
	
	/**
     * Retrieves the sentence at the specified index.
     * @param i The index of the sentence to retrieve.
     * @return The sentence at the specified index, or null if the index is out of bounds.
     */
	@Override
	public String get(int i) {
		if(i > size) {
			return null;
		}
		else {
			return text[i];
		}
	}
	
	/**
     * Returns the current number of sentences stored in the container.
     * @return The size of the container.
     */
	public int getSize() {
		return size;
	}
	
	/**
     * Returns the array of strings stored in the container.
     * @return The array of stored strings.
     */
	public String[] getText() {
		return text;
	}
		

}