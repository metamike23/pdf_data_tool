package com.scalablesolutionscloud.textextract;

/**
 * Interface for a text container that stores and provides access to text items.
 */
public interface TextContainerInterface {

	/**
     * Adds an item to the text container.
     *
     * @param item the item to add
     */
	public void add(String item);
	
	/**
     * Returns a string representation of the text container.
     *
     * @return a string representation of the text container
     */
	public String toString();
	
	/**
     * Retrieves the item at the specified index.
     *
     * @param i the index of the item to retrieve
     * @return the item at the specified index
     */
	public String get(int i);
}